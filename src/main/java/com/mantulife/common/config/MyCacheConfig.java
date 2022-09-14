package com.mantulife.common.config;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import com.mantulife.common.exception.BusinessException;
import com.mantulife.common.model.enums.ErrorCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.util.DigestUtils;

import java.lang.reflect.Field;
import java.time.Duration;

/**
 * author W_wang
 * MyCacheConfig 缓存配置类
 * email 1352255400@qq.com
 * date 2022-03-03 14:51:53
 */
@EnableConfigurationProperties(CacheProperties.class)
@Configuration
@Slf4j
public class MyCacheConfig {

    /**
     * author W_wang
     * KeyGenerator 重写缓存key，将参数作为条件传递
     * email 1352255400@qq.com
     * date 2022-03-03 14:51:53
     * @return  return return
     */
    @Bean("myKeyGenerator")
    public KeyGenerator keyGenerator() {
        return (o, method, objects) -> {
            //获取所有请求参数
            Object param = objects[0];
            //生成key（所有参加进行MD5加密）
            String name = method.getName();
            String key = name + "_" + DigestUtils.md5DigestAsHex(handleParams(param).getBytes());
            log.info("缓存" + key);
            return key;
        };
    }

    /**
     * 获取字段属性和值
     *
     * author W_wang
     * email 1352255400@qq.com
     * date 2021-04-15 09:19:33
     * @param params params
     * @return  return return
     */
    private String handleParams(Object params) {
        StringBuilder result = new StringBuilder();
        Class<?> clazz = params.getClass();
        try {
            for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
                Field[] field = clazz.getDeclaredFields();
                for (Field f : field) {
                    //向上循环 遍历父类
                    f.setAccessible(true);
                    Object o = f.get(params);
                    String name = f.getName();
                    result.append(name).append(":").append(o).append("-");
                }
            }
        } catch (IllegalAccessException e) {
            throw new BusinessException(ErrorCodeEnum.FAIL);
        }

        return result.toString();
    }


    @Bean
//    @Primary
    public RedisCacheManager redisCacheManager(LettuceConnectionFactory lettuceConnectionFactory, CacheProperties cacheProperties) {
        RedisSerializationContext.SerializationPair<Object> objectSerializationPair = RedisSerializationContext.SerializationPair.fromSerializer(new GenericFastJsonRedisSerializer());
        // 加载配置文件中用户自定义配置
        // 拿出redis部分
        CacheProperties.Redis redisProperties = cacheProperties.getRedis();
        // 缓存的有效期
        Duration timeToLive = Duration.ofMinutes(1);
        if (redisProperties.getTimeToLive() != null) {
            timeToLive = redisProperties.getTimeToLive();
        }

        // 键的前缀
        String keyPrefix = "";
        if (redisProperties.getKeyPrefix() != null) {
            keyPrefix = redisProperties.getKeyPrefix();
        }

        return RedisCacheManager.builder(lettuceConnectionFactory)
                .cacheDefaults(
                        RedisCacheConfiguration.defaultCacheConfig(Thread.currentThread().getContextClassLoader())
                                .serializeValuesWith(objectSerializationPair)
                                .entryTtl(timeToLive)
                                .prefixCacheNameWith(keyPrefix)
                )
                .build();
    }

}
