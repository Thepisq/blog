package com.liushaonetwork.blog.app.controller.front;

import com.liushaonetwork.blog.app.DO.MyUserDetails;
import com.liushaonetwork.blog.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 13496
 * /user/**
 * 用户操作类
 */
@RestController
public class UserController {
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPE = new BCryptPasswordEncoder();

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    ResponseEntity<?> userRegister(@RequestParam String username, @RequestParam(value = "password") String rawpassword) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(username, new BCryptPasswordEncoder().encode(rawpassword)));
    }

    @PostMapping("/checkForUserExists")
    ResponseEntity<?> userExists(@RequestParam String username) {
        return ResponseEntity.ok(userService.findUserExists(username));
    }

    @GetMapping("/user/upgrade")
    ResponseEntity<?> upgradeUser() {
        MyUserDetails currentUser = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(userService.upgrade(currentUser) == 1 ? "充值成功，请重新<a href='/login'>登录</a>" : "操作失败");
    }

}
