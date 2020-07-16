package com.zbnetwork.blog.app.exception;

/**
 * @author 13496
 */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(int id) {
        super("找不到用户{id=[" + id + "]}");
    }
}
