package com.zbnetwork.blog.app.exception;

/**
 * @author 13496
 */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("找不到用户{id=[" + id + "]}");
    }

    public UserNotFoundException(String username) {
        super("找不到用户{username=[" + username + "]}");
    }

    public UserNotFoundException(Long id, String username) {
        super("找不到用户{id=[" + id + "], username=[" + username + "]}");
    }
}
