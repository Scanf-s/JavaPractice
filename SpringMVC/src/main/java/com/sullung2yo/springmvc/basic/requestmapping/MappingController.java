package com.sullung2yo.springmvc.basic.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
// @GetMapping -> 여기에다가 지정하면 이 핸들러는 무조건 GET만 처리함
public class MappingController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    // @RequestMapping("/hello-basic", method = RequestMethod.GET) -> GET으로만 받도록 method 지정
    @RequestMapping({"/hello-basic", "/hello-basic-2"}) // HTTP Method 상관 없이 이 URI 입력하면 모두 이 메소드로 처리함
    public String helloBasic() {
        logger.info("helloBasic");
        return "ok";
    }

    @GetMapping("/hello-get") // GET Method만 접근 허용
    public String helloGet() {
        logger.info("helloGet");
        return "ok-get";
    }

    @PostMapping("/hello-post") // POST Method만 접근 허용
    public String helloPost() {
        logger.info("helloPost");
        return "ok-post";
    }

    @PutMapping("/hello-put") // PUT Method만 접근 허용
    public String helloPUT() {
        logger.info("helloPUT");
        return "ok-put";
    }

    @PatchMapping("/hello-patch") // PATCH Method만 접근 허용
    public String helloPatch() {
        logger.info("helloPatch");
        return "ok-patch";
    }

    @DeleteMapping("/hello-delete") // DELETE Method만 접근 허용
    public String helloDelete() {
        logger.info("helloDelete");
        return "ok-delete";
    }

    @GetMapping("/path-param/{userId}") // Path parameter로 넘어온 데이터를 가져오는 방법
    public String pathParam(@PathVariable("userId") String data) {
        logger.info("pathParam={}", data);
        return "ok";
    }

    @GetMapping("/path-param2/{userId}/{name}") // Path parameter로 넘어온 데이터를 가져오는 방법
    public String pathParam2(@PathVariable String userId, @PathVariable String name) { // 이렇게 해도 됨
        logger.info("pathParam2 userId={}, name={}", userId, name);
        return "ok";
    }

    // 특정 헤더에 서버에서 설정한 key value가 존재해야지 이 메서드가 실행됨
    @GetMapping(value = "/header-param", headers = "mode")
    public String mappingHeader() {
        logger.info("mappingHeader");
        return "ok";
    }

    // Content-type 기반 매핑
    @PostMapping(value = "/mapping-consume", consumes = "application/json")
    public String mappingConsumes() {
        logger.info("mappingConsumes");
        return "ok";
    }
}
