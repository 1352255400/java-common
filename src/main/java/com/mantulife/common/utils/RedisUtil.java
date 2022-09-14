package com.mantulife.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * author W_wang
 * version V1.0
 * remark Redis 工具类
 * email 1352255400@qq.com
 * date 2020/8/4 17:22
 * Copyright www.dx.com
 */
@Component
public class RedisUtil {

    //缓存前缀
    @Value("${spring.redis.key-prefix:oa:}")
    String prefixKey;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    //分组保存
    public void setByGroup(String keys, String key, String value, Long time) {
        //检查缓存时间
        time = time > 0 ? time : 7200;

        //初始化缓存列表
        List<String> cacheList = new ArrayList<>();

        //追加组数据（向主keys中追加key）
        String cacheData = get(keys);
        if (!StringUtils.isEmpty(cacheData)) {
            //将字符串转换成列表
            cacheList = JSON.parseObject(cacheData, new TypeReference<List<String>>() {
            });
        }

        //检查可以是否存在
        if (!cacheList.contains(key)) {
            cacheList.add(key);   //索引为0  //.add(e)
        }
        //将列表转成json
        cacheData = JSON.toJSONString(cacheList);
        set(keys, cacheData, time);

        //追加子数据
        set(key, value, time);
    }

    //分组删除
    public void deleteByGroup(String keys) {
        //获取数据
        String cacheData = get(keys);

        //将缓存数据转成列表
        List<String> cacheList = new ArrayList<>();
        if (!StringUtils.isEmpty(cacheData)) {
            cacheList = JSON.parseObject(cacheData, new TypeReference<List<String>>() {
            });
        }

        //删除子key
        cacheList.stream().forEach(key -> delete(key));

        //删除主key
        delete(keys);
    }

    public void set(String key, String value, Long time) {
        key = prefixKey + key;
        time = time > 0 ? time : 7200;
        ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
        stringStringValueOperations.set(key, value, time, TimeUnit.SECONDS);
    }

    public void delete(String key) {
        key = prefixKey + key;
        stringRedisTemplate.delete(key);
    }

    public String get(String key) {
        key = prefixKey + key;
        return stringRedisTemplate.opsForValue().get(key);
    }

    public Boolean hasKey(String key) {
        key = prefixKey + key;
        return stringRedisTemplate.hasKey(key);
    }

    // redis 获取redis时间
    public Long getExpire(String key) {
        key = prefixKey + key;
        return stringRedisTemplate.getExpire(key);
    }

    // redis 续期
    public Boolean expire(String key, int seconds) {
        key = prefixKey + key;
        return stringRedisTemplate.expire(key, seconds, TimeUnit.SECONDS);
    }

    public Long ttl(String key) {
        key = prefixKey + key;
        return stringRedisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    public Long incr(String key, Long delta) {
        key = prefixKey + key;
        return stringRedisTemplate.opsForValue().increment(key, delta);
    }

    public void hSet(String key, String field, String value) {
        key = prefixKey + key;
        HashOperations<String, String, String> stringObjectObjectHashOperations = stringRedisTemplate.opsForHash();
        stringObjectObjectHashOperations.put(key, field, value);
    }

    public String hGet(String key, String field) {
        key = prefixKey + key;
        HashOperations<String, String, String> stringObjectObjectHashOperations = stringRedisTemplate.opsForHash();
        return stringObjectObjectHashOperations.get(key, field);
    }

    public Long hDel(String key, String field) {
        key = prefixKey + key;
        HashOperations<String, String, String> stringObjectObjectHashOperations = stringRedisTemplate.opsForHash();
        return stringObjectObjectHashOperations.delete(key, field);
    }
}

