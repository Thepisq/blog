package com.zbnetwork.blog.app.service.impl;

import com.zbnetwork.blog.app.mapper.UserDynamicSqlSupport;
import com.zbnetwork.blog.app.mapper.UserMapper;
import com.zbnetwork.blog.app.utils.mapstruct.UserTrans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

/**
 * @author 13496
 * Spring Security 调用此Service进行登录验证
 */
@Service
public class UserUdServiceImpl implements UserDetailsService {
    private final UserMapper userMapper;

    @Autowired
    public UserUdServiceImpl(UserMapper userMapper) {
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
