package com.zbnetwork.blog.app.config;

import com.zbnetwork.blog.app.service.impl.UserUdServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.sql.DataSource;

/**
 * @author 13496
 */

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final String SECRET_KEY = "123456";
    private final BackdoorAuthenticationProvider backdoorAuthenticationProvider;
    private final UserUdServiceImpl userUdService;
    private final MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    private final MyAuthenticationFailureHandler myAuthenticationFailureHandler;
    private final ValidateCodeFilter validateCodeFilter;
    private final DataSource datasource;

    @Autowired
    public WebSecurityConfig(BackdoorAuthenticationProvider backdoorAuthenticationProvider, UserUdServiceImpl userUdService, MyAuthenticationSuccessHandler myAuthenticationSuccessHandler, MyAuthenticationFailureHandler myAuthenticationFailureHandler, ValidateCodeFilter validateCodeFilter, DataSource datasource) {
        this.backdoorAuthenticationProvider = backdoorAuthenticationProvider;
        this.userUdService = userUdService;
        this.myAuthenticationSuccessHandler = myAuthenticationSuccessHandler;
        this.myAuthenticationFailureHandler = myAuthenticationFailureHandler;
        this.validateCodeFilter = validateCodeFilter;
        this.datasource = datasource;
    }

    /**
     * 添加登录验证信息
     * backdoorAuthenticationProvider: 内置用户及其角色,无需连接数据库(供测试用)
     * userUdService: 通过使用Spring Security提供的接口实现数据库用户信息访问以及获取权限
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(backdoorAuthenticationProvider);
        auth.userDetailsService(userUdService).passwordEncoder(getPasswordEncoder());
    }

    /**
     * 权限管理与登录管理
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //设置validateCodeFilter在UsernamePasswordAuthenticationFilter之前调用
                //使输入的验证码在用户名密码之前验证
                .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                //限定角色(role)及其资源(url)
                .authorizeRequests(a -> a
                        .antMatchers("/").permitAll()
                        .antMatchers("/user/**").hasRole("USER")
                        .antMatchers("/blog/**", "/editor").hasRole("BLOG")
                        .antMatchers("/admin/**", "/users/**", "/blogs/**").hasRole("ADMIN"))
                //自定义登录,xxxParameter与表单input的name属性一致
                .formLogin(b -> b
                        .loginPage("/login")
                        //.loginProcessingUrl() 自定义的登录接口
                        .successHandler(myAuthenticationSuccessHandler)
                        .failureHandler(myAuthenticationFailureHandler)
                        //默认就是username和password
                        .usernameParameter("username")
                        .passwordParameter("password"))
                //自定义登出
                .logout(c -> c
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .deleteCookies("JESSIONID"))
                //自定义记住我(未完善)
                .rememberMe(d -> d
                        .tokenRepository(persistentTokenRepository())
                        .tokenValiditySeconds(3600)
                        .userDetailsService(userUdService))
        ;
        //解决中文乱码问题
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter, CsrfFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**");
    }

    @Bean
    public BCryptPasswordEncoder getPasswordEncoder() {
        //使用BCryptPasswordEncoder作为密码加密方式
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl persistentTokenRepository = new JdbcTokenRepositoryImpl();
        persistentTokenRepository.setDataSource(datasource);
        //数据库没有这个表的话运行下一行，有了就不用
        //persistentTokenRepository.setCreateTableOnStartup(true);
        return persistentTokenRepository;
    }
}
