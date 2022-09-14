package com.mantulife.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Author W_wang
 * Description 时间工具
 * Date 2019-07-29 10:14
 **/
@Slf4j
public class TimeUtil {

    public static final String DATA_FORMAT_YMD_HMS = "yyyy-MM-dd HH:mm:ss";
    public static final String DATA_FORMAT_YMD_HM = "yyyy-MM-dd HH:mm";
    public static final String DATA_FORMAT_YMD = "yyyy-MM-dd";
    public static final String DATA_FORMAT_YM = "yyyy-MM";

    /**
     * 获取 DATA_FORMAT_YMD_HMS 的日期
     *
     * @return  return return
     */
    public static String getDateToSecond() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATA_FORMAT_YMD_HMS));
    }

    /**
     * 获取 DATA_FORMAT_YMD_HMS 的日期
     *
     * @return  return return
     */
    public static String getDateToMinute() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATA_FORMAT_YMD_HM));
    }

    /**
     * 获取 DATA_FORMAT_YMD_HMS 的日期
     *
     * @return  return return
     */
    public static String getDate() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATA_FORMAT_YMD));
    }

    /**
     * 获取 DATA_FORMAT_YMD_HMS 的日期
     *
     * @return  return return
     */
    public static String getMonth() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATA_FORMAT_YM));
    }

    public static void main(String[] args) {
        log.info(TimeUtil.getDate());
    }
}
