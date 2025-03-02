package com.sullung2yo.clientside_oauth_template.user.service;

import com.sullung2yo.clientside_oauth_template.config.jwt.JwtTokenProvider;
import com.sullung2yo.clientside_oauth_template.user.domain.*;
import com.sullung2yo.clientside_oauth_template.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public void signUp(SignUpRequest signUpRequest) {
        // Check if the email is already registered
        if (userRepository.findByEmail(signUpRequest.getEmail()).isPresent()) {
            throw new RuntimeException("This email is already registered.");
        }

        // Password encryption
        String encodedPassword = passwordEncoder.encode(signUpRequest.getPassword());

        // Create User entity
        User user = User.builder()
                .email(signUpRequest.getEmail())
                .password(encodedPassword)
                .role("ROLE_USER")
                .build();
        userRepository.save(user);
    }

    public List<TokenResponse> login(LoginRequest loginRequest) {
        // Find user by email
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found."));

        // Check if the password is correct
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Incorrect password.");
        }

        List<TokenResponse> tokens = new ArrayList<>();

        // Create access token
        tokens.add(createAuthTokenForUser(user, TokenType.ACCESS));

        // Create refresh token
        tokens.add(createAuthTokenForUser(user, TokenType.REFRESH));

        return tokens;
    }

    private TokenResponse createAuthTokenForUser(User user, TokenType tokenType) {
        TokenResponse tokenResponse = new TokenResponse();
        String tokenValue = jwtTokenProvider.createToken(user.getEmail(), null, tokenType);
        tokenResponse.setToken(tokenValue);
        tokenResponse.setToken_type(tokenType);
        return tokenResponse;
    }
}
