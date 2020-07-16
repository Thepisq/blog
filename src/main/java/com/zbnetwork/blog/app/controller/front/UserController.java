package com.zbnetwork.blog.app.controller.front;

import com.zbnetwork.blog.app.VO.UserVO;
import com.zbnetwork.blog.app.constants.UserActivations;
import com.zbnetwork.blog.app.service.UserService;
import com.zbnetwork.blog.app.utils.mapstruct.UserTrans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author 13496
 */
@Controller
public class UserController {
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPE = new BCryptPasswordEncoder();
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserVO newUser) {
        newUser.setPassword(bCryptPE.encode(newUser.getPassword()));
        newUser.setId(userService.saveUser(UserTrans.TRANSLATOR.voToDto(newUser)));
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

}
