package com.liushaonetwork.blog.app.service.impl;

import com.github.pagehelper.PageHelper;
import com.liushaonetwork.blog.app.DO.MyUserDetails;
import com.liushaonetwork.blog.app.DO.User;
import com.liushaonetwork.blog.app.DTO.UserDTO;
import com.liushaonetwork.blog.app.exception.UserNotFoundException;
import com.liushaonetwork.blog.app.mapper.UserDynamicSqlSupport;
import com.liushaonetwork.blog.app.mapper.UserMapper;
import com.liushaonetwork.blog.app.service.UserService;
import com.liushaonetwork.blog.app.utils.IdWorker;
import com.liushaonetwork.blog.app.utils.mapstruct.UserTrans;
import io.jsonwebtoken.lang.Assert;
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
    private final IdWorker idWorker = new IdWorker();
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDTO oneUser(Long id) {
        User user = userMapper.selectByPrimaryKey(id).orElseThrow(() -> new UserNotFoundException(id));
        return UserTrans.INSTANCE.do2Dto(user);
    }

    @Override
    public UserDTO oneUser(String username) {
        User user = userMapper.selectOne(c -> c.where(UserDynamicSqlSupport.username, isEqualTo(username)))
                .orElseThrow(() -> new UserNotFoundException(username));
        return null;
    }

    @Override
    public int saveUser(User user) {
        return userMapper.insertSelective(user);
    }

    @Override
    public int saveUser(String username, String password) {
        Assert.notNull(username, "用户名不能为空");
        Assert.notNull(password, "密码不能为空");
        User user = new User();
        user.setId(idWorker.nextId());
        user.setUsername(username);
        user.setPassword(password);
        user.setRoles("USER");
        return userMapper.insertSelective(user);
    }

    @Override
    public int updateUser(UserDTO userDTO) {
        return userMapper.updateByPrimaryKeySelective(UserTrans.INSTANCE.dto2Do(userDTO));
    }

    @Override
    public List<UserDTO> findAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> userList = new ArrayList<>(userMapper.select(c -> c));
        System.out.println("[UserService.findAll]-->: 一共查出了{" + userList.size() + "}条数据, pageSize: " + pageSize);
        return UserTrans.INSTANCE.listDo2Dto(userList);
    }

    @Override
    public long findUserExists(String username) {
        return userMapper.count(c -> c.where(UserDynamicSqlSupport.username, isEqualTo(username)));
    }

    @Override
    public long findUserExists(Long id) {
        return userMapper.count(c -> c.where(UserDynamicSqlSupport.id, isEqualTo(id)));
    }

    @Override
    public int upgrade(MyUserDetails myUserDetails) {
        User user = userMapper.selectOne(c -> c.where(UserDynamicSqlSupport.id, isEqualTo(myUserDetails.getId())))
                .orElseThrow(() -> new UserNotFoundException(myUserDetails.getId()));
        if (user.getRoles().matches("BLOG")) {
            return 0;
        }
        user.setRoles(user.getRoles() + "," + "BLOG");
        return userMapper.updateByPrimaryKeySelective(user);
    }

}
