package com.mantulife.common.utils;


import cn.hutool.core.util.StrUtil;
import com.mantulife.common.model.enums.ErrorCodeEnum;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * author W_wang
 * version V1.0
 * remark 返回 工具类
 * email 1352255400@qq.com
 * date 2020/8/4 17:22
 * Copyright www.dx.com
 */
@Data
@Slf4j
public class Result {

    private Boolean success;

    private Integer code;

    private String msg;

    private Map<String, Object> data = new LinkedHashMap<>();

    public static Integer SUCCESS_CODE = 0; //成功

    public static Integer ERROR_CODE = 1000; //失败

    //把构造方法私有
    private Result() {
    }

    //成功静态方法
    public static Result ok() {
        Result r = new Result();
        r.setSuccess(true);
        r.setCode(SUCCESS_CODE);
        r.setMsg("成功");
        return r;
    }

    //失败静态方法
    public static Result error() {
        Result r = new Result();
        r.setSuccess(false);
        r.setCode(ERROR_CODE);
        r.setMsg("失败");
        return r;
    }

    //失败静态方法
    public static Result error(Integer code) {
        Result r = new Result();
        r.setSuccess(false);
        r.setCode(code);
        r.setMsg("失败");
        return r;
    }

    public Result success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    public Result message(String message) {
        if (StrUtil.isNotEmpty(message)) {
            this.setMsg(message);
        }
        return this;
    }

    public Result code(Integer code) {
        this.setCode(code);
        try {
            String codeMsg = ErrorCodeEnum.getEnum(code).msg();
            if (StrUtil.isNotEmpty(codeMsg)) {
                this.setMsg(codeMsg);
            }
        } catch (Exception e) {
            log.info("code不存在");
            log.info(e.getMessage());
        }

        return this;
    }

    public Result data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public Result data(Map<String, Object> map) {
        this.setData(map);
        return this;
    }
}

