package com.zbnetwork.blog.app.service.impl;

import com.github.pagehelper.PageHelper;
import com.zbnetwork.blog.app.DO.Blog;
import com.zbnetwork.blog.app.DTO.BlogDTO;
import com.zbnetwork.blog.app.DTO.UserDTO;
import com.zbnetwork.blog.app.mapper.BlogDynamicSqlSupport;
import com.zbnetwork.blog.app.mapper.BlogMapper;
import com.zbnetwork.blog.app.service.BlogService;
import com.zbnetwork.blog.app.service.UserService;
import com.zbnetwork.blog.app.utils.IdWorker;
import com.zbnetwork.blog.app.utils.mapstruct.BlogTrans;
import com.zbnetwork.blog.app.utils.userdetails.UserUd;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

/**
 * @author 13496
 */
@Service
public class BlogServiceImpl implements BlogService {
    private final IdWorker idWorker = new IdWorker();
    private final BlogMapper blogMapper;
    private final UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    public BlogServiceImpl(BlogMapper blogMapper, UserService userService) {
        this.blogMapper = blogMapper;
        this.userService = userService;
    }

    @Override
    public BlogDTO oneBlog(String title) {
        Blog blog = blogMapper.selectOne(b -> b.where(BlogDynamicSqlSupport.title, isEqualTo(title)))
                .orElseThrow(() -> new RuntimeException("找不到Blog{title=[" + title + "]}"));
        UserDTO user = userService.oneUser(blog.getAuthorId());
        if (null == user) {
            throw new RuntimeException("找不到Blog{id=[" + blog.getId() + "]}的作者{id=[" + blog.getAuthorId() + "]}");
        }
        return BlogTrans.INSTANCE.do2DtoWithUser(blog, user);
    }

    @Override
    public BlogDTO oneBlog(Long id) {
        Blog blog = (Blog) redisTemplate.opsForValue().get("blog_"+id);
        System.out.println("--------------------------------------走缓存");
        if (blog == null){
            System.out.println("--------------------------------------走数据库");
            blog = blogMapper.selectOne(c -> c.where(BlogDynamicSqlSupport.id, isEqualTo(id)))
                    .orElseThrow(() -> new RuntimeException("找不到id为{" + id + "}的blog"));
            redisTemplate.opsForValue().set("blog_"+id, blog);
        }
        UserDTO user = (UserDTO) redisTemplate.opsForValue().get("user_"+blog.getAuthorId());
        if (null == user) {
            user = userService.oneUser(blog.getAuthorId());
            if (null == user) {
                throw new RuntimeException("找不到Blog{id=[" + blog.getId() + "]}的作者{id=[" + blog.getAuthorId() + "]}");
            }
            redisTemplate.opsForValue().set("user_"+user.getId(), user);
        }
        return BlogTrans.INSTANCE.do2DtoWithUser(blog, user);
    }

    @Override
    public List<BlogDTO> findAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Blog> blogList = new ArrayList<>(blogMapper.select(c -> c));
        System.out.println("[BlogService.findAll]-->: 一共查出了{" + blogList.size() + "}条数据, pageSize: " + pageSize);
        List<UserDTO> userList = new ArrayList<>(blogList.size());
        blogList.forEach(blog -> userList.add(userService.oneUser(blog.getAuthorId())));
        return BlogTrans.INSTANCE.listDo2DtoWithUser(blogList, userList);
    }

    @Override
    public int saveBlog(Blog blog) {
        return blogMapper.insertSelective(blog);
    }

    @Override
    public int saveBlog(String title, String content, Integer topicId) {
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

        return blogMapper.insertSelective(blog);
    }

    @Override
    public int updateBlog(BlogDTO blogDTO) {
        blogDTO.setLastPushDate(LocalDateTime.now());
        return blogMapper.updateByPrimaryKeySelective(BlogTrans.INSTANCE.dto2Do(blogDTO));
    }
}
