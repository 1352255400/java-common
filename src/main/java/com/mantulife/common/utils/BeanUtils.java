package com.mantulife.common.utils;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.beans.BeanCopier;

import java.util.Collection;
import java.util.List;
import java.util.Objects;


/**
 * bean 工具类
 *
 * author W_wang
 */
@Slf4j
public class BeanUtils<S, T> {

    private BeanUtils() {
    }

    /**
     * 缓存复制后的对象
     */
//    public static Map<String, BeanCopier> beanCopierMap = LocalCacheUtils.createMap(false);

    /**
     * 转换单个实体
     *
     * @param source source
     * @param model model
     * @param <S> s
     * @param <T> t
     * @return  return
     */
    public static <S, T> T copyBean(S source, Class<T> model) {
        if (checkNull(source) || checkNull(model)) {
            return null;
        }
        try {
            T t = model.newInstance();
            copy(source, t);
            return t;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 没有抛出异常得到copy bean
     *
     * @param source source
     * @param modelClass modelClass
     * @param <S> s
     * @param <T> t
     * @return  return
     */
    public static <S, T> T copyBeanNoException(S source, Class<T> modelClass) {
        Object model = null;
        try {
            model = copyBean(source, modelClass);
        } catch (Exception e) {
            log.info("There is wrong message for copy bean info, wrong message is {}", e.getMessage());
        }
        return checkNull(model) ? null : (T) model;
    }


    /**
     * 复制对象
     *
     * @param source source
     * @param target target
     * @return  return
     */
    public static <S, T> T copyBean(S source, T target) {
        if (checkNull(source) || checkNull(target)) {
            return null;
        }
        copy(source, target);
        return target;
    }

    private static <S, T> void copy(S source, T target) {
        BeanCopier copier = getBeanCopier(source.getClass(), target.getClass());
        if (copier != null) {
            copier.copy(source, target, null);
        }
    }

    /**
     * List集合对象转换
     *
     * @param sourceList sourceList
     * @param tClass tClass
     * @return  return
     */
    public static <S, T> List<T> copyListBean(List<S> sourceList, Class<T> tClass) {
        List<T> result = Lists.newArrayList();
        if (checkEmptyList(sourceList) || checkNull(tClass)) {
            return result;
        }
        for (S s : sourceList) {
            T t = copyBean(s, tClass);
            result.add(t);
        }
        return result;
    }

    /**
     * List集合对象转换
     *
     * @param sourceList sourceList
     * @param model model
     * @return  return
     */
    public static <S, T> List<T> copyListBeanNoException(List<S> sourceList, Class<T> model) {
        List<T> result = null;
        try {
            result = copyListBean(sourceList, model);
        } catch (Exception e) {
            log.info("There is wrong message for copy list info, wrong message is {}", e.getMessage());
        }
        return result;
    }


    /**
     * 获取BeanCopier
     * @param source source
     * @param target target
     * @return return
     */
    public static BeanCopier getBeanCopier(Class source, Class target) {
        if (checkNull(source) || checkNull(target)) {
            return null;
        }
//        String beanKey = generateKey(source, target);
        BeanCopier copier  = BeanCopier.create(source, target, false);
//        if (isUseCache && beanCopierMap.containsKey(beanKey)) {
//            copier = beanCopierMap.get(beanKey);
//        } else {
//            copier = BeanCopier.create(source, target, false);
//        }
//        //使用缓存
//        if (isUseCache) {
//            beanCopierMap.put(beanKey, copier);
//        }
        return copier;
    }


    /**
     * 获取BeanCopier
     * 使用了缓存
     *
     * @return  return
     */
//    public static BeanCopier getBeanCopier(Class source, Class target) {
//        return getBeanCopier(source, target, true);
//    }


    private static String generateKey(Class<?> clz1, Class<?> clz2) {
        if (checkNull(clz2)) {
            throw new IllegalArgumentException("There is a null value in the parameter!");
        }
        return clz1.toString().concat("_").concat(clz2.toString());
    }

    private static boolean checkNull(Object obj) {
        return Objects.isNull(obj);
    }

    private static boolean checkEmptyList(Collection collection) {
        return Objects.isNull(collection) ? true : collection.isEmpty();
    }
}

