package com.mantulife.common.utils;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;


/**
 * author W_wang
 * version V1.0
 * remark 枚举映射类
 * email 1352255400@qq.com
 * date 2020/8/4 17:22
 */
@Data
@Builder
public
class Enum2Config implements Serializable {
    private static final long serialVersionUID = 2380340137249601742L;
    private Integer code;
    private String msg;
}