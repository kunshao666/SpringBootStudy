package com.kunshao.springbootstudy.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * BeanUtil
 * @author 王坤
 * @date 2019-03-19
 */
@Configuration
public class ModelMapperConfig {
    @Autowired
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }
}
