package com.kunshao.springbootstudy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * RestTemplate模板
 * @author 王坤
 * @date 2019-03-20
 */
@Configuration
public class RestTemplateConfig {
    @Autowired
    private RestTemplateBuilder templateBuilder;

    @Bean
    public RestTemplate restTemplate(){
        return  templateBuilder.build();
    }
}
