package com.liushaonetwork.blog.app.controller.front;

import com.liushaonetwork.blog.app.DO.MyUserDetails;
import com.liushaonetwork.blog.app.VO.FollowFrontVO;
import com.liushaonetwork.blog.app.service.FollowService;
import com.liushaonetwork.blog.app.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 13496
 */
@RestController
public class FollowController {
    private final FollowService followService;

    @Autowired
    public FollowController(FollowService followService) {
        this.followService = followService;
    }

    @GetMapping("/user/following")
    public ResponseEntity<?> getMyAllFollow() {
        MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<FollowFrontVO> following = followService.getFollowing(user.getId());
        if (following == null) {
            return ResponseEntity.ok(ResultUtil.fail("空空如也"));
        } else {
            return ResponseEntity.ok(following);
        }
    }
}
