package com.zbnetwork.blog.app.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 13496
 */
@Component
@ConfigurationProperties(prefix = "blog.constants")
public class Constants {

    public static int pageSize;

    //静态变量通过@ConfigurationProperties获取需要非静态的set方法
    public void setPageSize(int pageSize) {
        Constants.pageSize = pageSize;
    }
}
