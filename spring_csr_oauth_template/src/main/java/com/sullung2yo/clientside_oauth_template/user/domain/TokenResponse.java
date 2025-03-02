package com.sullung2yo.clientside_oauth_template.user.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenResponse {
    private String token;
    private TokenType token_type;
}
