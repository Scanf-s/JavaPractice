package com.sullung2yo.springmvc.basic.requestmapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class ResponseViewController {

    //@ResponseBody가 없기 때문에 response/hello view resolver가 실행되어서 뷰를 찾고, 렌더링을 수행한다.
    //만약 있다면, View resolver를 실행하지 않고, HTTP Body에 직접 response/hello라는 문자가 입력된다.
    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1() {
        ModelAndView modelAndView = new ModelAndView("response/hello")
                .addObject("data", "hello!");
        return modelAndView;
    }
}
