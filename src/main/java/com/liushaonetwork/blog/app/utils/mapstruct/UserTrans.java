package com.liushaonetwork.blog.app.utils.mapstruct;

import com.liushaonetwork.blog.app.DO.MyUserDetails;
import com.liushaonetwork.blog.app.DO.User;
import com.liushaonetwork.blog.app.DTO.UserDTO;
import com.liushaonetwork.blog.app.VO.UserFrontVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author 13496
 * 详情可以百度mapstruct
 * 加了@Mapper(即 @org.mapstruct.Mapper)的接口会在编译后自动生成impl类
 */
@Mapper(componentModel = "spring")
public interface UserTrans {
    UserTrans INSTANCE = Mappers.getMapper(UserTrans.class);

    User dto2Do(UserDTO userDTO);

    UserDTO do2Dto(User user);

    List<UserDTO> listDo2Dto(List<User> userList);

    @Mappings({
            @Mapping(source = "id", target = "userId"),
            @Mapping(source = "username", target = "nickname")
    })
    UserFrontVO dto2FtVo(UserDTO userDTO);

    List<UserFrontVO> listDto2FtVo(List<UserDTO> userList);

    MyUserDetails do2Ud(User user);

}

