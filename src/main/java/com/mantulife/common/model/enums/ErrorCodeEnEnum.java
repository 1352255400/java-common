package com.mantulife.common.model.enums;

/**
 * author W_wang
 * ErrorCodeEnum 错误码-英文
 * email 1352255400@qq.com
 * date 2021-04-15 09:19:33
 */
public enum ErrorCodeEnEnum {
    /**
     * 前两位应用-中间两位模块-后3位错误编码（1000000：公共编码）
     */
    // 公共错误码
    SUCCESS(0, "success"),//操作成功
    FAIL(10000000, "fail"),// 操作失败
    VILID_FAIL(10000001, "Validation failed"), // 验证失败
    DATA_NOT_EXIST(10000002, "Data does not exist"),//数据不存在
    TOKEN_NOT_EXIST(10000003, "Please pass in a token"),//请传入token
    TOKEN_IS_EXPIRED(10000004, "Token has expired"),//token已过期
    USER_NOT_EXIST(10000005, "Account does not exist"),//账号不存在
    USER_DISABLE(10000006, "Account has been disabled"),//账号已被禁用
    NO_AUTH(10000007, "Insufficient permissions"),//权限不足，请联系管理员
    AUTH_FAIL(10000008, "Authentication failed, please login again"),//认证失败,请重新登录
    TITLE_IS_EXISTS(10000009, "Title already exists"),//标题已存在
    NAME_IS_EXISTS(10000010, "Name already exists"),//名称已存在
    IP_EXCEPTION(10000011, "IP exception"),//ip异常
    REQUEST_EXCEPTION(10000012, "Request exception"),//请求异常
    SERVER_EXCEPTION(10000013, "Feign service exception"),//Feign服务异常
    UNKNOW_EXCEPTION(10000014, "System unknown exception"),//系统未知异常
    VAILD_EXCEPTION(10000015, "Parameter validation exception"),//参数验证异常
    ROUTE_EXCEPTION(10000016, "Routing exception"),//路由异常
    DATA_EXIST(10000017, "Data already exists"),//数据已存在
    USER_IS_NOT_ACCESS(10000018, "Account no permission"),//用户没有权限
    NEW_PWD_CHECK_FAIL(10000019, "New password and confirmed password are inconsistent"),//新密码和确认密码不一致
    OLD_PWD_CHECK_FAIL(10000020, "Old password incorrect"),//旧密码不正确
    SMS_TEMPLATE_NOT_EMPTY(10000021, "SMS template type cannot be empty"),//短信模板类型不能为空
    SMS_MOBILE_NOT_EMPTY(10000022, "Mobile number cannot be empty"),//手机号不能为空
    SMS_MOBILE_NOT_REGEX(10000023, "Incorrect mobile number format"),//手机号格式不正确
    SMS_VERIFICATION_EXISTED(10000024, "Verification code has been sent, please check it"),//验证码已发送，请注意查收
    CAPTCHA_CODE_IS_ERROR(10000025, "Verification code error"),//验证码错误
    FILE_NAME_NOT_EMPTY_ERROR(10000026, "Attachment name cannot be empty"),//Attachment name cannot be empty
    SAVE_FILE_FAIL_ERROR(10000027, "Failed to save attachment"),//保存附件失败
    DELETE_FILE_FAIL_ERROR(10000028, "Failed to delete attachment"),//删除附件失败
    GET_FILE_DATA_FAIL_ERROR(10000029, "Failed to get attachment data"),//获取附件数据失败
    TOO_MANY_REQUESTS(10000030, "TOO_MANY_REQUESTS(流量过大)"),
    SERVICE_UNAVAILABLE(10000031, "SERVICE_UNAVAILABLE (服务不存在)"),
    TERMINAL_EXCEPTION(10000032, "Terminal Exception (终端异常)"),


