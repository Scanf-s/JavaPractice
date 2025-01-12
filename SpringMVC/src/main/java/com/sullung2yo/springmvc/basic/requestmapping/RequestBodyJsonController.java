package com.sullung2yo.springmvc.basic.requestmapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sullung2yo.springmvc.basic.dto.HelloData;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyJsonController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ServletInputStream inputStream = req.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8); // parse json to string

        log.info("messageBody={}", messageBody);
        HelloData data = objectMapper.readValue(messageBody, HelloData.class); // Deserialize
        log.info("username={}, age={}", data.getUsername(), data.getAge());

        resp.getWriter().write("ok");
    }

    @ResponseBody
    @PostMapping("/request-body-json-v2")
    public String requestBodyJsonV2(@RequestBody String messageBody) throws IOException {
        // @RequestBody가 HTTP 메세지의 BODY에서 JSON으로 들어온 요청에 대해 HttpMessageConverter 사용 -> MappingJackson2HttpMessageConverter 적용
        // MappingJackson2HttpMessageConverter를 사용해서 Body에서 꺼내온 JSON 데이터를 String으로 변환하였으므로, 이를 objectMapper를 사용해서 HelloData에 담는다.
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class); // Deserialize
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v3")
    public String requestBodyJsonV3(@RequestBody HelloData helloData) {
        // @RequestBody에 DTO를 지정해서, 해당 구조에 맞게 데이터가 전달될 수 있도록 한다.
        // 또한, 알아서 Deserialize도 수행해서, helloData에 담기게 된다.
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }
}
