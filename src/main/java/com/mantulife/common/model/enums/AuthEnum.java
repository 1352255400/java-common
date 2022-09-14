package com.mantulife.common.model.enums;

import lombok.Getter;


/**
 * author W_wang
 * AuthEnum 认证配置
 * email 1352255400@qq.com
 * date 2021-04-15 09:19:33
 */
@Getter
public enum AuthEnum {
    AUTHORIZATION("Authorization"),
    TICKET("Ticket"),
    BEARER("Bearer "),
    USER_ID("userId"),
    TOKEN("token"),
    TOKEN_SSO("token_sso_"),
    USER_NAME("userName"),
    USER_INFO("userInfo"),
    USER_LANGUAGE("Language"),
    LOGIN_DEVICE_ID("DeviceId"),
    LOGIN_SOURCE("Source"),
    ;

    private String msg;

    AuthEnum(String msg) {
        this.msg = msg;
    }

}
