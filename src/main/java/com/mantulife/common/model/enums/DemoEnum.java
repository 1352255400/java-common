package com.mantulife.common.model.enums;

import com.baomidou.mybatisplus.annotation.IEnum;

import java.util.Arrays;

/**
 * author W_wang
 * DemoEnum 枚举demo
 * email 1352255400@qq.com
 * date 2021-04-15 09:19:33
 */
public enum DemoEnum implements IEnum<Integer>, IEnum2ConfigEnum<Integer, String> {
    SYSTEM(1, "平台"),
    BUSINESS(2, "企业端"),
    APP(3, "APP-平台"),
    APP_BUSINESS(4, "APP-企业端"),
    ;


    private Integer code;
    private String msg;

    DemoEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public Integer getValue() {
        return code;
    }


    public static DemoEnum getByCode(Integer code) {
        return Arrays.stream(DemoEnum.values())
                .filter(workOrderTypeEnum -> workOrderTypeEnum.getValue().equals(code)).findFirst().orElseGet(() -> null);
    }
}
