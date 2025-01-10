package com.sullung2yo.springmvc.basic.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mapping/users")
public class UserAPIController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping
    public String userList() {
        logger.info("userList");
        return "get user list";
    }

    @GetMapping("/{userId}")
    public String userDetail(@PathVariable String userId) {
        logger.info("userId={}", userId);
        return "get user";
    }

    @PostMapping
    public String addUser() {
        logger.info("addUser");
        return "add user";
    }

    @PatchMapping("/{userId}")
    public String userUpdate(@PathVariable String userId) {
        logger.info("userUpdate={}", userId);
        return "update user";
    }

    @DeleteMapping("/{userId}")
    public String userDelete(@PathVariable String userId) {
        logger.info("userDelete={}", userId);
        return "delete user";
    }
}
