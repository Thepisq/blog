package com.zbnetwork.blog.app.controller.front;

import com.zbnetwork.blog.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;

/**
 * @author 13496
 * /user/**
 * 用户操作类
 */
@Controller
public class UserController {
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPE = new BCryptPasswordEncoder();
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


}
