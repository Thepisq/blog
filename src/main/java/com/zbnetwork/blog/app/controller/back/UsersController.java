package com.zbnetwork.blog.app.controller.back;

import com.zbnetwork.blog.app.DTO.UserDTO;
import com.zbnetwork.blog.app.VO.UserFrontVO;
import com.zbnetwork.blog.app.service.UserService;
import com.zbnetwork.blog.app.utils.mapstruct.UserTrans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.zbnetwork.blog.app.utils.Constants.pageSize;
/**
 * @author 13496
 * "/users/**"
 * 后台用户管理类
 */
@RestController
public class UsersController {
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPE = new BCryptPasswordEncoder();
    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> one(@PathVariable Long id) {
        return ResponseEntity.ok(userService.oneUser(id));
    }

    @GetMapping("/users/page={pageNum}")
    public ResponseEntity<?> all(@PathVariable int pageNum) {
        List<UserFrontVO> userFrontVOList = UserTrans.INSTANCE.listDto2FtVo(userService.findAll(pageNum, pageSize));
        userFrontVOList.forEach(userFrontVO -> System.out.println("{Controller} " + userFrontVO.toString()));
        return ResponseEntity.ok(userFrontVOList);
    }

    /**
     * for test
     */
    @RequestMapping("/users/{id}/pass={rawPass}")
    public ResponseEntity<?> addPass(@PathVariable Long id, @PathVariable String rawPass) {
        UserDTO user = userService.oneUser(id);
        user.setPassword(new BCryptPasswordEncoder().encode(rawPass));
        return ResponseEntity.ok(userService.updateUser(user));
    }

    @RequestMapping("/users/{id}/passis={rawPass}")
    public ResponseEntity<?> isPass(@PathVariable Long id, @PathVariable String rawPass) {
        UserDTO user = userService.oneUser(id);
        user.setPassword(new BCryptPasswordEncoder().encode(rawPass));
        return ResponseEntity.ok("isMatches: " + new BCryptPasswordEncoder().matches(rawPass, user.getPassword()));
    }

}
