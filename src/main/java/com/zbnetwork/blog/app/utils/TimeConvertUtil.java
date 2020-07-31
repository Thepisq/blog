package com.zbnetwork.blog.app.utils;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * @author 13496
 * TimeConvertUtil:
 * 一些与时间格式有关的工具类
 */
public class TimeConvertUtil {
    /**
     * 比较指定时间与现在时间，返回日期或是时间差
     */
    public static String getDurationBetweenNow(LocalDateTime timeBeforeNow) {
        if (timeBeforeNow.getYear() < LocalDateTime.now().getYear()) {
            return timeBeforeNow.getYear() + "-" + timeBeforeNow.getMonth().getValue() + "=" + timeBeforeNow.getDayOfMonth();
        }
        Duration duration = Duration.between(timeBeforeNow, LocalDateTime.now());
        if (duration.toDays() >= 1) {
            return timeBeforeNow.getMonth().getValue() + "-" + timeBeforeNow.getDayOfMonth();
        } else if (duration.toHours() >= 1) {
            return duration.toHours() + "小时前";
        } else if (duration.toMinutes() >= 1) {
            return duration.toMinutes() + "分钟前";
        } else {
            return "刚刚";
        }
    }
}
