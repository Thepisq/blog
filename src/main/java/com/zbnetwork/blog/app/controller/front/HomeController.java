package com.zbnetwork.blog.app.controller.front;

import com.zbnetwork.blog.app.utils.role.UserUd;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Collection;

/**
 *
 * @author Administrator
 * @date 18-12-9
 */
@Controller
public class HomeController {
    @GetMapping(value = {"/","/index"})
    public String index(Model model){
        //获取当前用户信息并带回页面
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Collection<GrantedAuthority> authorityCollection = (Collection<GrantedAuthority>) auth.getAuthorities();
        model.addAttribute("authorities", authorityCollection.toString());
        model.addAttribute("username", SecurityContextHolder.getContext().getAuthentication().getName());
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login_page";
    }

    @GetMapping("/t")
    public String test() {
        return "test";
    }

    @GetMapping("/register")
    public String register() {
        return "register_page";
    }

    @GetMapping("/editor")
    public String editor() {
        return "blog/editor";
    }

    @GetMapping("/error")
    public String error() {
        return "error/403";
    }

    @RequestMapping("/user")
    public String user(@AuthenticationPrincipal Principal principal, Model model){
        //获取当前用户信息并带回页面
        model.addAttribute("username", principal.getName());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Collection<GrantedAuthority> authorityCollection = (Collection<GrantedAuthority>) auth.getAuthorities();
        model.addAttribute("authorities", authorityCollection.toString());
        return "user/user";
    }

    @RequestMapping("/admin")
    public String admin(@AuthenticationPrincipal Principal principal, Model model) {
        //获取当前用户信息并带回页面
        model.addAttribute("username", principal.getName());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Collection<GrantedAuthority> authorityCollection = (Collection<GrantedAuthority>) auth.getAuthorities();
        model.addAttribute("authorities", authorityCollection.toString());
        return "admin/admin";
    }

    @GetMapping("/myinfo")
    public ResponseEntity<?> myinfo() {
        //获取当前用户信息
        UserUd ud = (UserUd) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(ud.toString());
    }

}
