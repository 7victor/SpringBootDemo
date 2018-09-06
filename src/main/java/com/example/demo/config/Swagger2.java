package com.example.demo.config;/**
 * Created by 333 on 2018/9/5.
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Victor
 * @ClassName: Swagger2
 * @Description: Swagger2在线API文档框架配置
 * @date 2018/9/5 17:48
 */
@Configuration
@EnableSwagger2
public class Swagger2 {
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.demo.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("SpringBoot利用Swagger2构建api文档")
                .description("简单优雅的restfun风格,https://blog.csdn.net/qq_15396517")
                .termsOfServiceUrl("https://blog.csdn.net/qq_15396517")
                .version("1.0")
                .build();
    }
}
