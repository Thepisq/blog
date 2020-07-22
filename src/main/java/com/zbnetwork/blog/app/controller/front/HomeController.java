package com.zbnetwork.blog.app.controller.front;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Collection;

/**
 * @author Administrator
 * @date 18-12-9
 */
@Slf4j
@Controller
public class HomeController {
    @GetMapping(value = {"/", "/index"})
    @PreAuthorize("hasAnyAuthority()")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login_page";
    }

    @GetMapping("/register")
    public String register() {
        return "register_page";
    }

    @GetMapping("/editor")
    @PreAuthorize("hasRole('ROLE_BLOG')")
    public String editor() {
        return "blog/editor";
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
    public ResponseEntity<?> myInfo(@AuthenticationPrincipal UserDetails user) {
        return ResponseEntity.ok(user);
    }

    @GetMapping("/myinfo2")
    public ResponseEntity<?> myInfo2() {
        return ResponseEntity.ok(SecurityContextHolder.getContext().getAuthentication());
    }

}
