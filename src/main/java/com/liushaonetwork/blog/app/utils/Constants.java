package com.liushaonetwork.blog.app.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 13496
 * 从yml取配置信息的静态变量类
 */
@Component
@ConfigurationProperties(prefix = "blog.constants")
public class Constants {
    //页面显示内容量的最大值
    public static int pageSize;
    //页面显示的博客内容的字数
    public static int indexBlogContentTextSize;
    //token保存到header里的名字
    public static String tokenInHeader;
    //上面一个的前缀
    public static final String TOKEN_PREFIX = "Bearer ";
    //自定义jwt字段
    public static final String TOKEN_PAYLOAD_USERNAME = "username";
    public static final String TOKEN_PAYLOAD_ROLES = "roles";
    //当用户在线(带token访问网站)时，每隔xx秒刷新当前token
    public static final int TOKEN_REFRESH_INTERVAL = 300;

    //非静态的set方法才能从获取yml中的值
    public void setPageSize(int pageSize) {
        Constants.pageSize = pageSize;
    }

    public void setIndexBlogContentTextSize(int textSize) {
        Constants.indexBlogContentTextSize = textSize;
    }

    public void setTokenInHeader(String str) {
        Constants.tokenInHeader = str;
    }
}
