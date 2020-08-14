package com.liushaonetwork.blog.app.controller.front;

import com.alibaba.fastjson.JSON;
import com.liushaonetwork.blog.app.utils.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;

/**
 * @author Administrator
 * @date 18-12-9
 */
@Slf4j
@Controller
public class HomeController {
    @Autowired
    BlogController blogController;
    @Autowired
    FollowController followController;

    @GetMapping(value = {"/", "/index"})
    @PreAuthorize("hasAnyAuthority()")
    public String index(Model model, HttpServletRequest request) {
        ResponseEntity<?> blogs = blogController.getBlogs(1);
        model.addAttribute("blogs", blogs.getBody());
        model.addAttribute("blogJson", JSON.toJSONString(blogs.getBody()));
        model.addAttribute("auth", JSON.toJSONString(SecurityContextHolder.getContext().getAuthentication()));
        model.addAttribute("isuser", SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER")));
        model.addAttribute("ip", HttpUtil.getIPAddr(request));
        return "index";
    }

    @GetMapping("/b/{id}")
    public String show(HttpServletRequest request, Model model, @PathVariable long id) {
        ResponseEntity<?> blog = blogController.getBlog(id, request);
        model.addAttribute("blog", blog.getBody());
        return "blog/show";
    }

    @GetMapping("/user/follow_show")
    public String myfollowing(Model model) {
        ResponseEntity<?> followings = followController.getMyAllFollow();
        model.addAttribute("followings", followings.getBody());
        if (((HashMap) followings.getBody()).containsKey("msg")) {
            model.addAttribute("emptyList", true);
        } else {
            model.addAttribute("emptyList", false);
        }
        return "follow/follow_show";
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

    @GetMapping("/header")
    public void getHeader(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuilder headers = new StringBuilder("request: {\n");
        Enumeration enu = request.getHeaderNames();
        while (enu.hasMoreElements()) {
            String headName = (String) enu.nextElement();
            headers.append("  " + headName + ": " + request.getHeader(headName) + "\n");
        }
        headers.append("}");
        response.getWriter().write(headers.toString());
    }

    @GetMapping("/ip")
    public ResponseEntity<?> getIPAddr(HttpServletRequest request) {
        return ResponseEntity.ok(HttpUtil.getIPAddr(request));
    }
}
