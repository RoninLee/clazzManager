package com.school.manager;

import com.school.manager.utils.Md5Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClazzManagerApplicationTests {

    @Test
    public void contextLoads() {
        System.out.println(Md5Util.md5("Sxzx2019"));
    }

}
