package com.mantulife.common.model.enums;

import com.baomidou.mybatisplus.annotation.IEnum;

import java.util.Arrays;


/**
 * 文件大类
 *
 * author W_wang
 * FileCateEnum
 * email 1352255400@qq.com
 * date 2022-03-03 14:51:53
 */
public enum FileCateEnum implements IEnum<Integer>, IEnum2ConfigEnum<Integer, String> {
    ALL(0, "全部/ALL"),
    PICTURE(1, "图片/PICTURE"),
    VIDEO(2, "视频/VIDEO"),
    DOCUMENT(3, "文档/DOCUMENT"),
    MUSIC(4, "音乐/MUSIC"),
    OTHER(5, "其他/OTHER"),
//    COMPANY_APP(6, "分享"),
//    COMPANY_APP(7, "回收站"),
    ;


    private Integer code;
    private String msg;

    FileCateEnum(Integer code, String msg) {
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


    public static FileCateEnum getByCode(Integer code) {
        return Arrays.stream(FileCateEnum.values())
                .filter(workOrderTypeEnum -> workOrderTypeEnum.getValue().equals(code)).findFirst().orElseGet(() -> null);
    }
}

