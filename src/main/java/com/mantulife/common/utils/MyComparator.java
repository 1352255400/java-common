package com.mantulife.common.utils;

import java.util.Comparator;

//定义key的比较器，比较算法根据第一个参数o1,小于、等于或者大于o2分别返回负整数、0或者正整数，来决定二者存放的先后位置：返回负数则o1在前，正数则o2在前。
public class MyComparator implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        return o1.compareTo(o2);
    }
}
