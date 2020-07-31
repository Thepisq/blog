package com.zbnetwork.blog.app.service;

import com.zbnetwork.blog.app.DO.Blog;
import com.zbnetwork.blog.app.DTO.BlogDTO;

import java.util.List;

/**
 * @author 13496
 */
public interface BlogService {
    /**
     * find one blog by title
     */
    BlogDTO oneBlog(String title);

    BlogDTO oneBlog(Long id);

    /**
     * find all blog by page
     */
    List<BlogDTO> findAll(int pageNum, int pageSize);

    /**
     * save one blog
     */
    int saveBlog(Blog blog);

    int saveBlog(String title, String content, Integer topicId);

    /**
     * update one blog
     */
    int updateBlog(BlogDTO blogDTO);
}
