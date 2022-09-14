package com.mantulife.common.utils;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.mantulife.common.model.enums.AuthEnum;
import com.mantulife.common.model.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static cn.hutool.core.text.CharSequenceUtil.isEmpty;
import static cn.hutool.core.text.CharSequenceUtil.isNotEmpty;

/**
 * author W_wang
 * version V1.0
 * remark 用户工具类
 * email 1352255400@qq.com
 * date 2020/8/4 17:22
 * Copyright www.dx.com
 */
@Slf4j
@Component
public class UserUtil {

    @Resource
    private RedisUtil redisUtil;

    /**
     * 获取缓存用户
     *
     * @param token token
     * @return  return return
     */
    public UserVo getCacheUserByToken(String token) {
        UserVo userVo = new UserVo();
        if (ObjectUtil.isEmpty(token)) {
            return userVo;
        }

        //检查token是否过期
        String tokenInfo = redisUtil.get(token);
        if (isNotEmpty(tokenInfo)) {
            userVo = JSON.parseObject(tokenInfo, UserVo.class);
        }
        return userVo;
    }

    /**
     * 获取缓存用户
     *
     * @return  return return
     */
    public UserVo getCacheUser() {
        UserVo userVo = new UserVo();

        String token = getToken();

        if (ObjectUtil.isEmpty(token)) {
            return userVo;
        }

        //检查token是否过期
        String tokenInfo = redisUtil.get(token);
        if (isNotEmpty(tokenInfo)) {
            userVo = JSON.parseObject(tokenInfo, UserVo.class);
        }
        return userVo;
    }

    /**
     * 获取token
     *
     * @return  return return
     */
    public String getToken() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (ObjectUtil.isNull(requestAttributes) || requestAttributes == null) {
            return null;
        }
        HttpServletRequest request = requestAttributes.getRequest();
        String authHeader = request.getHeader(AuthEnum.AUTHORIZATION.getMsg());
        if (isEmpty(authHeader)) {
            return null;
        }
        // 获取token
        String token = authHeader.replace(AuthEnum.BEARER.getMsg(), "");

        log.info("获取token：" + authHeader + "===" + token);

        return token;
    }

    /**
     * 获取 用户语言
     *
     * @return  return return
     */
    public String getUserLanguage() {
        String language = "zh";
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (ObjectUtil.isNull(requestAttributes) || requestAttributes == null) {
            return language;
        }
        HttpServletRequest request = requestAttributes.getRequest();
        language = request.getHeader(AuthEnum.USER_LANGUAGE.getMsg());
        return isNotEmpty(language) ? language : "zh";
    }

    /**
     * 修改缓存
     *
     * @param userVo 用户信息
     * @return  return Boolean
     */
    public boolean updateCacheUser(UserVo userVo) {
        String token = getToken();

        if (ObjectUtil.isEmpty(token)) {
            return false;
        }

        redisUtil.set(token, JSON.toJSONString(userVo), 7200L);

        return true;
    }
}
