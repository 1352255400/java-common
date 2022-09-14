package com.mantulife.common.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * author W_wang
 * FileVo 文件返回VO
 * email 1352255400@qq.com
 * date 2021-08-02 14:13:22
 */
@Data
public class FileVo {

    @ApiModelProperty(value = "文件名称")
    private String name;

    @ApiModelProperty(value = "文件大小")
    private Long size;

    @ApiModelProperty(value = "文件大小")
    private String sizeFormet;

    @ApiModelProperty(value = "文件类型")
    private String type;

    @ApiModelProperty(value = "文件类型后缀")
    private String typeSuffix;

    @ApiModelProperty(value = "文件地址")
    private String url;
}
