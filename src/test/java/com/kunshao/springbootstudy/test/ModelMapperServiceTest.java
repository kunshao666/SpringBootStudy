package com.kunshao.springbootstudy.test;

import com.kunshao.springbootstudy.ApplicationTests;
import com.kunshao.springbootstudy.service.ModelMapperService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ModelMapperServiceTest extends ApplicationTests {
    @Autowired
    private ModelMapperService modelMapperService;

    @Test
    public void test(){
        this.modelMapperService.testModelMapper();
    }
}
