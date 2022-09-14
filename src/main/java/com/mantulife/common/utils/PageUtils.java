package com.mantulife.common.utils;

import java.util.HashMap;
import java.util.Map;


/**
 * author W_wang
 * version V1.0
 * remark 分页工具类
 * email 1352255400@qq.com
 * date 2020/8/4 17:22
 */
public class PageUtils {

    private PageUtils() {
    }

    //自定义分页W_wang
    public static Map<String, Object> pageNation(Long total, Long page, Long limit) {
        limit = limit != null && limit > 0 ? limit : 1;
        Map<String, Object> map = new HashMap<>();
        map.put("pageNumber", page);
        map.put("pageSize", limit);
        map.put("totalRecords", total);
        map.put("totalPages", (int) Math.ceil((double) total / limit));
        return map;
    }
}
