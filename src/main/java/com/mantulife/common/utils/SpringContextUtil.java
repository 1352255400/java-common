package com.mantulife.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


/**
 * author W_wang
 * version V1.0
 * remark spring bean 工具类
 * email 1352255400@qq.com
 * date 2020/8/4 17:22
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {
    /**
     * Spring应用上下文环境
     */
    private static ApplicationContext applicationContext;

    /**
     *  实现ApplicationContextAware接口的回调方法。设置上下文环境
     * @param applicationContext param
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        SpringContextUtil.applicationContext = applicationContext;
    }

    /**
     * @return  return ApplicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 获取对象
     *
     * @param name name
     * @return  return Object
     * @throws BeansException throws
     */
    public static Object getBean(String name) throws BeansException {
        return applicationContext.getBean(name);
    }

    /**
     *  获取对象通过Class
     * @param cls cls
     * @param <C> c
     * @return  return return
     * @throws BeansException throws
     */
    public static <C> Object getBean(Class<C> cls) throws BeansException {
        return applicationContext.getBean(cls);
    }

}