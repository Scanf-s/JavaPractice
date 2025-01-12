package com.sullung2yo.springmvc.basic.requestmapping;

import com.sullung2yo.springmvc.basic.dto.HelloData;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@Controller
//@RestController -> 이걸 사용하면 모든 메서드에 @ResponseBody가 적용된다.
public class ResponseBodyController {

    // HTTP Response body에 text/plain으로 바로 담는 경우
    @GetMapping("/response-body-string-v1")
    public void responseBodyV1(HttpServletResponse resp) throws IOException {
        resp.getWriter().write("ok");
    }

    // HttpEntity, ResponseEntity(HttpEntity를 상속받은 클래스)를 사용해서 Http status를 추가할 수 있다.
    @GetMapping("/response-body-string-v2")
    public ResponseEntity<String> responseBodyV2() {
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @ResponseBody // View를 사용하지 않고 바로 HTTP Body에 데이터 삽입
    @GetMapping("/response-body-string-v3")
    public String responseBodyV3() {
        return "ok";
    }

    // ResponseEntity를 반환하고, HttpMessageConverter를 사용해서 Serialize를 수행한다.
    @GetMapping("/response-body-json-v1")
    public ResponseEntity<HelloData> responseBodyJsonV1() {
        HelloData helloData = new HelloData();
        helloData.setUsername("user123");
        helloData.setAge(20);

        return new ResponseEntity<>(helloData, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping("/response-body-json-v2")
    public HelloData responseBodyJsonV2() {
        HelloData helloData = new HelloData();
        helloData.setUsername("user123");
        helloData.setAge(20);

        return helloData;
    }
}
