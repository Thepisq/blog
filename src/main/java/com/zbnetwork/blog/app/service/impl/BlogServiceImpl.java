package com.zbnetwork.blog.app.service.impl;

import com.github.pagehelper.PageHelper;
import com.zbnetwork.blog.app.DO.Blog;
import com.zbnetwork.blog.app.DTO.BlogDTO;
import com.zbnetwork.blog.app.DTO.UserDTO;
import com.zbnetwork.blog.app.mapper.BlogDynamicSqlSupport;
import com.zbnetwork.blog.app.mapper.BlogMapper;
import com.zbnetwork.blog.app.service.BlogService;
import com.zbnetwork.blog.app.service.UserService;
import com.zbnetwork.blog.app.utils.mapstruct.BlogTrans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

/**
 * @author 13496
 */
@Service
public class BlogServiceImpl implements BlogService {
    private final BlogMapper blogMapper;
    private final UserService userService;

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
        return BlogTrans.do2DtoWithUser(blog, user);
    }

    @Override
    public BlogDTO oneBlog(Long id) {
        Blog blog = blogMapper.selectOne(c -> c.where(BlogDynamicSqlSupport.id, isEqualTo(id)))
                .orElseThrow(() -> new RuntimeException("找不到id为{" + id + "}的blog"));
        UserDTO user = userService.oneUser(blog.getAuthorId());
        if (null == user) {
            throw new RuntimeException("找不到Blog{id=[" + blog.getId() + "]}的作者{id=[" + blog.getAuthorId() + "]}");
        }
        return BlogTrans.do2DtoWithUser(blog, user);
    }

    @Override
    public List<BlogDTO> findAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Blog> blogList = new ArrayList<>(blogMapper.select(c -> c));
        System.out.println("[BlogService.findAll]-->: 一共查出了{" + blogList.size() + "}条数据, pageSize: " + pageSize);
        return BlogTrans.INSTANCE.listDo2Dto(blogList);
    }

    @Override
    public int saveBlog(Blog blog) {
        return blogMapper.insertSelective(blog);
    }

    @Override
    public int updateBlog(BlogDTO blogDTO) {
        return blogMapper.updateByPrimaryKeySelective(BlogTrans.INSTANCE.dto2Do(blogDTO));
    }
}
