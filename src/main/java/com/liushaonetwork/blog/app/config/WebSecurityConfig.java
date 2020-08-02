package com.liushaonetwork.blog.app.config;

import com.liushaonetwork.blog.app.config.jwtauthentication.JwtAuthenticationFilter;
import com.liushaonetwork.blog.app.config.jwtauthentication.JwtAuthenticationSuccessHandler;
import com.liushaonetwork.blog.app.config.loginauthentication.MyAuthenticationFailureHandler;
import com.liushaonetwork.blog.app.config.loginauthentication.MyAuthenticationSuccessHandler;
import com.liushaonetwork.blog.app.config.validatecodeauthentication.ValidateCodeFilter;
import com.liushaonetwork.blog.app.service.impl.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.header.Header;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.Arrays;

import static com.liushaonetwork.blog.app.utils.Constants.tokenInHeader;

/**
 * @author 13496
 * WebSecurityConfig:
 *   spring security基本配置
 */

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final MyUserDetailsService userUdService;
    private final MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    private final MyAuthenticationFailureHandler myAuthenticationFailureHandler;
    private final ValidateCodeFilter validateCodeFilter;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final JwtAuthenticationSuccessHandler jwtAuthenticationSuccessHandler;

    @Autowired
    public WebSecurityConfig(MyUserDetailsService userUdService, MyAuthenticationSuccessHandler myAuthenticationSuccessHandler, MyAuthenticationFailureHandler myAuthenticationFailureHandler, ValidateCodeFilter validateCodeFilter, JwtAuthenticationFilter jwtAuthenticationFilter, JwtAuthenticationSuccessHandler jwtAuthenticationSuccessHandler) {
        this.userUdService = userUdService;
        this.myAuthenticationSuccessHandler = myAuthenticationSuccessHandler;
        this.myAuthenticationFailureHandler = myAuthenticationFailureHandler;
        this.validateCodeFilter = validateCodeFilter;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.jwtAuthenticationSuccessHandler = jwtAuthenticationSuccessHandler;
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
//                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)

                .authorizeRequests(a -> a
                        .antMatchers("/", "/b/**").permitAll()
                        .antMatchers("/user/**").hasRole("USER")
                        .antMatchers("/blog/**", "/editor").hasRole("BLOG")
                        .antMatchers("/admin/**", "/users/**", "/blogs/**").hasRole("ADMIN"))
//                //关闭csrf
//                .csrf().disable()
//                //关闭session
//                .sessionManagement().disable()
//                //支持跨域
//                .cors().and()

                .formLogin(b -> b
                        .loginPage("/login")
                        .successHandler(myAuthenticationSuccessHandler)
                        .failureHandler(myAuthenticationFailureHandler)
                        //默认就是username和password
                        .usernameParameter("username")
                        .passwordParameter("password"))

//                .apply(new JwtAuthenticationConfigurer<>())
//                    .permissiveRequestUrls("/logout")
//                    .successHandler(jwtAuthenticationSuccessHandler)
//                    .failureHandler(myAuthenticationFailureHandler)
//                .and()

                .logout(c -> c
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .deleteCookies("JESSIONID"))

                .headers(d -> d
                        .addHeaderWriter(new StaticHeadersWriter(Arrays.asList(
                                new Header("Access-Control-Allow-Origin", "*"),
                                new Header("Access-Control-Expose-Headers", tokenInHeader))))
                )
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

}
