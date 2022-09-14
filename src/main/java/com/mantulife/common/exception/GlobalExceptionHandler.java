package com.mantulife.common.exception;

import com.mantulife.common.model.enums.ErrorCodeEnEnum;
import com.mantulife.common.model.enums.ErrorCodeEnum;
import com.mantulife.common.utils.Result;
import com.mantulife.common.utils.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static cn.hutool.core.text.CharSequenceUtil.isNotEmpty;

/**
 * author W_wang
 * version V1.0
 * Package com.xinchao.ims.common.exception
 * remark 全局异常处理
 * email 1352255400@qq.com
 * date 2020/8/4 17:22
 * Copyright www.dx.com
 */
@Slf4j
@RestControllerAdvice
@ControllerAdvice
public class GlobalExceptionHandler {

    @Resource
    private UserUtil userUtil;

    //指定出现什么异常执行这个方法
    @ExceptionHandler(Exception.class)
    @ResponseBody //为了返回数据
    public Result error(Exception e) {
        e.printStackTrace();
        // 获取错误码
        Integer errorCode = getErrorCode(e);
        log.info("全局异常：" + e.getMessage() + "--" + errorCode);
        // 获取错误信息
        String msg = getMsg(errorCode);
        return Result.error().code(errorCode).message(isNotEmpty(msg) ? msg : e.getMessage());
    }

    //特定异常
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody //为了返回数据
    public Result error(ArithmeticException e) {
        e.printStackTrace();
        // 获取错误码
        Integer errorCode = getErrorCode(e);
        log.info("ArithmeticException异常：" + e.getMessage() + "--" + errorCode);

        // 获取错误信息
        String msg = getMsg(errorCode);
        return Result.error().code(errorCode).message(msg);
    }

    //自定义异常
    @ExceptionHandler(BusinessException.class)
    @ResponseBody //为了返回数据
    public Result error(BusinessException e) {
        e.printStackTrace();
        // 获取错误码
        Integer errorCode = getErrorCode(e);
        log.info("ImsException异常：" + e.getMessage() + "--" + errorCode);

        // 获取错误信息
        String msg = getMsg(errorCode);
        return Result.error().code(errorCode).message(msg);
    }

    //验证异常处理
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result handleVaildException(MethodArgumentNotValidException e) {
        log.error("数据验证失败{}，异常类型{}", e.getMessage(), e.getClass());


        //接收异常
        BindingResult bindingResult = e.getBindingResult();
        Map<String, String> map = new HashMap<>();
        StringBuilder str = new StringBuilder();
        bindingResult.getFieldErrors().forEach((error) -> {
            map.put(error.getField(), error.getDefaultMessage());
            str.append(error.getDefaultMessage() + "、");
        });
        String strs = str.toString().replaceAll("^、*|、*$", "");
        // 获取错误码
        Integer errorCode = getErrorCode(e);
        log.info("ImsException异常：" + e.getMessage() + "--" + errorCode);
        return Result.error().code(ErrorCodeEnum.VAILD_EXCEPTION.code()).message(strs);
    }


    //自定义异常
    @ExceptionHandler(value = Throwable.class)
    public Result handleException(Throwable throwable) {
        log.error("自定义异常{}，异常类型{}", throwable.getMessage(), throwable.getClass());

        // 获取错误信息
        String msg = getMsg(ErrorCodeEnum.UNKNOW_EXCEPTION.code());
        return Result.error().data("info", throwable.getMessage()).code(ErrorCodeEnum.UNKNOW_EXCEPTION.code()).message(msg);
    }


    /**
     * author W_wang
     * version V1.0
     * remark 获取错误码
     * email 1352255400@qq.com
     * date 2020/8/4 17:22
     */
    private Integer getErrorCode(Exception e) {
        Class<?> clazz = e.getClass();
        Integer errorCode = ErrorCodeEnum.FAIL.code();
        try {
            for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
                Field[] field = clazz.getDeclaredFields();
                for (Field f : field) {
                    //向上循环 遍历父类
                    f.setAccessible(true);
                    String name = f.getName();
                    if ("errorCode".equals(name)) {
                        Object o = f.get(e);
                        errorCode = Integer.parseInt(o.toString());
                    }
                }
            }
        } catch (IllegalAccessException ee) {
            ee.getStackTrace();
        }
        return errorCode;
    }

    /**
     * author W_wang
     * version V1.0
     * remark 获取信息
     * email 1352255400@qq.com
     * date 2020/8/4 17:22
     */
    private String getMsg(Integer code) {
        // 获取语言环境
        String userLanguage = userUtil.getUserLanguage();
        String msg = "";
        try {
            switch (userLanguage) {
                case "zh":
                    msg = ErrorCodeEnum.getEnum(code).msg();
                    break;
                case "en":
                    msg = ErrorCodeEnEnum.getEnum(code).msg();
                    break;
                default:
                    msg = ErrorCodeEnum.getEnum(code).msg();
                    break;
            }
        } catch (Exception e) {
            log.info("code不存在");
            log.info(e.getMessage());
        }
        return msg;
    }
}
