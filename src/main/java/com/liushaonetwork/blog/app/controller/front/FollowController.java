package com.liushaonetwork.blog.app.controller.front;

import com.liushaonetwork.blog.app.DO.MyUserDetails;
import com.liushaonetwork.blog.app.VO.FollowFrontVO;
import com.liushaonetwork.blog.app.service.FollowService;
import com.liushaonetwork.blog.app.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
            return ResponseEntity.ok(ResultUtil.successWith(following));
        }
    }

    @PostMapping("/user/following/{id}")
    public ResponseEntity<?> addNewFollowing(@PathVariable Long id) {
        MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user.getId().equals(id)) {
            return ResponseEntity.ok(ResultUtil.fail("不能关注自己哦"));
        } else {
            int result = followService.toggleFollowing(user.getId(), id);
            int result2 = followService.toggleFollowers(id, user.getId());
            System.out.println("添加TA到关注列表结果: " + result);
            System.out.println("添加到TA粉丝列表: " + result2);
            if (result == 1) {
                return ResponseEntity.ok(ResultUtil.success("关注成功", result));
            } else if (result == 0) {
                return ResponseEntity.ok(ResultUtil.fail("操作失败", result));
            } else if (result == -1) {
                return ResponseEntity.ok(ResultUtil.success("取关成功", result));
            } else {
                return ResponseEntity.ok(ResultUtil.fail("error", result));
            }
        }
    }

    @GetMapping("/user/following/is={id}")
    public ResponseEntity<?> isCurrentUserFollowing(@PathVariable Long id) {
        MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(ResultUtil.of(followService.isCurrentUserFollowing(user.getId(), id), null, 0, null));
    }
}
