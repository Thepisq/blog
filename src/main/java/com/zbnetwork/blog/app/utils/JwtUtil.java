package com.zbnetwork.blog.app.utils;

import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

/**
 * @author 13496
 */
public class JwtUtil {
    private static final long EXPIRE_TIME = 3 * 60 * 60 * 1000;

    /**
     * @param id        发放token对象的id
     * @param subject   发放token对象的信息，比如User对象的username
     * @param secretKey 生成加密token的密钥，一般写进配置文件
     * @return
     */
    public static String signToken(Long id, String subject, String secretKey) {
        Date expireDate = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        System.out.println(Encoders.BASE64.encode(secretKey.getBytes()).toString());
        //根据字节长度选择相应的 HMAC 算法
        Key key = Keys.hmacShaKeyFor(Encoders.BASE64.encode(secretKey.getBytes()).getBytes());
        return Jwts.builder()
                .claim("userId", String.valueOf(id))
                .setSubject(subject)
                .setExpiration(expireDate)
                .signWith(key)
                .compact();
    }

    public static String parseToken(String token, String secretKey) {
        SecretKey key = Keys.hmacShaKeyFor(Encoders.BASE64.encode(secretKey.getBytes()).getBytes());
        Jws result;
        try {
            result = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return result.toString();
        } catch (JwtException e) {

        }
        return null;
    }

}
