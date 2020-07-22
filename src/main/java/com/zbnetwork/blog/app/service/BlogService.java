package com.zbnetwork.blog.app.service;

import com.zbnetwork.blog.app.DO.Blog;
import com.zbnetwork.blog.app.DTO.BlogDTO;

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
     * save one blog
     */
    int saveBlog(Blog blog);

    /**
     * update one blog
     */
    int updateBlog(BlogDTO blogDTO);
}
