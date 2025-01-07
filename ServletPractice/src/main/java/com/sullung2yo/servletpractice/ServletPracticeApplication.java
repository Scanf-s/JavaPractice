package com.sullung2yo.servletpractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan // 스프링이 자동으로 Servlet을 Spring context 에다가 등록시킴
@SpringBootApplication
public class ServletPracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServletPracticeApplication.class, args);
    }

}
