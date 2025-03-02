package com.sullung2yo.clientside_oauth_template.user.controller;

import com.sullung2yo.clientside_oauth_template.user.domain.LoginRequest;
import com.sullung2yo.clientside_oauth_template.user.domain.SignUpRequest;
import com.sullung2yo.clientside_oauth_template.user.domain.TokenResponse;
import com.sullung2yo.clientside_oauth_template.user.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public String signUp(@RequestBody SignUpRequest signUpRequest) {
        authService.signUp(signUpRequest);
        return "User Registration Successful";
    }

    @PostMapping("/login")
    public List<TokenResponse> login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }
}
