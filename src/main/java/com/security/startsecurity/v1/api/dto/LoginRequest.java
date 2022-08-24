package com.security.startsecurity.v1.api.dto;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginRequest {

    private String email;

    private String password;

    public Authentication generateAuthenticationToken() {
        return new UsernamePasswordAuthenticationToken(
                this.email,
                this.password
        );
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
