package com.zbnetwork.blog.app.controller.front;

import com.zbnetwork.blog.app.DO.User;
import com.zbnetwork.blog.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    ResponseEntity<?> userRegister(@RequestParam String username, @RequestParam String password) {
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(bCryptPE.encode(password));
        newUser.setRoles("USER");
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(newUser));
    }

    @PostMapping("/checkForUserExists")
    ResponseEntity<?> userExists(@RequestParam String username) {
        return ResponseEntity.ok(userService.findUserExists(username));
    }

}
