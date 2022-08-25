package com.security.startsecurity.v1.api.service;

import com.security.startsecurity.v1.api.dto.LoginRequest;
import com.security.startsecurity.v1.api.security.CustomAuthenticationManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;

@RequiredArgsConstructor
public class AuthService {

    private final CustomAuthenticationManager authenticationManager;

    public Authentication authenticate(LoginRequest loginRequest) {
        return authenticationManager.authenticate(loginRequest.generateAuthenticationToken());
    }

}
