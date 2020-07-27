package com.zbnetwork.blog.app.utils.mapstruct;

import com.zbnetwork.blog.app.DO.Blog;
import com.zbnetwork.blog.app.DTO.BlogDTO;
import com.zbnetwork.blog.app.DTO.UserDTO;
import com.zbnetwork.blog.app.VO.BlogFrontVO;
import com.zbnetwork.blog.app.utils.TimeConvertUtil;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

import static com.zbnetwork.blog.app.utils.Constants.indexBlogContentTextSize;

/**
 * @author 13496
 */
@Mapper(componentModel = "spring")
public interface BlogTrans {
    BlogTrans INSTANCE = Mappers.getMapper(BlogTrans.class);

    BlogDTO do2Dto(Blog blog);

    List<BlogDTO> listDo2Dto(List<Blog> blogList);

    default BlogFrontVO dto2FtVo(BlogDTO blogDTO) {
        if (blogDTO == null) {
            return null;
        }

        BlogFrontVO blogFrontVO = new BlogFrontVO();

        blogFrontVO.setId(blogDTO.getId());
        blogFrontVO.setAuthorId(blogDTO.getAuthorId());
        blogFrontVO.setAuthorName(blogDTO.getAuthorName());
        blogFrontVO.setTitle(blogDTO.getTitle());
        blogFrontVO.setLikes(blogDTO.getLikes());
        blogFrontVO.setClicks(blogDTO.getClicks());
        blogFrontVO.setCollects(blogDTO.getCollects());
        blogFrontVO.setComments(blogDTO.getComments());
        if (blogDTO.getFirstPushDate() != null) {
            blogFrontVO.setFirstPushDate(TimeConvertUtil.getDurationBetweenNow(blogDTO.getFirstPushDate()));
        }
        if (blogDTO.getLastPushDate() != null) {
            blogFrontVO.setLastPushDate(TimeConvertUtil.getDurationBetweenNow(blogDTO.getLastPushDate()));
        }
        blogFrontVO.setTopicId(blogDTO.getTopicId());
        blogFrontVO.setContent(blogDTO.getContent());
        blogFrontVO.setContentView(blogDTO.getContentView());

        return blogFrontVO;
    }

    List<BlogFrontVO> listDto2Vo(List<BlogDTO> blogDTOList);

    Blog dto2Do(BlogDTO blogDTO);

    default List<BlogDTO> listDo2DtoWithUser(List<Blog> blogs, List<UserDTO> users) {
        if (blogs == null || users == null || blogs.size() != users.size()) {
            return null;
        }
        List<BlogDTO> blogDTOs = new ArrayList<>(blogs.size());
        for (int i = 0; i < blogs.size(); i++) {
            blogDTOs.add(do2DtoWithUser(blogs.get(i), users.get(i)));
        }
        return blogDTOs;
    }

    default BlogDTO do2DtoWithUser(Blog blog, UserDTO user) {
        BlogDTO dto = BlogTrans.INSTANCE.do2Dto(blog);
        dto.setContentView(dto.getContent().replaceAll("<(?!p|br|/p|br/)[^>]+>", "").substring(0, indexBlogContentTextSize) + "...");
        dto.setAuthorName(user.getUsername());
        return dto;
    }
}
