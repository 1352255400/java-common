package com.mantulife.common.utils;

import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * author W_wang
 * version V1.0
 * remark 日期工具
 * email 1352255400@qq.com
 * date 2020/8/4 17:22
 */
@Slf4j
public class DateUtils {

    private DateUtils() {
    }

    public final static String DATE = "yyyy-MM-dd";
    public final static String DATE_SLASH = "yyyy/MM/dd";
    public final static String DATE_CHINESE = "yyyy年MM月dd日";

    public final static String DATE_TIME = "yyyy-MM-dd HH:mm:ss";
    public final static String DATE_TIME_HOURS = "yyyy-MM-dd HH";
    public final static String DATE_TIME_MINUTES = "yyyy-MM-dd HH:mm";
    public final static String DATE_TIME_SLASH = "yyyy/MM/dd HH:mm:ss";
    public final static String DATE_TIME_CHINESE = "yyyy年MM月dd日 HH时mm分ss秒";

    public final static String DATE_TIME_MILLION = "yyyy-MM-dd HH:mm:ss:SSS";

    public final static String YEAR = "yyyy";
    public final static String YEAR_TO_MONTH = "yyyyMM";
    public final static String YEAR_TO_DATE = "yyyyMMdd";
    public final static String YEAR_TO_SECOND = "yyyyMMddHHmmss";
    public final static String YEAR_TO_MILLION = "yyyyMMddHHmmssSSS";

    public final static String ZERO_TIME = " 00:00:00";
    public final static String ZERO_TIME_MILLION = " 00:00:00:000";
    public final static String ZERO_TIME_WITHOUT_HOURS = ":00:00";
    public final static String ZERO_TIME_WITHOUT_MINUTES = ":00";


