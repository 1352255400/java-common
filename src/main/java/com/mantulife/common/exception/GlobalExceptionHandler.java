package com.mantulife.common.exception;

import com.mantulife.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * @author W_wang
 * @version V1.0
 * @Package com.xinchao.ims.common.exception
 * @remark 全局异常处理
 * @email 1352255400@qq.com
 * @date 2020/8/4 17:22
 * @Copyright www.mantulife.com
 */
@Slf4j
@RestControllerAdvice
@ControllerAdvice
public class GlobalExceptionHandler {

    //指定出现什么异常执行这个方法
    @ExceptionHandler(Exception.class)
    @ResponseBody //为了返回数据
    public R error(Exception e) {
        e.printStackTrace();
        return R.error().message("全局异常：" + e.getMessage()).data("error", e.getMessage());
    }

    //特定异常
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody //为了返回数据
    public R error(ArithmeticException e) {
        e.printStackTrace();
        return R.error().message("ArithmeticException异常：" + e.getMessage()).data("error", e.getMessage());
    }

    //自定义异常
    @ExceptionHandler(ImsException.class)
    @ResponseBody //为了返回数据
    public R error(ImsException e) {
        e.printStackTrace();
        return R.error().code(e.getCode()).message(e.getMsg());
    }

    //验证异常处理
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R handleVaildException(MethodArgumentNotValidException e) {
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
        return R.error().code(CodeEnume.VAILD_EXCEPTION.getCode()).data("data", map).message(CodeEnume.VAILD_EXCEPTION.getMsg() + ":" + strs);
    }


    //自定义异常
    @ExceptionHandler(value = Throwable.class)
    public R handleException(Throwable throwable) {
        log.error("自定义异常{}，异常类型{}", throwable.getMessage(), throwable.getClass());

        return R.error().data("info", throwable.getMessage()).code(CodeEnume.UNKNOW_EXCEPTION.getCode()).message(CodeEnume.UNKNOW_EXCEPTION.getMsg());
    }


    //自定义错误路由异常
    /*@ExceptionHandler(value = Exception.class)
    public R handleErrorPageException(Exception e) {
        log.error("路由异常{}，异常类型{}", e.getMessage(), e.getClass());

        return R.error().data("info", e.getMessage()).code(CodeEnume.ROUTE_EXCEPTION.getCode()).message(CodeEnume.ROUTE_EXCEPTION.getMsg());
    }*/
}
