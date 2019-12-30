package com.school.manager;

import com.school.manager.dao.UserGradeSubjectDao;
import com.school.manager.dto.resp.UserGradeSubjectResp;
import com.school.manager.entity.LoginUserInfo;
import com.school.manager.pojo.User;
import com.school.manager.pojo.UserPwd;
import com.school.manager.utils.Md5Util;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClazzManagerApplicationTests {

    @Resource
    private UserGradeSubjectDao userGradeSubjectDao;

    @Test
    public void contextLoads() {
        System.out.println(Md5Util.md5("Sxzx2019"));
        Pageable pageable = PageRequest.of(0, 10);
        Page<UserGradeSubjectResp> objects = userGradeSubjectDao.fuzzyQueryList("", pageable);
        long totalElements = objects.getTotalElements();
        int totalPages = objects.getTotalPages();
        List<UserGradeSubjectResp> content = objects.getContent();
        System.out.println(totalElements + ";" + totalPages + ";" + content.toString());
    }

    @Test
    public void beanCopy() {
        LoginUserInfo loginUserInfo = new LoginUserInfo();
        User user = new User();
        user.setId("1234");
        user.setUsername("name");
        UserPwd userPwd = new UserPwd();
        userPwd.setPassword("wrwer");
        BeanUtils.copyProperties(user, loginUserInfo);
        System.out.println(loginUserInfo.toString());
        BeanUtils.copyProperties(userPwd, loginUserInfo);
        System.out.println(loginUserInfo.toString());
    }

    @Test
    public void createJwt() {
        ZonedDateTime zonedDateTime = LocalDateTime.now().atZone(ZoneId.systemDefault());
        Date issuedDate = Date.from(zonedDateTime.toInstant());
        Date date = Date.from(zonedDateTime.plusMinutes(1).toInstant());
        System.out.println(date);
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId("111")
                .setSubject("aaa")
                .setIssuedAt(issuedDate)
                .signWith(SignatureAlgorithm.HS256, "ronin")
                .setExpiration(date);
        System.out.println(jwtBuilder.compact());
    }

    @Test
    public void parseJet() {
        Claims claims = Jwts.parser().
                setSigningKey("ronin")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMTEiLCJzdWIiOiJhYWEiLCJpYXQiOjE1Nzc3MTM2NDcsImV4cCI6MTU3NzcxMzcwN30.wS6t80fIIvOLBWVByntGhkxSHQJZ-NwukIAXV9XPaa0")
                .getBody();
        System.out.println("用户id：" + claims.getId());
        System.out.println("用户名：" + claims.getSubject());
        System.out.println("登录时间：" + claims.getIssuedAt());
        System.out.println("过期时间：" + claims.getExpiration());
    }
}
