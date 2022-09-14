package com.mantulife.common.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * author W_wang
 * UserVo 用户基本信息
 * email 1352255400@qq.com
 * date 2021-08-02 14:13:22
 */
@Data
public class UserVo {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "公司id")
    private Long companyId;

    @ApiModelProperty(value = "公司名称")
    private String companyName;

    @ApiModelProperty(value = "公司logo")
    private String companyLogo;

    @ApiModelProperty(value = "工号")
    private String code;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "简介")
    private String remark;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "角色名称")
    private String roleNames;

    @ApiModelProperty(value = "登录ip")
    private String loginIp;

    @ApiModelProperty(value = "是否是超管：0否1是")
    private Integer isSuperAdmin;

    @ApiModelProperty(value = "部门id")
    private Long departmentId;

    @ApiModelProperty(value = "职位id")
    private Long jobId;

    @ApiModelProperty(value = "状态：0正常1禁用")
    private Integer status;

    @ApiModelProperty(value = "花名")
    private String nikeName;

    @ApiModelProperty(value = "性别")
    private Integer gender;

    @ApiModelProperty(value = "是否是系统平台用户")
    private Integer isSystemUser = 0;

    @ApiModelProperty(value = "登录原（PC:0,IOS:1,安卓:2）", required = true)
    private Integer source;

    @ApiModelProperty(value = "设备id：防止token被拦截（app必填）：api-header头除了token外必传字段", required = true)
    private String deviceID;
}
