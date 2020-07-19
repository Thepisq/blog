package com.zbnetwork.blog.app.utils.mapstruct;

import com.zbnetwork.blog.app.DO.Blog;
import com.zbnetwork.blog.app.DTO.BlogDTO;
import com.zbnetwork.blog.app.VO.BlogVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BlogTrans {
    BlogTrans INSTANCE = Mappers.getMapper(BlogTrans.class);

    BlogDTO do2Dto(Blog blog);

    BlogVO dto2Vo(BlogDTO blogDTO);

    Blog dto2Do(BlogDTO blogDTO);
}
