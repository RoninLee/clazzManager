package com.school.manager.utils;

import com.school.manager.common.constant.RequestConstant;
import com.school.manager.jwt.LoginUserInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * @author RoninLee
 */
@Component
@ConfigurationProperties("jwt.config")
public class JwtUtil {

    /**
     * 加盐的规则
     */
    private String key;

    /**
     * 失效长度/小时
     */
    private Integer ttl;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public long getTtl() {
        return ttl;
    }

    public void setTtl(Integer ttl) {
        this.ttl = ttl;
    }

    /**
     * 生成JWT
     *
     * @param id            用户id
     * @param subject       名称
     * @param loginUserInfo 用户信息
     * @return jwt令牌
     */
    public synchronized String createJwt(String id, String subject, LoginUserInfo loginUserInfo) {
        ZonedDateTime zonedDateTime = LocalDateTime.now().atZone(ZoneId.systemDefault());
        Date now = Date.from(zonedDateTime.toInstant());
        JwtBuilder builder = Jwts.builder().setId(id)
                .setSubject(subject)
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256, key)
                .claim(RequestConstant.LOGIN_USER_INFO, loginUserInfo);
        // 设置过期时长
        if (ttl > 0) {
            builder.setExpiration(Date.from(zonedDateTime.plusHours(ttl).toInstant()));
        }
        return builder.compact();
    }

    /**
     * 解析JWT
     *
     * @param jwtStr Jwt令牌
     * @return 用户信息
     */
    public Claims parseJwt(String jwtStr) {
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(jwtStr)
                .getBody();
    }
}
