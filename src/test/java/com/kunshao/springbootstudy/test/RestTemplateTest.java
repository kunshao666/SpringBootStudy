package com.kunshao.springbootstudy.test;

import com.kunshao.springbootstudy.ApplicationTests;
import com.kunshao.springbootstudy.model.entity.Link;
import com.kunshao.springbootstudy.service.RestService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class RestTemplateTest extends ApplicationTests {
    @Autowired
    private RestService restService;

    @Test
    public void test(){
        int page = 0;
        while(page <= 100){
            Link apiLink = new Link();
            apiLink.setApi(true);
            apiLink.setSite("今日头条");
            apiLink.setCurrentPage(page ++);
            apiLink.setKeyWord("天津");
            this.restService.genLink(apiLink);
        }
    }

    @Test
    public void testForEach(){
        List<String> list = new ArrayList<>();
        for(String str:list){

        }
    }
}
