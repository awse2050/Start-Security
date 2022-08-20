package com.security.startsecurity.api.service;

import com.security.startsecurity.api.dto.LoginRequest;
import com.security.startsecurity.api.security.CustomAuthenticationManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;

@RequiredArgsConstructor
public class AuthService {

    private final CustomAuthenticationManager authenticationManager;

    public Authentication authenticate(LoginRequest loginRequest) {
        return authenticationManager.authenticate(loginRequest.generateAuthenticationToken());
    }

}