    /**
     * 字符串转成日期、时间格式
     *
     * @param dateString 日期字符串
     * @param pattern    格式化类型，默认为yyyy-MM-dd，其它使用DateUtils.xxx
     * @return  return
     * @throws ParseException throws
     */
    public static Date parse(String dateString, String pattern) throws ParseException {
        if (StringUtils.isBlank(dateString)) {
            return null;
        } else {
            dateString = dateString.trim();
            if (StringUtils.isBlank(pattern)) {
                pattern = DATE;
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            return simpleDateFormat.parse(dateString);
        }
    }

    /**
     * 字符串转成日期（yyyy-MM-dd）格式
     *
     * @param dateString 日期字符串
     * @return  return Date
     * @throws ParseException throws
     */
    public static Date parseDate(String dateString) throws ParseException {
        return parse(dateString, null);
    }

    /**
     * 字符串转成时间（yyyy-MM-dd HH:mm:ss）格式
     *
     * @param dateString 日期字符串
     * @return  return
     * @throws ParseException throws
     */
    public static Date parseDateTime(String dateString) throws ParseException {
        if (StringUtils.isBlank(dateString)) {
            return null;
        } else {
            dateString = dateString.trim();
            if (dateString.length() == DATE_TIME_HOURS.length()) {
                return parse(dateString, DATE_TIME_HOURS);
            } else if (dateString.length() == DATE_TIME_MINUTES.length()) {
                return parse(dateString, DATE_TIME_MINUTES);
            } else if (dateString.length() == DATE_TIME_MILLION.length()) {
                return parse(dateString, DATE_TIME_MILLION);
            } else {
                return parse(dateString, DATE_TIME);
            }
        }
    }

    /**
     * 时间转字符串
     *
     * @param date    时间
     * @param pattern 格式化类型，默认为yyyy-MM-dd HH:mm:ss，其它使用DateUtils.xxx
     * @return  return
     */
    public static String format(Date date, String pattern) {
        if (date == null) {
            return "";
        } else {
            if (StringUtils.isBlank(pattern)) {
                pattern = DATE_TIME;
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            return simpleDateFormat.format(date);
        }
    }

    /**
     * 时间转日期字符串（yyyy-MM-dd）
     *
     * @param date 时间
     * @return  return
     */
    public static String formatDate(Date date) {
        return format(date, DATE);
    }

    /**
     * 时间转日期字符串（yyyy-MM-dd HH:mm:ss）
     *
     * @param date 时间
     * @return  return
     */
    public static String formatDateTime(Date date) {
        return format(date, null);
    }

    /**
     * 将日期格式转换成时间（yyyy-MM-dd HH:mm:ss）格式
     *
     * @param dateString 日期字符串
     * @return  return
     */
    public static String dateToDateTime(String dateString) {
        if (StringUtils.isBlank(dateString)) {
            return "";
        } else {
            dateString = dateString.trim();
            if (dateString.length() == DATE.length()) {
                return dateString + ZERO_TIME;
            } else if (dateString.length() == DATE_TIME_HOURS.length()) {
                return dateString + ZERO_TIME_WITHOUT_HOURS;
            } else if (dateString.length() == DATE_TIME_MINUTES.length()) {
                return dateString + ZERO_TIME_WITHOUT_MINUTES;
            } else if (dateString.length() == DATE_TIME_MILLION.length()) {
                return dateString.substring(0, DATE_TIME.length());
            } else {
                return dateString;
            }
        }
    }

    /**
     * 将日期格式转换成时间（时分秒毫秒）格式
     *
     * @param dateString 日期字符串
     * @return  return
     */
    public static String dateToDateTimeMillion(String dateString) {
        if (StringUtils.isBlank(dateString)) {
            return "";
        } else {
            dateString = dateString.trim();
            return dateString + ZERO_TIME_MILLION;
        }
    }


    /**
     * 将时间字（yyyy-MM-dd HH:mm:ss）符串转换成日期（yyyy-MM-dd）格式
     *
     * @param dateTimeString 时间字符串
     * @return  return String
     */
    public static String dateTimeToDate(String dateTimeString) {
        if (StringUtils.isBlank(dateTimeString)) {
            return "";
        } else {
            dateTimeString = dateTimeString.trim();
            if (dateTimeString.length() >= DATE.length()) {
                return dateTimeString.substring(0, DATE.length());
            } else {
                return dateTimeString;
            }
        }
    }

    /**
     * 将时间（yyyy-MM-dd HH:mm:ss）转换成日期（yyyy-MM-dd）
     *
     * @param dateTime 时间
     * @return  return Date
     * @throws ParseException throws
     */
    public static Date dateTimeToDate(Date dateTime) throws ParseException {
        if (dateTime == null) {
            return null;
        } else {
            return parseDate(formatDate(dateTime));
        }
    }

    /**
     * 获取当前时间（yyyy-MM-dd HH:mm:ss）
     *
     * @return  return Date
     */
    public static Date now() {
        return new Date();
    }

    /**
     * 获取当前时间（yyyy-MM-dd HH:mm:ss）
     *
     * @return  return Date
     */
    public static Date dateTime() {
        return new Date();
    }

    /**
     * 获取当前时间（yyyy-MM-dd HH:mm:ss）
     *
     * @return  return Date
     */
    public static Date getDateTime() {
        return dateTime();
    }

    /**
     * 获取当前日期（yyyy-MM-dd）
     *
     * @return  return Date
     * @throws ParseException throws
     */
    public static Date date() throws ParseException {
        return dateTimeToDate(new Date());
    }

    /**
     * 获取当前日期（yyyy-MM-dd）
     *
     * @return  return Date
     * @throws ParseException throws
     */
    public static Date getDate() throws ParseException {
        return date();
    }

    /**
     * 日期加减天数
     *
     * @param date 日期，为空时默认当前时间，包括时分秒
     * @param days 加减的天数
     * @return  return
     * @throws ParseException throws
     */
    public static Date dateAdd(Date date, int days) throws ParseException {
        if (date == null) {
            date = new Date();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    /**
     *  日期加减分钟
     * @param date 日期，为空时默认当前时间，包括时分秒
     * @param amount 加减的分钟
     * @return  return
     * @throws ParseException throws
     */
    public static Date subtractTime(Date date, int amount) throws ParseException {
        if (date == null) {
            date = new Date();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, amount);
        return cal.getTime();
    }

    /**
     * 日期加减多少月
     *
     * @param date   日期，为空时默认当前时间，包括时分秒
     * @param months 加减的月数
     * @return  return
     * @throws ParseException throws
     */
    public static Date monthAdd(Date date, int months) throws ParseException {
        if (date == null) {
            date = new Date();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, months);
        return cal.getTime();
    }


    /**
     * 时间比较
     * <p>如果date大于compareDate返回1，小于返回-1，相等返回0</p>
     *
     * @param date date
     * @return  return
     * @throws ParseException throws
     */
    public static int dateCompare(Date date) throws ParseException {
        Calendar cal = Calendar.getInstance();
        Calendar compareCal = Calendar.getInstance();
        cal.setTime(date);
        compareCal.setTime(date);
        return cal.compareTo(compareCal);
    }


    /**
     * 获取两个日期相差的天数，不包含今天
     *
     * @param startDate date
     * @param endDate date
     * @return  return
     * @throws ParseException throws
     */
    public static int dateBetween(Date startDate, Date endDate) throws ParseException {
        if (ObjectUtil.isEmpty(startDate)
                || startDate == null
                || endDate == null
                || ObjectUtil.isEmpty(endDate)) {
            return 0;
        }
        Date dateStart = parse(format(startDate, DATE), DATE);
        Date dateEnd = parse(format(endDate, DATE), DATE);
        return (int) ((dateEnd.getTime() - dateStart.getTime()) / 1000 / 60 / 60 / 24);
    }


    /**
     * 获取两个日期相差的天数，包含今天
     *
     * @param startDate date
     * @param endDate date
     * @return  return
     * @throws ParseException throws
     */
    public static int dateBetweenIncludeToday(Date startDate, Date endDate) throws ParseException {
        return dateBetween(startDate, endDate) + 1;
    }
}
