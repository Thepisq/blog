package com.liushaonetwork.blog.app.utils;

import com.liushaonetwork.blog.app.DO.MyUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.liushaonetwork.blog.app.utils.Constants.*;

/**
 * @author 13496
 * JwtUtil:
 * JWT工具类
 */
@Data
@Component
public class JwtUtil {

    /**
     * signToken: 生成token
     *
     * @param user 发放token的对象
     * @return token字符串
     */
    public static String signToken(MyUserDetails user) {
        Date expireDate = new Date(System.currentTimeMillis() + tokenExpireIn.toMillis());
        //根据字节长度选择相应的 HMAC 算法
        Key key = Keys.hmacShaKeyFor(Encoders.BASE64.encode(tokenSecretKey.getBytes()).getBytes());
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put(tokenPayloadUsername, user.getUsername());
        userInfo.put(tokenPayloadRoles, user.getAuthorities());
        return Jwts.builder()
                .setClaims(userInfo) //自定义字段要在默认字段前设置
                .setIssuer("blog")
                .setIssuedAt(new Date())
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
    public static Claims getClaims(String token) {
        SecretKey key = Keys.hmacShaKeyFor(Encoders.BASE64.encode(tokenSecretKey.getBytes()).getBytes());
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

    }


}
