package com.sullung2yo.springmvc.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogTestController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @GetMapping("/log-test")
    public String logTest() {
        String name = "Spring Log Test";

        //application.properties에서 logging.level.root=trace,debug,info,warn,error 중 하나 선택해서 로그 레벨 선택
        //trace는 모든 로그를 보는 레벨
        //패키지 단위로 로그 레벨을 설정할수도 있음
        //logging.level.com.sullung2yo.springmvc.basic=trace -> 이 패키지에만 trace 레벨 적용
        log.trace("trace log={}", name);
        log.debug("debug log={}", name);
        log.info("info log={}", name); // 아무 설정 안하면 기본 레벨로 설정됨
        log.warn("warn log={}", name);
        log.error("error log={}", name);

        return "ok";
    }
}
