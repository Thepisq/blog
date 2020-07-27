package com.zbnetwork.blog.app.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 13496
 */
@Component
@ConfigurationProperties(prefix = "blog.constants")
public class Constants {
    //页面显示内容量的最大值
    public static int pageSize;
    //页面显示的博客内容的字数
    public static int indexBlogContentTextSize;

    //静态变量通过@ConfigurationProperties获取需要非静态的set方法
    public void setPageSize(int pageSize) {
        Constants.pageSize = pageSize;
    }

    public void setIndexBlogContentTextSize(int textSize) {
        Constants.indexBlogContentTextSize = textSize;
    }
}