    // COMPANY服务 错误码 5000000-5999999
    SAVE_USER_WORK_FAIL_ERROR(5000000, "Failed to add work experience"),//新增工作经历失败
    SAVE_USER_PROJECT_FAIL_ERROR(5000001, "Failed to add project experience"),//新增项目经历失败
    SAVE_USER_CONTRACT_FAIL_ERROR(5000002, "Failed to add contract information"),//新增合同信息失败
    // 5100000-5199999:组织架构
    CODE_EXIST(5000003, "Job No. already exists"),//工号已存在
    USER_EXIST(5000004, "The mobile phone number has been occupied"),//用户已存在
    SMS_EXIST(5000005, "Mobile number already exists"),//手机号已存在
    COMPANY_NOT_EXIST(50000012, "Company does not exist"),//公司不存在
    COMPANY_IS_DISABLE(50000013, "Abnormal company status"),//公司状态异常
    COMPANY_IS_NOT_ACCESS(50000014, "The company has no authority"),//公司没有权限
    DEPARTMENT_IN_USE(50000015, "This department is in use and cannot be deleted"),//该部门正在使用，不能删除
    JOB_CATEGORY_IN_USE(50000016, "This position category is in use and cannot be deleted"),//该职位类别正在使用，不能删除
    DEPARTMENT_IS_PARENT(50000017, "The superior is the subordinate of the current department and cannot be updated"),//上级是当前部门的下级，不能更新
    DEPARTMENT_NOT_SELF(50000018, "The superior cannot update for himself"),//上级不能为自己，不能更新
    JOB_CATEGORY_IS_PARENT(50000019, "The superior is the subordinate of the current job category and cannot be updated"),//上级是当前职位类别的下级，不能更新
    JOB_CATEGORY_NOT_SELF(50000020, "The superior cannot update for himself"),//上级不能为自己，不能更新
    DEPARTMENT_HAVE_CHILDREN(50000021, "There is a sub-department, please delete the sub-department first"),//存在子级部门，请先删除子级部门
    JOB_CATEGORY_HAVE_CHILDREN(50000022, "There are sub-level positions, please delete the sub-level positions first"),//存在子级职位，请先删除子级职位
    JOB_IN_USE(50000023, "The job is in use and cannot be deleted"),//该职位正在使用，不能删除
    POST_IN_USE(50000024, "The post is in use and cannot be deleted"),//该职务正在使用，不能删除
    ROLE_IN_USE(50000025, "This permission is in use and cannot be removed"),//该权限正在使用，不能删除
    // 5100000-5199999:组织架构 end


    // im服务 错误码
    IM_USER_NO_AUTH(9000001, "Insufficient permissions to delete the user"),//权限不足，无法删除该用户
    IM_FRIEND_IS_EXISTS(9000002, "Friend already exists"),//朋友已存在
    IM_FRIEND_NOT_IS_SELF(9000003, "No need to add your own friends"),//不用加自己好友
    // im服务 错误码 end

    //cloudDisk 云盘服务 错误码区间：4000000-4999999
    CLOUD_DISK_SHARE_DISABLED(4000001, "The cloud disk sharing link fails"),

    // dx-auth 认证中心：5000  错误码区间：3000000-3999999
    CAPTCHA_IS_ERROR(3000000, "Verification code error"),//验证码错误
    APPLY_SECRET(3000001, "Please contact your superior to apply for Google password"),//请联系上级申请谷歌口令
    SECRET_IS_ERROR(3000002, "Google password error"),//谷歌口令错误
    ACCOUNT_OR_PWD_IS__ERROR(3000003, "Incorrect account or password"),//账号或者密码不正确
    ACCOUNT_ISNOT_BINDING_COMPANY(3000004, "No company is bound to the account"),//账号没有绑定公司
    ACCOUNT_NO_COMPANY_AVAILABLE(3000005, "No company available under the account"),//账号下没有可用公司
    // dx-auth 认证中心：5000  错误码区间：3000000-3999999 end

    //dx-third-partys 第三方服务：8000 错误码区间：6000000-6999999
    JOIN_CODE_NOT_EMPTY(6000000, "Membership code cannot be empty"),//入会码不能为空
    JOIN_CODE_ERROR(6000001, "Membership code error"),//入会码不正确
    HOST_IS_NOT_THE_CURRENT_USER(6000002, "The meeting support person is not the current user"),//会议主持人不是当前用户
    MEETING_IS_CLOSE(6000003, "The meeting has ended and cannot be entered"),//会议已经结束，不可进入
    MEETING_NOT_STARTED(6000004, "The meeting has not started and cannot be entered"),//会议未开始，不可进入
    MEETING_NOT_IN_ROOM(6000005, "User has left, cannot be set"),//用户已离开，不可设置
    ONLY_HOST_SHARE(6000006, "The host can invite members"),//主持人才能邀请成员
    MEETING_DOES_NOT_EXIST(6000007, "The meeting does not exist, please check"),//会议不存在,请检查
    USER_NOT_IN_MEETING_LIST(6000008, "The user is not in the meeting list, please check"), //用户不在会议名单，请检查
    CREATOR_MEETING_MEMBER_ERROR(6000009, "Description Failed to create meeting member information"),//创建会议成员信息失败
    GAIN_A_MEETING_ID_NOT_NULL(6000010, "The relaunch conference ID cannot be empty"),//再次发起 会议id不能为空
    MEETING_INVITE_ERROR(6000014, "User re-invitation failed"),//用户再邀请失败
    LINK_JOIN_ERROR(60000015, "External link membership failed"),//外链入会失败

    //OpenPlatform 7000000-7999999
    USER_LIST_IS_NULL(7000000, "User ID cannot be empty"),
    USER_BINDING_IS_NULL(7000001, "No jiguang binding user was queried"),
    SEND_PUSH_FAIL(7000002, "Push failed"),
    SIGN_FAIL(7000003, "Signature verification failed"),// 签名校验失败
    ;


    private int code;
    private String msg;

    public String msg() {
        return msg;
    }

    public int code() {
        return code;
    }

    ErrorCodeEnEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ErrorCodeEnEnum getEnum(int code) {
        for (ErrorCodeEnEnum ele : ErrorCodeEnEnum.values()) {
            if (ele.code() == code) {
                return ele;
            }
        }
        return null;
    }
}
