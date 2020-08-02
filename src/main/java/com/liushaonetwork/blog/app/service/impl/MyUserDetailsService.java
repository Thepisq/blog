package com.liushaonetwork.blog.app.service.impl;

import com.liushaonetwork.blog.app.mapper.UserDynamicSqlSupport;
import com.liushaonetwork.blog.app.mapper.UserMapper;
import com.liushaonetwork.blog.app.utils.mapstruct.UserTrans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

/**
 * @author 13496
 * Spring Security 调用此Service进行数据库用户信息获取
 */
@Service
public class MyUserDetailsService implements UserDetailsService {
    private final UserMapper userMapper;

    @Autowired
    public MyUserDetailsService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * 重写loadUserByUsername方法来获取数据库中用户信息
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userMapper.selectOne(c -> c.where(UserDynamicSqlSupport.username, isEqualTo(username)))
                .map(UserTrans.INSTANCE::do2Ud)
                .orElseThrow(() -> new UsernameNotFoundException("用户[username: " + username + "]不存在"));
    }


}
