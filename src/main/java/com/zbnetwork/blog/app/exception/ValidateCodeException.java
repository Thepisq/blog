package com.zbnetwork.blog.app.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author 13496
 */
public class ValidateCodeException extends AuthenticationException {
    private static final long serialVersionUID = 1L;

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
