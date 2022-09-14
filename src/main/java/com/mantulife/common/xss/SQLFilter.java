package com.mantulife.common.xss;

import com.mantulife.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * Author W_wang
 * Description SQL过滤
 * Date 2019-07-29 10:14
 **/
@Slf4j
public class SQLFilter {

    private SQLFilter() {
    }

    /**
     * SQL注入过滤
     *
     * @param str 待验证的字符串
     * @return  return return
     */
    public static String sqlInject(String str) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        //去掉'|"|;|\字符
        str = StringUtils.replace(str, "'", "");
        str = StringUtils.replace(str, "\"", "");
        str = StringUtils.replace(str, ";", "");
        str = StringUtils.replace(str, "\\", "");

        //转换成小写
        str = str.toLowerCase();

        //非法字符
        String[] keywords = {"master", "truncate", "insert", "select", "delete", "update", "declare", "alter", "drop"};

        //判断是否包含非法字符
        for (String keyword : keywords) {
            if (str.indexOf(keyword) != -1) {
                throw new BusinessException("sql包含非法字符");
            }
        }

        return str;
    }
}
