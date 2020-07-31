package com.zbnetwork.blog.app.controller.front;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 13496
 */
@Slf4j
@RestController
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {
    private final String NO_AUTHORIZE_ERROR_MESSAGE = "Forbidden";

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @RequestMapping("/error")
    public ResponseEntity<?> exception(HttpServletRequest request, HttpServletResponse response) {
        String msg = "。";
        if (NO_AUTHORIZE_ERROR_MESSAGE.equals(request.getAttribute("javax.servlet.error.message"))) {
            msg = "<a href='/user/upgrade'>充值权限</a>";
        }
        return ResponseEntity.status(response.getStatus()).body("error: \n" + request.getAttribute("javax.servlet.error.message") + "\n" + msg);
    }

    @Override
    public String getErrorPath() {
        return "error/error";
    }
}
