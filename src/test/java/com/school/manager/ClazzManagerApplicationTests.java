package com.school.manager;

import com.school.manager.dao.UserGradeSubjectDao;
import com.school.manager.dto.resp.UserGradeSubjectResp;
import com.school.manager.utils.Md5Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
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

}
