package com.zbnetwork.blog.app.service.impl;

import com.zbnetwork.blog.app.mapper.UserMapper;
import com.zbnetwork.blog.app.utils.mapstruct.UserTrans;
import com.zbnetwork.blog.app.utils.role.UserUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author 13496
 */
@Service
public class UserUdServiceImpl implements UserDetailsService {
    private final UserMapper userMapper;
    @Autowired
    public UserUdServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserUD ud = UserTrans.TRANSLATOR.doToUd(userMapper.selectOne(c -> c.where(username,)))
        return ;
    }
}
