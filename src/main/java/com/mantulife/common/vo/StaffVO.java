package com.mantulife.common.vo;

import lombok.Data;

@Data
public class StaffVO {

    //employeeNo,employeeName,gender,phoneNumber,deptCode,deptName,businessEmail,positionCode,positionName,disabled
    private String employeeNo;
    private String employeeName;
    private String gender;
    private String nickName;
    private String deptCode;
    private String deptName;
    private String businessEmail;
    private String status;
    private String phoneNumber;
    private String master;
    private String masterEmployeeNo;
    private String contractCode;
    private String contractCodeName;
    private String positionCode;
    private String positionName;
    private String disabled;//0启用1禁用


    //private String personalEmail;
    //private String phoneNumer;
//    private String managerEmployeeName;
//    private String managerEmployeeNo;
//    private String education;
//    private String positionType;
//    private String mdCreateTime;
//    private String workBase;
//    private String source;
//    private String professionalAbilityLevel;
//    private String sourceCode;
//    private String probation;
//    private String leaveType;
//    private String leaveTime;
//    private String dingId;
//    private String contractStartDate;
//    private String mdCreateUser;
//    private String idCardNumber;
//    private String custCityCode;
//    private String startDate;
//    private String probationPeriodEndDate;
//    private String regionName;
//    private String professionalLine;
//    private String dingOpenid;
//    private String regionCode;
//    private String executiveDeptCode;
//    private String custCityName;
//    private String classCode;
//    private String contractEndDate;
//    private String wxId;
//    private String salaryLevelName;
//    private String createTime;
//    private String executiveDeptName;
//    private String salaryLevel;
}
