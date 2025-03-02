package com.sullung2yo.clientside_oauth_template.config.jwt;

import com.sullung2yo.clientside_oauth_template.user.domain.TokenType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Component
public class JwtTokenProvider {

    @Getter
    private final SecretKey secretKey;

    private final long accessTokenValidMilliseconds;
    private final long refreshTokenValidMilliseconds;

    public JwtTokenProvider(
            @Value("${jwt.secret}") String secretKey,
            @Value("${jwt.access_token.expiration}") long accessTokenValidMilliseconds,
            @Value("${jwt.refresh_token.expiration}") long refreshTokenValidMilliseconds
    ) {
        this.secretKey = Keys.hmacShaKeyFor(secretKey.getBytes());
        this.accessTokenValidMilliseconds = accessTokenValidMilliseconds;
        this.refreshTokenValidMilliseconds = refreshTokenValidMilliseconds;
    }

    /**
     * Generate JWT Access/Refresh Token
     * @param email User Email
     * @param tokenType Access/Refresh type
     * @return JWT Token
     */
    public String createToken(String email, Map<String, ?> payload, TokenType tokenType) {
        Claims claims;

        if (tokenType == TokenType.ACCESS) {
            claims = generateClaims(email, payload, accessTokenValidMilliseconds);
        } else if (tokenType == TokenType.REFRESH) {
            claims = generateClaims(email, payload, refreshTokenValidMilliseconds);
        } else {
            throw new RuntimeException("Invalid token type.");
        }

        return Jwts.builder()
                .signWith(secretKey)
                .claims(claims)
                .compact();
    }

    private Claims generateClaims(String subject, Map<String, ?> payload, long tokenValidMilliseconds) {
        Date now = new Date();
        Date expiredAt = new Date(now.getTime() + tokenValidMilliseconds);

        return Jwts.claims()
                .subject(subject)
                .issuedAt(now)
                .expiration(expiredAt)
                .add(payload)
                .build();
    }

}
