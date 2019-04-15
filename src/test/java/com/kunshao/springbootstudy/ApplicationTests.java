package com.kunshao.springbootstudy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.MessageFormat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
    @Test
    public void test(){
//        String url = "https://www.toutiao.com/api/search/content/?aid=24&app_name=web_search&offset=''{0}''&format=json&keyword=''{1}''&autoload=true&count=''{2}''&en_qc=1&cur_tab=1&from=search_tab&pd=synthesis&timestamp=1553235738299";
        String url = "n=''{0}'',keyword=''{1}'',count=''{2}''";

        String str = MessageFormat.format(url, "0", "天津", "10");

        System.out.println(str);
    }
}
