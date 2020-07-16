package com.zbnetwork.blog.app.service.impl;

import com.github.pagehelper.PageHelper;
import com.zbnetwork.blog.app.DO.User;
import com.zbnetwork.blog.app.DTO.UserDTO;
import com.zbnetwork.blog.app.exception.UserNotFoundException;
import com.zbnetwork.blog.app.mapper.UserMapper;
import com.zbnetwork.blog.app.service.UserService;
import com.zbnetwork.blog.app.utils.mapstruct.UserTrans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 13496
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDTO oneUser(int id) {
        User user = userMapper.selectByPrimaryKey(id).orElseThrow(()->new UserNotFoundException(id));
        return UserTrans.INSTANCE.do2Dto(user);
    }

    @Override
    public int saveUser(UserDTO userDTO) {
        User user = UserTrans.INSTANCE.dto2Do(userDTO);
        return userMapper.insertSelective(user);
    }

    @Override
    public int updateUser(UserDTO userDTO) {
        User user = UserTrans.INSTANCE.dto2Do(userDTO);
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public List<UserDTO> findAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> userList = new ArrayList<User>(userMapper.select(c -> c));
        userList.forEach(user -> System.out.println("{Service} " + user.toString()));
        return UserTrans.INSTANCE.listDo2Dto(userList);
    }
}
