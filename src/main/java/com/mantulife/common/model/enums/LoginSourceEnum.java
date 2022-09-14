package com.mantulife.common.model.enums;

import com.baomidou.mybatisplus.annotation.IEnum;

import java.util.Arrays;

/**
 * author W_wang
 * DemoEnum 登录来源
 * email 1352255400@qq.com
 * date 2021-04-15 09:19:33
 */
public enum LoginSourceEnum implements IEnum<Integer>, IEnum2ConfigEnum<Integer, String> {
    PC(0, "PC"),
    IOS(1, "苹果"),
    ANDROID(2, "安卓"),
    ;


    private Integer code;
    private String msg;

    LoginSourceEnum(Integer code, String msg) {
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


    public static LoginSourceEnum getByCode(Integer code) {
        return Arrays.stream(LoginSourceEnum.values())
                .filter(workOrderTypeEnum -> workOrderTypeEnum.getValue().equals(code)).findFirst().orElseGet(() -> null);
    }
}
