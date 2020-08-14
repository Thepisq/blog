package com.liushaonetwork.blog.app.controller.front;

import com.liushaonetwork.blog.app.DO.MyUserDetails;
import com.liushaonetwork.blog.app.DTO.BlogDTO;
import com.liushaonetwork.blog.app.service.BlogService;
import com.liushaonetwork.blog.app.service.SysTopicService;
import com.liushaonetwork.blog.app.utils.ResultUtil;
import com.liushaonetwork.blog.app.utils.mapstruct.BlogTrans;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

import static com.liushaonetwork.blog.app.utils.Constants.pageSize;
/**
 * @author 13496
 * /blog/**
 * blog操作类
 */
@EnableAsync
@Slf4j
@RestController
public class BlogController {
    private final BlogService blogService;
    private final SysTopicService topicService;
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    public BlogController(BlogService blogService, SysTopicService topicService) {
        this.blogService = blogService;
        this.topicService = topicService;
    }

    @GetMapping("/blog/{id}")
    public ResponseEntity<?> getBlog(@PathVariable Long id, HttpServletRequest request) {
        BlogDTO blogDTO = blogService.oneBlog(id);
        String topicName = topicService.getById(blogDTO.getTopicId()).getTopicName();
        blogDTO.setTopicName(topicName);

        //判断当前用户是否点过赞
        blogDTO.setIsCurrentUserLikes(isCurrentUserLike(blogDTO.getId()));

        //增加点击量
        blogService.blogClicks(id, request, SecurityContextHolder.getContext().getAuthentication());

        return ResponseEntity.ok(BlogTrans.INSTANCE.dto2FtVo(blogDTO));
    }

    @GetMapping("/blog/p={pageNum}")
    public ResponseEntity<?> getBlogs(@PathVariable int pageNum) {
        List<BlogDTO> blogs = blogService.findAll(pageNum, pageSize);
        for (BlogDTO blog : blogs) {
            String topicName = topicService.getById(blog.getTopicId()).getTopicName();
            blog.setTopicName(topicName);
            blog.setIsCurrentUserLikes(isCurrentUserLike(blog.getId()));
            log.info("获取首页数据时，发现了当前用户给{" + blog.getTitle() + "}" + (blog.getIsCurrentUserLikes() ? "点了赞" : "没点赞") + blog.getIsCurrentUserLikes());
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

    /**
     * 点赞行为
     */
    @PostMapping("/blog/{id}/likes")
    public ResponseEntity<?> addLikes(@PathVariable Long id) {
        MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        blogService.blogLikes(id, user.getId());
        return ResponseEntity.ok(ResultUtil.success());
    }

    private Boolean isCurrentUserLike(Long id) {
        if (!SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"))) {
            return false;
        }
        MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean bool = false;
        try {
            String REDIS_KEY = "blogLikes:" + user.getId() + "_likes_" + id;
            log.info("当前用户点赞key是否存在: " + redisTemplate.hasKey(REDIS_KEY) + "\n key:" + REDIS_KEY);
            bool = redisTemplate.hasKey(REDIS_KEY);
        } catch (Exception e) {
            bool = false;
        }
        log.info("最终结果: " + bool);
        return bool;
    }
}
