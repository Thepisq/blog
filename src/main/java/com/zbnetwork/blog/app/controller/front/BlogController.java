package com.zbnetwork.blog.app.controller.front;

import com.zbnetwork.blog.app.DO.Blog;
import com.zbnetwork.blog.app.service.BlogService;
import com.zbnetwork.blog.app.utils.role.UserUd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author 13496
 * /blog/**
 * blog操作类
 */
@RestController
public class BlogController {
    private final BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/blog/{id}")
    public ResponseEntity<?> getBlog(@PathVariable int id) {
        return ResponseEntity.ok(blogService.oneBlog(id));
    }

    @PostMapping("/blog/add")
    public ResponseEntity<?> addBlog(@RequestParam String title, @RequestParam String content, @RequestParam Integer topicId) {
        Blog blog = new Blog();
        blog.setTitle(title);
        blog.setContent(content);
        blog.setTopicId(topicId);
        System.out.println("获取Blog对象:\n" + blog.toString());
        UserUd currentUser = (UserUd) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("获取User对象:\n" + currentUser.toString());
        blog.setFirstPushDate(new Date());
        blog.setLastPushDate(new Date());
        blog.setAuthorId(currentUser.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(blogService.saveBlog(blog));
    }

}
