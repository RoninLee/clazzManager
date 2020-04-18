package com.school.manager;

import com.school.manager.common.FileConfigConstant;
import com.school.manager.jwt.LoginUserInfo;
import com.school.manager.pojo.dao.UserGradeSubjectDao;
import com.school.manager.pojo.entity.User;
import com.school.manager.pojo.entity.UserPassword;
import com.school.manager.service.LessonPlanService;
import com.school.manager.utils.IdWorker;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.HtmlUtils;

import javax.annotation.Resource;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClazzManagerApplicationTests {


    @Autowired
    private FileConfigConstant fileConfigConstant;

    @Resource
    private IdWorker idWorker;

    @Resource
    private UserGradeSubjectDao userGradeSubjectDao;

    @Autowired
    private LessonPlanService lessonPlanService;

/*    @Test
    public void contextLoads() {
        System.out.println(Md5Util.md5("Sxzx2019"));
        Pageable pageable = PageRequest.of(0, 10);
        Page<UserGradeSubjectResp> objects = userGradeSubjectDao.fuzzyQueryList("", pageable);
        long totalElements = objects.getTotalElements();
        int totalPages = objects.getTotalPages();
        List<UserGradeSubjectResp> content = objects.getContent();
        System.out.println(totalElements + ";" + totalPages + ";" + content.toString());
    }*/

    @Test
    public void beanCopy() {
        LoginUserInfo loginUserInfo = new LoginUserInfo();
        User user = new User();
        user.setId("1234");
        user.setName("name");
        UserPassword userPassword = new UserPassword();
        userPassword.setPassword("wrwer");
        BeanUtils.copyProperties(user, loginUserInfo);
        System.out.println(loginUserInfo.toString());
        BeanUtils.copyProperties(userPassword, loginUserInfo);
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

    @Test
    public void base64ToPic() {
        String imgStr = "data:image/png;base64,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";

        OutputStream out = null;
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] bytes = decoder.decode(imgStr.split(",")[1]);
        for (int i = 0; i < bytes.length; ++i) {
            if (bytes[i] < 0) {// 调整异常数据
                bytes[i] += 256;
            }
        }
        // 生成jpeg图片
        String imgPath = fileConfigConstant.getFilePath() + "/temp/" + idWorker.nextId() + ".png";
        try {
            out = new FileOutputStream(imgPath);
            out.write(bytes);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(imgPath);
    }

    @Test
    public void getImgSrc() {
        String context = "<p><span style=\"font-size: 18px;\"><strong><span style=\"font-family: 宋体;\">【</span></strong><strong><span style=\"font-family: 宋体;\">教学目标</span></strong><span style=\"font-family: 宋体;\">】</span></span></p><p style=\"text-align:left\"><span style=\"font-size: 14px;\"><strong><span style=\"font-family: 宋体;\">&nbsp; 知识与技能</span></strong><span style=\"font-family: 宋体;\">：</span></span></p><p style=\"text-align:left\"><span style=\"font-size: 14px;\"><span style=\"font-family: 宋体;\">&nbsp;<img class=\"kfformula\" src=\"data:image/png;base64,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\" data-latex=\"\\frac {dy} {dx}\\div \\frac {\\delta y} {\\delta x}=\\frac {\\Delta y} {\\Delta x}\"/></span></span></p><p style=\"text-align:left\"><span style=\"font-size: 14px;\"><span style=\"font-family: 宋体;\"><br/></span></span></p><p style=\"text-align:left\"><span style=\"font-size: 14px;\"><strong><span style=\"font-size: 14px; font-family: 宋体;\">&nbsp; 过程与方法：</span></strong></span></p><p style=\"text-align:left\"><span style=\"font-size: 14px;\"><strong><span style=\"font-size: 14px; font-family: 宋体;\"><br/></span></strong></span></p><p style=\"text-align:left\"><span style=\"font-size: 14px;\"><strong><span style=\"font-size: 14px; font-family: 宋体;\"><br/></span></strong></span></p><p style=\"text-align:left\"><span style=\"font-size: 18px;\"><strong><span style=\"font-family: 宋体;\">【教学重点】</span></strong><strong><span style=\"font-family: 宋体;\"></span></strong></span></p><p style=\"text-align:left\"><span style=\"font-size: 18px;\"><strong><span style=\"font-family: 宋体;\"><br/></span></strong></span></p><p style=\"text-align:left\"><span style=\"font-size: 18px;\"><strong><span style=\"font-family: 宋体;\"><br/></span></strong></span></p><p style=\"text-align:left\"><span style=\"font-size: 18px;\"><strong><span style=\"font-family: 宋体;\"><br/></span></strong></span></p><p style=\"text-align:left\"><span style=\"font-size: 18px;\"><strong><span style=\"font-family: 宋体;\"><br/></span></strong></span></p><p style=\"text-align:left\"><span style=\"font-size: 18px;\"><strong><span style=\"font-family: 宋体;\"><br/></span></strong></span></p><p style=\"text-align:left\"><span style=\"font-size: 18px;\"><strong><span style=\"font-family: 宋体;\">【教学难点</span></strong><span style=\"font-family: 宋体;\">】</span></span></p><p style=\"text-align:left\"><span style=\"font-size: 18px;\"><span style=\"font-family: 宋体;\"><br/></span></span></p><p style=\"text-align:left\"><span style=\"font-size: 18px;\"><span style=\"font-family: 宋体;\"><br/></span></span></p><p style=\"text-align:left\"><span style=\"font-size: 18px;\"><span style=\"font-family: 宋体;\"><br/></span></span></p><p style=\"text-align:left\"><span style=\"font-size: 18px;\"><span style=\"font-family: 宋体;\"><br/></span></span></p><p style=\"text-align:left\"><span style=\"font-size: 18px;\"><span style=\"font-family: 宋体;\"><br/></span></span></p><p style=\"text-align:left\"><span style=\"font-size: 18px;\"><span style=\"font-family: 宋体;\"><br/></span></span></p><p style=\"text-align:left\"><span style=\"font-family: 宋体; font-size: 18px;\">【<strong>教具准备</strong>】</span></p><p style=\"text-align:left\"><span style=\"font-family: 宋体; font-size: 18px;\"><br/></span></p><p style=\"text-align:left\"><span style=\"font-family: 宋体; font-size: 18px;\"><br/></span></p><p style=\"text-align:left\"><span style=\"font-family: 宋体; font-size: 18px;\"><br/></span></p><p style=\"text-align:left\"><span style=\"font-family: 宋体; font-size: 18px;\"><br/></span></p><p style=\"text-align:left\"><span style=\"font-family: 宋体; font-size: 18px;\"><br/></span></p><p style=\"text-align:left\"><span style=\"font-family: 宋体; font-size: 18px;\"><br/></span></p><p style=\"text-align:left\"><span style=\"font-family: 宋体; font-size: 18px;\"><br/></span></p><p style=\"text-align:left\"><strong><span style=\"font-size: 18px;\"><span style=\"font-family: 宋体;\">【教学过程</span><span style=\"font-family: 宋体;\">】</span></span></strong></p><p style=\"text-align:left\"><strong><span style=\"font-size: 18px;\"><span style=\"font-family: 宋体;\"><br/></span></span></strong></p><p style=\"text-align:left\"><strong><span style=\"font-size: 18px;\"><span style=\"font-family: 宋体;\"><br/></span></span></strong></p><p><br/></p>";
        String contextHtml = HtmlUtils.htmlUnescape(context);
        List<HashMap<String, String>> pics = new ArrayList<>();
        HashMap<String, String> map = new HashMap<>();

        Document doc = Jsoup.parse(contextHtml);
        Elements imgs = doc.select("img");
        int count = 1;
        for (Element img : imgs) {
            String imgUrl = "${imgUrl" + count + "}";
            map.put(imgUrl, img.attr("src"));
            contextHtml = contextHtml.replace(map.get(imgUrl), imgUrl);
            count++;
        }
    }

    @Test
    public void export() {
        lessonPlanService.export("1249708071568150528");
    }
}
