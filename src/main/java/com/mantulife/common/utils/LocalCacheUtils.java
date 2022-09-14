package com.mantulife.common.utils;

import cn.hutool.core.util.ObjectUtil;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * author W_wang
 * version V1.0
 * remark 缓存工具类
 * email 1352255400@qq.com
 * date 2020/8/4 17:22
 */
@Slf4j
public class LocalCacheUtils<K, V> {

    private static long EXPIRE_TIME = 30;
    private static TimeUnit EXPIRE_TIME_TU = TimeUnit.MINUTES;
    private static boolean INIT_EXPIRE = true;


    /**
     * 获取cache
     *
     * @param duration   过期时间
     * @param tu         过期时间单位
     * @param initExpire 是否从创建时开始过期，
     *                   true-从key创建时开始计算过期时间，
     *                   false-表示从最后一次操作后开始计算过期时间
     * @param <K> param
     * @param <V> param
     * @return  return return
     */
    public static <K, V> Cache<K, V> createCache(long duration, TimeUnit tu, boolean initExpire) {
        CacheBuilder<K, V> cacheBuilder = (CacheBuilder<K, V>) CacheBuilder.newBuilder();
        duration = duration <= 0 ? EXPIRE_TIME : duration;
        tu = ObjectUtil.isNotEmpty(tu) ? tu : EXPIRE_TIME_TU;
        if (initExpire) {
            cacheBuilder.expireAfterWrite(duration, tu);
        } else {
            cacheBuilder.expireAfterAccess(duration, tu);
        }
        Cache<K, V> cache = cacheBuilder.build();
        return cache;
    }

    /**
     * 根据默认值获取cache
     * @param <K> param
     * @param <V> param
     * @return  return param
     */
    public static <K, V> Cache<K, V> createCache() {
        Cache<K, V> cache = createCache(EXPIRE_TIME, EXPIRE_TIME_TU, INIT_EXPIRE);
        return cache;
    }


    /**
     *  根据默认值获取cache
     * @param initExpire 是否从创建时开始过期，
     *      *      *                   true-从key创建时开始计算过期时间，
     *      *      *                   false-表示从最后一次操作后开始计算过期时间
     * @param <K> param
     * @param <V> param
     * @return  return param
     */
    public static <K, V> Cache<K, V> createCache(boolean initExpire) {
        Cache<K, V> cache = createCache(EXPIRE_TIME, EXPIRE_TIME_TU, initExpire);
        return cache;
    }

    /**
     * 获取map
     *
     * @param duration   过期时间
     * @param tu         过期时间单位
     * @param initExpire 是否从创建时开始过期，
     *                   true-从key创建时开始计算过期时间，
     *                   false-表示从最后一次操作后开始计算过期时间
     * @param <K> param
     * @param <V> param
     * @return  return return
     */
    public static <K, V> Map<K, V> createMap(long duration, TimeUnit tu, boolean initExpire) {
        return (Map<K, V>) createCache(duration, tu, initExpire).asMap();
    }

    /**
     * 根据默认值
     *
     * @param <K> param
     * @param <V> param
     * @return  return return
     */
    public static <K, V> Map<K, V> createMap() {
        return createMap(EXPIRE_TIME, EXPIRE_TIME_TU, INIT_EXPIRE);
    }


    /**
     * 根据默认值
     *
     * @param initExpire 是否从创建时开始过期，
     *                   true-从key创建时开始计算过期时间，
     *                   false-表示从最后一次操作后开始计算过期时间
     * @param <K> param
     * @param <V> param
     * @return  return return
     */
    public static <K, V> Map<K, V> createMap(boolean initExpire) {
        return createMap(EXPIRE_TIME, EXPIRE_TIME_TU, initExpire);
    }


    public static void main(String[] args) throws Exception {
        int loops = 5;
        Map<String, String> map = createMap(1, TimeUnit.SECONDS, true);
        map.put("k1", "v1");
        for (int i = 0; i < loops; i++) {
            Thread.sleep(500);
        }
        Cache<String, String> cache = createCache(1, TimeUnit.SECONDS, true);
        cache.put("k1", "v1");
        for (int i = 0; i < loops; i++) {
            Thread.sleep(500);
        }
        Map<String, String> map1 = createMap(1, TimeUnit.SECONDS, false);
        map1.put("k1", "v1");
        for (int i = 0; i < loops; i++) {
            Thread.sleep(500);
        }


        Cache<String, String> cache1 = createCache(1, TimeUnit.SECONDS, false);
        cache1.put("k1", "v1");
        for (int i = 0; i < loops; i++) {
            Thread.sleep(500);
        }
    }
}