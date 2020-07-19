package com.zbnetwork.blog.app.service.impl;

import com.zbnetwork.blog.app.DO.Blog;
import com.zbnetwork.blog.app.DTO.BlogDTO;
import com.zbnetwork.blog.app.mapper.BlogDynamicSqlSupport;
import com.zbnetwork.blog.app.mapper.BlogMapper;
import com.zbnetwork.blog.app.service.BlogService;
import com.zbnetwork.blog.app.utils.mapstruct.BlogTrans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

/**
 * @author 13496
 */
@Service
public class BlogServiceImpl implements BlogService {
    private final BlogMapper blogMapper;

    @Autowired
    public BlogServiceImpl(BlogMapper blogMapper) {
        this.blogMapper = blogMapper;
    }

    @Override
    public BlogDTO oneBlog(String title) {
        Blog blog = blogMapper.selectOne(b -> b.where(BlogDynamicSqlSupport.title, isEqualTo(title)))
                .orElseThrow(() -> new RuntimeException("找不到标题为{" + title + "}的blog"));
        return BlogTrans.INSTANCE.do2Dto(blog);
    }

    @Override
    public BlogDTO oneBlog(int id) {
        Blog blog = blogMapper.selectOne(b -> b.where(BlogDynamicSqlSupport.id, isEqualTo(id)))
                .orElseThrow(() -> new RuntimeException("找不到id为{" + id + "}的blog"));
        return BlogTrans.INSTANCE.do2Dto(blog);
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
