package com.mantulife.common.vo;

import lombok.Data;

@Data
public class UserBO {
    private Integer id;
    private String code;
    private String nickname;
    private String avatar;
    private String profile;
    private String email;
    private String phone;
    private String username;
    private Integer isSendMsg;
    private Integer state;
    private String ip;
}
