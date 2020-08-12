package com.liushaonetwork.blog.app.service.impl;

import com.github.pagehelper.PageHelper;
import com.liushaonetwork.blog.app.DO.Blog;
import com.liushaonetwork.blog.app.DO.MyUserDetails;
import com.liushaonetwork.blog.app.DTO.BlogDTO;
import com.liushaonetwork.blog.app.DTO.UserDTO;
import com.liushaonetwork.blog.app.exception.BlogNotFoundException;
import com.liushaonetwork.blog.app.mapper.BlogDynamicSqlSupport;
import com.liushaonetwork.blog.app.mapper.BlogMapper;
import com.liushaonetwork.blog.app.service.BlogService;
import com.liushaonetwork.blog.app.service.UserService;
import com.liushaonetwork.blog.app.utils.HttpUtil;
import com.liushaonetwork.blog.app.utils.IdWorker;
import com.liushaonetwork.blog.app.utils.mapstruct.BlogTrans;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

/**
 * @author 13496
 */
@Slf4j
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
                .orElseThrow(() -> new BlogNotFoundException(title));
        UserDTO user = userService.oneUser(blog.getAuthorId());
        if (null == user) {
            throw new BlogNotFoundException(blog.getId(), blog.getAuthorId());
        }
        return BlogTrans.INSTANCE.do2DtoWithUser(blog, user);
    }

    @Override
    public BlogDTO oneBlog(Long id) {
        Blog blog = (Blog) redisTemplate.opsForValue().get("blog:blog_" + id);
        System.out.println("--------------------------------------走缓存");
        if (blog == null) {
            System.out.println("--------------------------------------走数据库");
            blog = blogMapper.selectOne(c -> c.where(BlogDynamicSqlSupport.id, isEqualTo(id)))
                    .orElseThrow(() -> new BlogNotFoundException(id));
            try {
                redisTemplate.opsForValue().set("blog:blog_" + id, blog);
            } catch (Exception e) {
                log.info("缓存文章内容时出错");
            }
        }
        UserDTO user = (UserDTO) redisTemplate.opsForValue().get("user:user_" + blog.getAuthorId());
        if (null == user) {
            user = userService.oneUser(blog.getAuthorId());
            if (null == user) {
                throw new BlogNotFoundException(blog.getId(), blog.getAuthorId());
            }
            try {
                redisTemplate.opsForValue().set("user:user_" + user.getId(), user);
            } catch (Exception e) {
                log.info("缓存用户信息时出错");
            }
        }
        BlogDTO blogDTO = BlogTrans.INSTANCE.do2DtoWithUser(blog, user);
        blogDTO.setIsCurrentUserLikes(isCurrentUserLike(blogDTO.getId()));
        return blogDTO;
    }

    private Boolean isCurrentUserLike(Long id) {
        if ("anonymousUser" == SecurityContextHolder.getContext().getAuthentication().getName()) {
            return false;
        }
        MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            String REDIS_KEY = "blogLikes:" + user.getId() + "_likes_" + id;
            return (Boolean) redisTemplate.opsForValue().get(REDIS_KEY);
        } catch (Exception e) {
            return false;
        }
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
        MyUserDetails currentUser = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
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

    @Override
    public int blogLikes(Long id, Long userId) {
        Blog blog = blogMapper.selectByPrimaryKey(id).orElseThrow(() -> new BlogNotFoundException(id));
        int likes = blog.getLikes();

        String REDIS_KEY = "blogLikes:" + userId + "_likes_" + id;
        if (null == redisTemplate.opsForValue().get(REDIS_KEY)) {
            redisTemplate.opsForValue().set(REDIS_KEY, true);
            blog.setLikes(++likes);
        } else if (null != redisTemplate.opsForValue().get(REDIS_KEY)) {
            boolean isLikes = (boolean) redisTemplate.opsForValue().get(REDIS_KEY);
            if (isLikes) {
                blog.setLikes(--likes);
                redisTemplate.opsForValue().set(REDIS_KEY, false);
            } else {
                blog.setLikes(++likes);
                redisTemplate.opsForValue().set(REDIS_KEY, true);
            }
        } else {
            return 0;
        }
        int result = blogMapper.updateByPrimaryKeySelective(blog);
        redisTemplate.opsForValue().set("blog_" + id, blog);
        return result;
    }

    @Async
    @Override
    public void blogClicks(Long id, HttpServletRequest request) {
        log.info("进入增加点击事件");
        Blog blog = blogMapper.selectByPrimaryKey(id).orElseThrow(() -> new BlogNotFoundException(id));
        int clicks = blog.getClicks();

        String REDIS_KEY;
        if (null != SecurityContextHolder.getContext().getAuthentication()) {
            log.info("当前用户已登录");
            MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Long userId = user.getId();
            REDIS_KEY = "click:" + userId + "_click_" + id;

            if (null == redisTemplate.opsForValue().get(REDIS_KEY)) {
                redisTemplate.opsForValue().set(REDIS_KEY, LocalDateTime.now());
                blog.setClicks(++clicks);

            } else if (null != redisTemplate.opsForValue().get(REDIS_KEY)) {
                LocalDateTime lastClick = (LocalDateTime) redisTemplate.opsForValue().get(REDIS_KEY);
                assert lastClick != null;

                if (isBeforeToday(lastClick)) {
                    blog.setClicks(++clicks);
                    redisTemplate.opsForValue().set(REDIS_KEY, false);

                }

            } else {
                return;
            }
        } else {
            log.info("当前用户未登录");
            String ip = Objects.requireNonNull(HttpUtil.getIPAddr(request), "ip获取失败");
            log.info("ip: " + ip);
            REDIS_KEY = "click:" + "anonymous_" + ip.replaceAll(":", "-") + "_click_" + id;

            if (null == redisTemplate.opsForValue().get(REDIS_KEY)) {
                redisTemplate.opsForValue().set(REDIS_KEY, LocalDateTime.now());
                blog.setClicks(++clicks);
                log.info("当前用户未点击过此文章 --> 点击+1");
            } else if (null != redisTemplate.opsForValue().get(REDIS_KEY)) {
                LocalDateTime lastClick = (LocalDateTime) redisTemplate.opsForValue().get(REDIS_KEY);
                log.info("当前用户点击过此文章");

                if (isBeforeToday(lastClick)) {
                    blog.setClicks(++clicks);
                    redisTemplate.opsForValue().set(REDIS_KEY, LocalDateTime.now());
                    log.info("不是今天点的 --> 点击+1");
                }
            } else {
                return;
            }
        }

        blogMapper.updateByPrimaryKeySelective(blog);
    }

    private boolean isBeforeToday(LocalDateTime last) {
        LocalDateTime now = LocalDateTime.now();
        if (last.getYear() == now.getYear()) {
            return last.getDayOfYear() == now.getDayOfYear();
        }
        return false;
    }
}
