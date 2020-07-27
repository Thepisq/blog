package com.zbnetwork.blog.app.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
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
import java.util.Objects;

/**
 * @author 13496
 * 使用ConfigurationProperties获取yml中的配置
 * 使用@Autowired注入JwtUtil对象，直接new获取不到yml配置的值
 */
@Data
@Component
@ConfigurationProperties(prefix = "blog.jwt")
public class JwtUtil implements Serializable {
    //单位毫秒
    private Duration expireIn;
    private String secretKey;
    private String header;

    /**
     * @param subject 发放token对象的信息，比如User对象的username之类的
     * @return token字符串
     */
    public String signToken(String subject) {
        Date expireDate = new Date(System.currentTimeMillis() + expireIn.toMillis());
        //根据字节长度选择相应的 HMAC 算法
        Key key = Keys.hmacShaKeyFor(Encoders.BASE64.encode(secretKey.getBytes()).getBytes());
        return Jwts.builder()
                .setSubject(subject)
                .setExpiration(expireDate)
                .signWith(key)
                .compact();
    }

    public Claims getClaims(String token) {
        SecretKey key = Keys.hmacShaKeyFor(Encoders.BASE64.encode(secretKey.getBytes()).getBytes());
        Claims result;
        try {
            result = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return result;
        } catch (JwtException e) {
            return null;
        }
    }

    public boolean verify(String token, String subject) {
        Claims tokenClaims = Objects.requireNonNull(getClaims(token));
        String subInToken = tokenClaims.getSubject();
        return subInToken.equals(subject) && !(new Date().after(tokenClaims.getExpiration()));
    }

    public String refreshToken(String token) {
        Claims tokenClaims = Objects.requireNonNull(getClaims(token));
        return signToken(tokenClaims.getSubject());
    }

    public String getSubject(String token) {
        Claims tokenClaims = Objects.requireNonNull(getClaims(token));
        return tokenClaims.getSubject();
    }


}
