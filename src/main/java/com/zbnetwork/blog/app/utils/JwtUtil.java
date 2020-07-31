package com.zbnetwork.blog.app.utils;

import com.zbnetwork.blog.app.utils.userdetails.UserUd;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.io.Serializable;
import java.security.Key;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 13496
 * JwtUtil:
 *   JWT工具类
 *   使用时用@Autowired注入
 */
@Data
@Component
@ConfigurationProperties(prefix = "blog.jwt")
public class JwtUtil implements Serializable {
    private Duration expireIn;
    private String secretKey;

    /**
     * signToken: 生成token
     *
     * @param user 发放token的对象
     * @return token字符串
     */
    public String signToken(UserUd user) {
        Date expireDate = new Date(System.currentTimeMillis() + expireIn.toMillis());
        //根据字节长度选择相应的 HMAC 算法
        Key key = Keys.hmacShaKeyFor(Encoders.BASE64.encode(secretKey.getBytes()).getBytes());
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("username", user.getUsername());
        userInfo.put("roles", user.getAuthorities());
        return Jwts.builder()
                .setIssuer("blog")
                .setIssuedAt(new Date())
                .setClaims(userInfo)
                .setExpiration(expireDate)
                .signWith(key)
                .compact();
    }

    /**
     * getClaims 获取payload的数据
     *
     * @param token token字符串
     * @return Claims对象，jwt中payload内容的包装类，本质是Map
     */
    public Claims getClaims(String token) {
        SecretKey key = Keys.hmacShaKeyFor(Encoders.BASE64.encode(secretKey.getBytes()).getBytes());
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

    }


}
