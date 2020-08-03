package com.liushaonetwork.blog.app.config;

import com.liushaonetwork.blog.app.config.jwtauthentication.JwtAuthenticationConfigurer;
import com.liushaonetwork.blog.app.config.jwtauthentication.JwtAuthenticationProvider;
import com.liushaonetwork.blog.app.config.jwtauthentication.JwtAuthenticationSuccessHandler;
import com.liushaonetwork.blog.app.config.loginauthentication.MyAuthenticationFailureHandler;
import com.liushaonetwork.blog.app.config.loginauthentication.MyAuthenticationSuccessHandler;
import com.liushaonetwork.blog.app.config.validatecodeauthentication.ValidateCodeFilter;
import com.liushaonetwork.blog.app.service.impl.MyUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 * @author 13496
 * WebSecurityConfig:
 *   spring security基本配置
 */

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userUdService()).passwordEncoder(getPasswordEncoder());
        auth.authenticationProvider(jwtAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //验证码在用户名密码之前验证
                .addFilterBefore(new ValidateCodeFilter(), UsernamePasswordAuthenticationFilter.class)

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
                        .successHandler(myAuthenticationSuccessHandler())
                        .failureHandler(myAuthenticationFailureHandler())
                        //默认就是username和password
                        .usernameParameter("username")
                        .passwordParameter("password"))

                .apply(new JwtAuthenticationConfigurer<>())
                .permissiveRequestUrls("/logout")
                .successHandler(jwtAuthenticationSuccessHandler())
                .failureHandler(myAuthenticationFailureHandler())
                .and()

                .logout(c -> c
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .deleteCookies("JESSIONID"))
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

    @Bean("userUdService")
    protected MyUserDetailsService userUdService() {
        return new MyUserDetailsService();
    }

    @Bean("myAuthenticationSuccessHandler")
    protected MyAuthenticationSuccessHandler myAuthenticationSuccessHandler() {
        return new MyAuthenticationSuccessHandler();
    }

    @Bean("myAuthenticationFailureHandler")
    protected MyAuthenticationFailureHandler myAuthenticationFailureHandler() {
        return new MyAuthenticationFailureHandler();
    }

    @Bean("jwtAuthenticationSuccessHandler")
    protected JwtAuthenticationSuccessHandler jwtAuthenticationSuccessHandler() {
        return new JwtAuthenticationSuccessHandler();
    }

    @Bean("jwtAuthenticationProvider")
    protected JwtAuthenticationProvider jwtAuthenticationProvider() {
        return new JwtAuthenticationProvider();
    }

}
