package com.school.manager;

import com.school.manager.utils.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author RoninLee
 */
@SpringBootApplication
public class ClazzManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClazzManagerApplication.class, args);
    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker(1, 1);
    }
}
