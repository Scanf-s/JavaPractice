package com.sullung2yo.clientside_oauth_template.user.controller;

import com.sullung2yo.clientside_oauth_template.user.domain.User;
import com.sullung2yo.clientside_oauth_template.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/hello")
    public ResponseEntity<?> hello(Authentication authentication) {
        if (authentication != null) {
            String email = authentication.getName();
            Optional<User> user = userRepository.findByEmail(email);
            if (user.isEmpty()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
            return ResponseEntity.ok("Hello, " + email);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
