package com.liushaonetwork.blog.app.controller.front;

import com.liushaonetwork.blog.app.DO.MyUserDetails;
import com.liushaonetwork.blog.app.DTO.BlogDTO;
import com.liushaonetwork.blog.app.service.BlogService;
import com.liushaonetwork.blog.app.service.SysTopicService;
import com.liushaonetwork.blog.app.utils.ResultUtil;
import com.liushaonetwork.blog.app.utils.mapstruct.BlogTrans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.liushaonetwork.blog.app.utils.Constants.pageSize;
/**
 * @author 13496
 * /blog/**
 * blog操作类
 */
@RestController
public class BlogController {
    private final BlogService blogService;
    private final SysTopicService topicService;

    @Autowired
    public BlogController(BlogService blogService, SysTopicService topicService) {
        this.blogService = blogService;
        this.topicService = topicService;
    }

    @GetMapping("/blog/{id}")
    public ResponseEntity<?> getBlog(@PathVariable Long id) {
        BlogDTO blogDTO = blogService.oneBlog(id);
        String topicName = topicService.getById(blogDTO.getTopicId()).getTopicName();
        blogDTO.setTopicName(topicName);
        return ResponseEntity.ok(BlogTrans.INSTANCE.dto2FtVo(blogDTO));
    }

    @GetMapping("/blog/p={pageNum}")
    public ResponseEntity<?> getBlogs(@PathVariable int pageNum) {
        List<BlogDTO> blogs = blogService.findAll(pageNum, pageSize);
        for (BlogDTO blog : blogs) {
            String topicName = topicService.getById(blog.getTopicId()).getTopicName();
            blog.setTopicName(topicName);
        }
        return ResponseEntity.ok(BlogTrans.INSTANCE.listDto2Vo(blogs));
    }

    @PostMapping("/blog/add")
    public ResponseEntity<?> addBlog(@RequestParam String title, @RequestParam String content, @RequestParam(defaultValue = "1") Integer topicId) {
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
        MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
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

    @PostMapping("/b/{id}/like={bool}")
    public ResponseEntity<?> addLikes(@PathVariable Long id, @PathVariable boolean bool) {
        return null;
    }
}
