package com.zbnetwork.blog.app.utils.mapstruct;

import com.zbnetwork.blog.app.DO.User;
import com.zbnetwork.blog.app.DTO.UserDTO;
import com.zbnetwork.blog.app.VO.UserVO;
import com.zbnetwork.blog.app.utils.role.UserUd;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author 13496
 * do : from Database
 * dto : from Controller to Service OR reverse
 * vo : from Controller to Browser
 * 加了@Mapper(即 @org.mapstruct.Mapper)的接口会在编译后自动生成impl类
 */
@Mapper(componentModel = "spring")
public interface UserTrans {
    UserTrans INSTANCE = Mappers.getMapper(UserTrans.class);

    /**
     * 两个类变量名一致不用额外写映射
     */
    User dto2Do(UserDTO userDTO);

    UserDTO do2Dto(User user);

    List<UserDTO> listDo2Dto(List<User> userList);

    @Mappings({
            @Mapping(source = "id", target = "userId"),
            @Mapping(source = "username", target = "nickname")
    })
    UserVO dto2Vo(UserDTO userDTO);

    List<UserVO> listDto2Vo(List<UserDTO> userList);

    UserUd do2Ud(User user);

}

