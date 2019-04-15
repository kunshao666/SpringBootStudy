package com.kunshao.springbootstudy.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kunshao.springbootstudy.ApplicationTests;
import com.kunshao.springbootstudy.model.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ObjectMapperTest extends ApplicationTests {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void objectMapperTest(){
        User user = new User();
        user.setId(2L);
        user.setName("王坤");
        user.setAge(23);

//        this.objectMapper.
    }
}
