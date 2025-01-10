package com.sullung2yo.springmvc.basic.requestmapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Slf4j
@Controller
public class RequestParamController {

    // 쿼리 파라미터 또는 Form 데이터를 받는 방법
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        int age = Integer.parseInt(req.getParameter("age"));
        log.info("username={}, age={}", username, age);
        resp.getWriter().write("ok");
    }

    // @RequestParam으로 받기
    @ResponseBody // Controller라서 View를 찾아가므로 ok라는 html파일을 검색하게 된다. 따라서 ResponseBody로 바로 ok 문자열을 반환핧 수 있도록 설정
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String username, // v3, v4와 같이 생략하지 말고 이렇게 쓰는게 좋다.
            @RequestParam("age") int age)
    {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    // 또다른 방식 - 생략
    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username, // 생략됨
            @RequestParam int age // 생략됨
    ) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    // 더 생략할수도 있다. (String, int, Integer같은 단순 타입으로만 받는 경우)
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(
            String username, // 생략됨 -> 단, required=True
            int age // 생략됨 -> 단, required=True
    ) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
        @RequestParam(value = "username", required = true) String username, // 이렇게 하면 빈문자 ""도 받게 되어서 처리를 해줘야함
        @RequestParam(value = "age", required = false) Integer age // 요청 시 age 없어도 OK
    ) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(value = "username", defaultValue = "guest") String username,
            @RequestParam(value = "age", defaultValue = "0") Integer age
    ) {
        // username이 빈문자열 ""이면 기본값으로 처리해버림
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    // Map으로 받아서 처리하기
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam MultiValueMap<String, Object> paramMap) {
        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }
}
