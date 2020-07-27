package com.zbnetwork.blog.app.utils;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * @author 13496
 */
public class TimeConvertUtil {
    // duration < 60s : '刚刚'
    // duration < 60min : 'xx分钟前'
    // duration < 1hour : 'xx小时前'
    // duration < 1day :'xx天前'
    public static String getDurationBetweenNow(LocalDateTime time) {
        // LocalDateTime.now() 在 time 之后，结果为正。否则为负。
        Duration dur = Duration.between(time, LocalDateTime.now());
        System.out.println(" Millis: " + dur.toMillis() +
                " Minutes: " + dur.toMinutes() +
                " Hours: " + dur.toHours() +
                " Days: " + dur.toDays());
        if (dur.toDays() >= 1) {
            return dur.toDays() + "天前";
        } else if (dur.toHours() >= 1) {
            return dur.toHours() + "小时前";
        } else if (dur.toMinutes() >= 1) {
            return dur.toMinutes() + "分钟前";
        } else {
            return "刚刚";
        }
    }
}
