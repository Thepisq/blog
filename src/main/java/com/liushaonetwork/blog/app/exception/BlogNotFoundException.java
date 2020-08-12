package com.liushaonetwork.blog.app.exception;

/**
 * @author 13496
 */
public class BlogNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -1598800491824630180L;

    public BlogNotFoundException(Long id) {
        super("找不到文章{id=[" + id + "]}");
    }

    public BlogNotFoundException(String title) {
        super("找不到文章{title=[" + title + "]}");
    }

    public BlogNotFoundException(Long blog_id, Long author_id) {
        super("找不到Blog{id=[" + blog_id + "]}的作者{id=[" + author_id + "]}");
    }

}
