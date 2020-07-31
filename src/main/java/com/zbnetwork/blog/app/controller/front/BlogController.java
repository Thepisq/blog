package com.zbnetwork.blog.app.controller.front;

import com.zbnetwork.blog.app.DTO.BlogDTO;
import com.zbnetwork.blog.app.service.BlogService;
import com.zbnetwork.blog.app.utils.IdWorker;
import com.zbnetwork.blog.app.utils.ResultUtil;
import com.zbnetwork.blog.app.utils.mapstruct.BlogTrans;
import com.zbnetwork.blog.app.utils.userdetails.UserUd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    private final String ROLE_BLOG = "ROLE_BLOG";

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
        Map<String, Object> result;
        if (blogService.saveBlog(title, content, topicId) == 1) {
            result = ResultUtil.success();
        } else {
            result = ResultUtil.fail();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/blog/{id}/update")
    public ResponseEntity<?> updateBlog(@PathVariable Long id, @RequestParam String title, @RequestParam String content, @RequestParam Integer topicId) {
        UserUd user = (UserUd) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        BlogDTO updateBlog = blogService.oneBlog(id);

        Map<String, Object> result;
        if (updateBlog.getAuthorId().equals(user.getId())) {
            updateBlog.setTitle(title);
            updateBlog.setContent(content);
            updateBlog.setTopicId(topicId);
            if (blogService.updateBlog(updateBlog) == 1) {
                result = ResultUtil.success();
            } else {
                result = ResultUtil.fail();
            }
        } else {
            result = ResultUtil.fail("您无权修改此文章");
        }

        return ResponseEntity.ok(result);
    }
}
