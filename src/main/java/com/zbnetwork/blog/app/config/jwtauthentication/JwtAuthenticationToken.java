package com.zbnetwork.blog.app.config.jwtauthentication;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author 13496
 */
public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private final String principal;


    public JwtAuthenticationToken(String username) {
        super(null);
        this.principal = username;
        setAuthenticated(false);
    }

    public JwtAuthenticationToken(String username, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = username;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }
}
