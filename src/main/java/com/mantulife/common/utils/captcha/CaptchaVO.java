package com.mantulife.common.utils.captcha;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * author W_wang
 * UserVo 验证码返回VO
 * email 1352255400@qq.com
 * date 2021-08-02 14:13:22
 */
@Data
public class CaptchaVO {

    @ApiModelProperty(value = "验证码code")
    private String code;

    @ApiModelProperty(value = "验证码值")
    private String value;
}
