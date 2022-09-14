package com.mantulife.common.exception;


import com.mantulife.common.model.enums.ErrorCodeEnum;

/**
 * BusinessException 自定义异常类
 *
 * author W_wang
 * email 1352255400@qq.com
 * date 2021-04-15 09:19:33
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -4595757411312427173L;
    private int errorCode;

    public BusinessException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.msg());
        this.errorCode = errorCodeEnum.code();
    }

    public BusinessException(ErrorCodeEnum errorCodeEnum, Throwable cause) {
        super(errorCodeEnum.msg(), cause);
        this.errorCode = errorCodeEnum.code();
    }

    public BusinessException(String message) {
        super(message);
        this.errorCode = 500;
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = 500;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
