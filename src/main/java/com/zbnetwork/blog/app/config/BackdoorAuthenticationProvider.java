package com.zbnetwork.blog.app.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author 13496
 */
@Component
public class BackdoorAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        //使登录名为'test'的用户(任意密码)添加以下信息(role)
        if ("test".equals(name)) {
            Collection<GrantedAuthority> authorityCollection = new ArrayList<>();
            authorityCollection.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            authorityCollection.add(new SimpleGrantedAuthority("ROLE_USER"));
            authorityCollection.add(new SimpleGrantedAuthority("ROLE_BLOG"));
            return new UsernamePasswordAuthenticationToken("隐藏BOSS", password, authorityCollection);
        } else {
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(
                UsernamePasswordAuthenticationToken.class);
    }
}
