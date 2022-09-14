package com.mantulife.common.utils;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * author W_wang
 * version V1.0
 * remark 编码 工具类
 * email 1352255400@qq.com
 * date 2020/8/4 17:22
 * Copyright www.dx.com
 */
@Slf4j
public class SnCreator {

    private SnCreator() {

    }

    public static String create() {
        //随机数
        String num = RandomUtil.randomInt(100, 1000) + "";
        return TimeUtil.getDateToSecond().concat(num);
    }

    public static void main(String[] args) {
        log.info(SnCreator.create());
    }
}

