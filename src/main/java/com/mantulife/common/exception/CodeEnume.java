package com.mantulife.common.exception;

/**
 * @author W_wang
 * @version V1.0
 * @Package com.xinchao.ims.common.exception
 * @remark 枚举错误码
 * @email 1352255400@qq.com
 * @date 2020/8/4 17:22
 * @Copyright www.mantulife.com
 */
public enum CodeEnume {
    UNKNOW_EXCEPTION(10000, "系统未知异常"),
    VAILD_EXCEPTION(10001, "参数验证异常"),
    ROUTE_EXCEPTION(10002, "路由异常");

    private int code;
    private String msg;

    CodeEnume(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
