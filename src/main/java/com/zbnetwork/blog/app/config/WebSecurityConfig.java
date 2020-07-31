package com.zbnetwork.blog.app.config;

import com.zbnetwork.blog.app.config.loginauthentication.MyAuthenticationFailureHandler;
import com.zbnetwork.blog.app.config.loginauthentication.MyAuthenticationSuccessHandler;
import com.zbnetwork.blog.app.config.validatecodeauthentication.ValidateCodeFilter;
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
 * WebSecurityConfig:
 *   spring security基本配置
 */

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserUdServiceImpl userUdService;
    private final MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    private final MyAuthenticationFailureHandler myAuthenticationFailureHandler;
    private final ValidateCodeFilter validateCodeFilter;
    private final DataSource datasource;

    @Autowired
    public WebSecurityConfig(UserUdServiceImpl userUdService, MyAuthenticationSuccessHandler myAuthenticationSuccessHandler, MyAuthenticationFailureHandler myAuthenticationFailureHandler, ValidateCodeFilter validateCodeFilter, DataSource datasource) {
        this.userUdService = userUdService;
        this.myAuthenticationSuccessHandler = myAuthenticationSuccessHandler;
        this.myAuthenticationFailureHandler = myAuthenticationFailureHandler;
        this.validateCodeFilter = validateCodeFilter;
        this.datasource = datasource;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userUdService).passwordEncoder(getPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //验证码在用户名密码之前验证
                .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)

                .authorizeRequests(a -> a
                        .antMatchers("/", "/b/**").permitAll()
                        .antMatchers("/user/**").hasRole("USER")
                        .antMatchers("/blog/**", "/editor").hasRole("BLOG")
                        .antMatchers("/admin/**", "/users/**", "/blogs/**").hasRole("ADMIN"))

                .formLogin(b -> b
                        .loginPage("/login")
                        .successHandler(myAuthenticationSuccessHandler)
                        .failureHandler(myAuthenticationFailureHandler)
                        //默认就是username和password
                        .usernameParameter("username")
                        .passwordParameter("password"))

                .logout(c -> c
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .deleteCookies("JESSIONID"))
                //(未完善)
                .rememberMe(d -> d
                        .tokenRepository(persistentTokenRepository())
                        .tokenValiditySeconds(3600)
                        .userDetailsService(userUdService))
        ;

        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter, CsrfFilter.class);
    }

    /**
     * 静态资源释放
     */
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/static/**");
    }

    @Bean
    public BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl persistentTokenRepository = new JdbcTokenRepositoryImpl();
        persistentTokenRepository.setDataSource(datasource);
        //下一行运行会创建一个表，未创建过需要运行一次
        //persistentTokenRepository.setCreateTableOnStartup(true);
        return persistentTokenRepository;
    }
}
