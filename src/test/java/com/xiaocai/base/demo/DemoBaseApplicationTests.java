package com.xiaocai.base.demo;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import sun.net.www.http.HttpClient;

import java.net.URL;

@SpringBootTest(classes = DemoBaseApplicationTests.class)
class DemoBaseApplicationTests {

    final static String URL = "http://localhost:8805/user/getOne?name=jack";

    @Test
    void contextLoads() {

    }

}
