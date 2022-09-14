package com.mantulife.common.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * author W_wang
 * SystemApi 接口表
 * email 1352255400@qq.com
 * date 2021-10-11 10:08:15
 */
@Data
public class ApiDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "服务名称")
    private String appName;

    @ApiModelProperty(value = "控制器名称")
    private String controllerPath;

    @ApiModelProperty(value = "控制器名称")
    private String controllerName;

    @ApiModelProperty(value = "方法名称")
    private String methodName;

    @ApiModelProperty(value = "接口地址")
    private String api;

    @ApiModelProperty(value = "请求类型")
    private String type;


}
