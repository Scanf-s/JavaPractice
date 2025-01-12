package com.sullung2yo.springmvc.basic.requestmapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@Slf4j
@RestController
public class RequestHeaderController {

    @RequestMapping ("/headers")
    public String headerInfo(
            HttpServletRequest req,
            HttpServletResponse resp,
            HttpMethod httpMethod,
            Locale locale,
            @RequestHeader MultiValueMap<String, String> headerMap, // 같은 Key에 multi value가 존재하는 헤더 데이터 가져올 때 사용
            @RequestHeader("host") String host,
            @CookieValue(value="myCookie", required = false) String cookie
            ) {

        log.info("request={}", req);
        log.info("response={}", resp);
        log.info("httpMethod={}", httpMethod);
        log.info("locale={}", locale);
        log.info("headerMap={}", headerMap);
        log.info("header host={}", host);
        log.info("myCookie={}", cookie);

        return "header info";
    }
}
