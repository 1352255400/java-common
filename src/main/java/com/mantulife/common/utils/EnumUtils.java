package com.mantulife.common.utils;

import com.google.common.collect.Lists;
import com.mantulife.common.model.enums.IEnum2ConfigEnum;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


/**
 * author W_wang
 * version V1.0
 * remark 将枚举转成Enum2Config对象
 * * 转换需要实现IEnum2ConfigEnum
 * email 1352255400@qq.com
 * date 2020/8/4 17:22
 */
@Component
public class EnumUtils {

    private EnumUtils() {
    }

    /**
     * 方法标识
     */
    private static final String M1 = "getCode";
    /**
     * 方法标识
     */
    private static final String M2 = "getMsg";

    @Resource
    private UserUtil userUtil;

    /**
     * author W_wang
     * version V1.0
     * remark 枚举转化成对象
     * email 1352255400@qq.com
     * date 2020/8/4 17:22
     *
     * @param clz clz
     * @return  return return
     */
    public List<Enum2Config> getEnum2Configs(Class<? extends IEnum2ConfigEnum> clz) {

        if (!userUtil.getUserLanguage().equals("zh")) {
            String name = clz.getCanonicalName();
            try {
                clz = (Class<? extends IEnum2ConfigEnum>) Class.forName(name.replace("Enum", "EnEnum"));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        List<Enum2Config> result = Lists.newArrayList();
        if (Objects.isNull(clz) || !IEnum2ConfigEnum.class.isAssignableFrom(clz)) {
            return result;
        }
        Object[] objects = clz.getEnumConstants();
        Method method1 = null;
        try {
            method1 = clz.getMethod(M1);
            Method method2 = clz.getMethod(M2);
            for (Object object : objects) {
                Integer code = (Integer) method1.invoke(object);
                String msg = method2.invoke(object).toString();
                Enum2Config enum2Config = Enum2Config.builder().code(code).msg(msg).build();
                result.add(enum2Config);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * author W_wang
     * version V1.0
     * remark 枚举转化成map
     * email 1352255400@qq.com
     * date 2020/8/4 17:22
     *
     * @param clz clz
     * @return  return return
     */
    public static Map<Integer, String> getEnum2Map(Class<? extends IEnum2ConfigEnum> clz) {
        Map<Integer, String> map = new HashMap<>();
        if (Objects.isNull(clz) || !IEnum2ConfigEnum.class.isAssignableFrom(clz)) {
            return map;
        }

        Object[] objects = clz.getEnumConstants();
        try {
            Method method1 = clz.getMethod(M1);
            Method method2 = clz.getMethod(M2);

            for (Object object : objects) {
                Integer code = (Integer) method1.invoke(object);
                String msg = method2.invoke(object).toString();
                map.put(code, msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }
}
