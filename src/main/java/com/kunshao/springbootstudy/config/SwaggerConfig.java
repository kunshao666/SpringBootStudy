package com.kunshao.springbootstudy.config;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger的配置
 * @author 王坤
 * @date 2019-03-19
 */
@EnableSwagger2
@Configuration
@EnableSwaggerBootstrapUI
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.kunshao.springbootstudy.controller"))
                .paths(PathSelectors.any())
                .build();
                //.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("坤少-Swagger学习")
                .description("接口文档")
                .termsOfServiceUrl("http://localhost:8080/")
                .contact(new Contact("wangkun","","wangkun@troila.com"))
                .version("1.0")
                .build();
    }
}
