package com.school.manager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author RoninLee
 * @description Swagger配置
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("课程管理系统swagger接口文档")
                .apiInfo(new ApiInfoBuilder().title("课程管理系统swagger接口文档")
                        .contact(new Contact("李泽龙", "", "ITMaster233@gmail.com")).version("1.0").build())
                .select().paths(PathSelectors.any()).build();
    }
}
