package com.zbnetwork.blog.app.service.impl;

import com.github.pagehelper.PageHelper;
import com.zbnetwork.blog.app.DO.User;
import com.zbnetwork.blog.app.DTO.UserDTO;
import com.zbnetwork.blog.app.exception.UserNotFoundException;
import com.zbnetwork.blog.app.mapper.UserDynamicSqlSupport;
import com.zbnetwork.blog.app.mapper.UserMapper;
import com.zbnetwork.blog.app.service.UserService;
import com.zbnetwork.blog.app.utils.mapstruct.UserTrans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

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
    public int saveUser(User user) {
        return userMapper.insertSelective(user);
    }

    @Override
    public int updateUser(UserDTO userDTO) {
        return userMapper.updateByPrimaryKeySelective(UserTrans.INSTANCE.dto2Do(userDTO));
    }

    @Override
    public List<UserDTO> findAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> userList = new ArrayList<User>(userMapper.select(c -> c));
        System.out.println("[UserService.findAll]-->: 一共查出了{" + userList.size() + "}条数据, pageSize: " + pageSize);
        return UserTrans.INSTANCE.listDo2Dto(userList);
    }

    @Override
    public long findUserExists(String username) {
        return userMapper.count(c -> c.where(UserDynamicSqlSupport.username, isEqualTo(username)));
    }
}
