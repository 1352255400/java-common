package com.mantulife.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * author W_wang
 * version V1.0
 * remark 生成随机数
 * email 1352255400@qq.com
 * date 2020/8/4 17:22
 * Copyright www.dx.com
 */
@Slf4j
public class RandUtil {

    private RandUtil() {
    }

    private static final Random random = new Random();

    private static final DecimalFormat fourdf = new DecimalFormat("0000");

    private static final DecimalFormat sixdf = new DecimalFormat("000000");

    public static String getFourBitRandom() {
        return fourdf.format(random.nextInt(10000));
    }

    public static String getSixBitRandom() {
        return sixdf.format(random.nextInt(1000000));
    }

    /**
     * 给定数组，抽取n个数据
     *
     * @param list list
     * @param n n
     * @return  return return
     */
    public static ArrayList getRandom(List list, int n) {
        HashMap<Object, Object> hashMap = new HashMap<>();
        // 生成随机数字并存入HashMap
        for (int i = 0; i < list.size(); i++) {
            int number = random.nextInt(100) + 1;
            hashMap.put(number, i);
        }

        // 从HashMap导入数组
        Object[] robjs = hashMap.values().toArray();
        ArrayList r = new ArrayList();
        // 遍历数组并打印数据
        for (int i = 0; i < n; i++) {
            r.add(list.get((int) robjs[i]));
        }
        return r;
    }

    public static void main(String[] args) {
        log.info(RandUtil.getFourBitRandom());
    }
}
