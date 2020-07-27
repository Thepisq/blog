package com.zbnetwork.blog.app.controller.front;

import com.zbnetwork.blog.app.DO.Blog;
import com.zbnetwork.blog.app.DTO.BlogDTO;
import com.zbnetwork.blog.app.service.BlogService;
import com.zbnetwork.blog.app.utils.IdWorker;
import com.zbnetwork.blog.app.utils.mapstruct.BlogTrans;
import com.zbnetwork.blog.app.utils.role.UserUd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static com.zbnetwork.blog.app.utils.Constants.pageSize;
/**
 * @author 13496
 * /blog/**
 * blog操作类
 */
@RestController
public class BlogController {
    private final BlogService blogService;
    private final IdWorker idWorker = new IdWorker();

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/b/{id}")
    public ResponseEntity<?> getBlog(@PathVariable Long id) {
        BlogDTO blogDTO = blogService.oneBlog(id);
        return ResponseEntity.ok(BlogTrans.INSTANCE.dto2FtVo(blogDTO));
    }

    @GetMapping("/blog/p={pageNum}")
    public ResponseEntity<?> getBlogs(@PathVariable int pageNum) {
        List<BlogDTO> blogs = blogService.findAll(pageNum, pageSize);
        return ResponseEntity.ok(BlogTrans.INSTANCE.listDto2Vo(blogs));
    }

    @PostMapping("/blog/add")
    public ResponseEntity<?> addBlog(@RequestParam String title, @RequestParam String content, @RequestParam Integer topicId) {
        UserUd currentUser = (UserUd) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("获取User对象:\n" + currentUser.toString());

        Blog blog = new Blog();
        blog.setId(idWorker.nextId());
        blog.setTitle(title);
        blog.setContent(content);
        blog.setTopicId(topicId);
        blog.setFirstPushDate(LocalDateTime.now());
        blog.setLastPushDate(LocalDateTime.now());
        blog.setAuthorId(currentUser.getId());

        System.out.println("获取Blog对象:\n" + blog.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(blogService.saveBlog(blog));
    }

}
