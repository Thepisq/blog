package com.zbnetwork.blog.app.utils.mapstruct;

import com.zbnetwork.blog.app.DO.User;
import com.zbnetwork.blog.app.DTO.UserDTO;
import com.zbnetwork.blog.app.VO.UserVO;
import com.zbnetwork.blog.app.utils.role.UserUD;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author 13496
 */
@Mapper
public interface UserTrans {
    UserTrans TRANSLATOR = Mappers.getMapper( UserTrans.class );

    UserDTO voToDto(UserVO userVO);
    User dtoToDo(UserDTO userDTO);
    UserDTO doToDto(User user);
    List<UserDTO> listDoToDto(List<User> userList);
    UserVO dtoToVo(UserDTO userDTO);
    List<UserVO> listDtoToVo(List<UserDTO> userList);
    UserUD doToUd(User user);
}

