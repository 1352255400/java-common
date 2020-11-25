package com.mantulife.common.utils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author W_wang
 * @version V1.0
 * @remark 统一返回结果的类
 * @email 1352255400@qq.com
 * @date 2020/8/4 17:22
 * @Copyright www.mantulife.com
 */
@Data
public class R {

    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String msg;

    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new LinkedHashMap<>();

    public static Integer SUCCESS = 0; //成功

    public static Integer ERROR = 1000; //失败

    //把构造方法私有
    private R() {
    }

    //成功静态方法
    public static R ok() {
        R r = new R();
        r.setSuccess(true);
        r.setCode(SUCCESS);
        r.setMsg("成功");
        return r;
    }

    //失败静态方法
    public static R error() {
        R r = new R();
        r.setSuccess(false);
        r.setCode(ERROR);
        r.setMsg("失败");
        return r;
    }

    public R success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    public R message(String message) {
        this.setMsg(message);
        return this;
    }

    public R code(Integer code) {
        this.setCode(code);
        return this;
    }

    public R data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public R data(Map<String, Object> map) {
        this.setData(map);
        return this;
    }
}
