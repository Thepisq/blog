package com.liushaonetwork.blog.app.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;

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
    public static String tokenPrefix;
    //自定义的token内容(key-value-> key)
    public static String tokenPayloadUsername;
    public static String tokenPayloadRoles;
    //当用户在线(带token访问网站)时，每隔xx秒刷新当前token
    public static int tokenRefreshInterval;

    //token过期时间
    public static Duration tokenExpireIn;
    //token密钥
    public static String tokenSecretKey;

    //通过非静态的set方法注入
    public void setPageSize(int pageSize) {
        Constants.pageSize = pageSize;
    }

    public void setIndexBlogContentTextSize(int indexBlogContentTextSize) {
        Constants.indexBlogContentTextSize = indexBlogContentTextSize;
    }

    public void setTokenInHeader(String tokenInHeader) {
        Constants.tokenInHeader = tokenInHeader;
    }

    public void setTokenExpireIn(Duration tokenExpireIn) {
        Constants.tokenExpireIn = tokenExpireIn;
    }

    public void setTokenSecretKey(String tokenSecretKey) {
        Constants.tokenSecretKey = tokenSecretKey;
    }

    public void setTokenPrefix(String tokenPrefix) {
        Constants.tokenPrefix = tokenPrefix;
    }

    public void setTokenPayloadUsername(String tokenPayloadUsername) {
        Constants.tokenPayloadUsername = tokenPayloadUsername;
    }

    public void setTokenPayloadRoles(String tokenPayloadRoles) {
        Constants.tokenPayloadRoles = tokenPayloadRoles;
    }
}
