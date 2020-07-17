package com.zbnetwork.blog.app.config;

import com.zbnetwork.blog.app.service.impl.UserUdServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author 13496
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final BackdoorAuthenticationProvider backdoorAuthenticationProvider;
    private final UserUdServiceImpl userUdService;

    @Autowired
    public WebSecurityConfig(BackdoorAuthenticationProvider backdoorAuthenticationProvider, UserUdServiceImpl userUdService) {
        this.backdoorAuthenticationProvider = backdoorAuthenticationProvider;
        this.userUdService = userUdService;
    }

    /**
     * 添加登录验证信息
     * backdoorAuthenticationProvider: 自定义登录事件
     * userUdService: 通过使用Spring Security提供的接口实现数据库用户信息访问以及登录验证
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(backdoorAuthenticationProvider);
        auth.userDetailsService(userUdService).passwordEncoder(new BCryptPasswordEncoder());
    }

    /**
     * 鉴权
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/index", "/error").permitAll()
                .antMatchers("/user/**").hasRole("USER")
                .antMatchers("/admin/**", "/users/**").hasRole("ADMIN")
                .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/")
                //表单input的name属性要与设置的一致
                .usernameParameter("username").passwordParameter("password")
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/login");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**");
    }
}
