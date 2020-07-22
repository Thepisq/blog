package com.zbnetwork.blog.app.utils.mapstruct;

import com.zbnetwork.blog.app.DO.Blog;
import com.zbnetwork.blog.app.DTO.BlogDTO;
import com.zbnetwork.blog.app.DTO.UserDTO;
import com.zbnetwork.blog.app.VO.BlogFrontVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author 13496
 */
@Mapper(componentModel = "spring")
public interface BlogTrans {
    BlogTrans INSTANCE = Mappers.getMapper(BlogTrans.class);

    BlogDTO do2Dto(Blog blog);

    BlogFrontVO dto2FtVo(BlogDTO blogDTO);

    Blog dto2Do(BlogDTO blogDTO);

    static BlogDTO do2DtoWithUser(Blog blog, UserDTO user) {
        BlogDTO dto = BlogTrans.INSTANCE.do2Dto(blog);
        dto.setAuthorName(user.getUsername());
        return dto;
    }
}
