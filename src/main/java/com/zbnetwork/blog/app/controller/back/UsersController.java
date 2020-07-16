package com.zbnetwork.blog.app.controller.back;

import com.zbnetwork.blog.app.VO.UserVO;
import com.zbnetwork.blog.app.service.UserService;
import com.zbnetwork.blog.app.utils.mapstruct.UserTrans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author 13496
 * "/users/**"
 */
@RestController("/admin")
public class UsersController {
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPE = new BCryptPasswordEncoder();
    @Value("${pageSize:20}")
    private static int pageSize;
    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> one(@PathVariable int id) {
        return ResponseEntity.ok(userService.oneUser(id));
    }

    @GetMapping("/users/page={pageNum}")
    public ResponseEntity<?> all(@PathVariable int pageNum) {
        List<UserVO> userVOList = UserTrans.TRANSLATOR.listDtoToVo(userService.findAll(pageNum, pageSize));
        return ResponseEntity.ok(userVOList);
    }

}
