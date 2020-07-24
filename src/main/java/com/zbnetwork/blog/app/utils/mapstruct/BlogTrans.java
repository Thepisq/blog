package com.zbnetwork.blog.app.utils.mapstruct;

import com.zbnetwork.blog.app.DO.Blog;
import com.zbnetwork.blog.app.DTO.BlogDTO;
import com.zbnetwork.blog.app.DTO.UserDTO;
import com.zbnetwork.blog.app.VO.BlogFrontVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author 13496
 */
@Mapper(componentModel = "spring")
public interface BlogTrans {
    BlogTrans INSTANCE = Mappers.getMapper(BlogTrans.class);

    BlogDTO do2Dto(Blog blog);

    List<BlogDTO> listDo2Dto(List<Blog> blogList);

    BlogFrontVO dto2FtVo(BlogDTO blogDTO);

    List<BlogFrontVO> listDto2Vo(List<BlogDTO> blogDTOList);

    Blog dto2Do(BlogDTO blogDTO);

    static BlogDTO do2DtoWithUser(Blog blog, UserDTO user) {
        BlogDTO dto = BlogTrans.INSTANCE.do2Dto(blog);
        dto.setAuthorName(user.getUsername());
        return dto;
    }
}
