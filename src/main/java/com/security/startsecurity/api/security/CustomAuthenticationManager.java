package com.security.startsecurity.api.security;

import com.security.startsecurity.api.dto.AuthenticatedMember;
import com.security.startsecurity.api.service.CustomUserDetailsService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationManager implements AuthenticationManager {

    private final CustomUserDetailsService customUserDetailsService;

    public CustomAuthenticationManager(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println("인증합니다.");
        System.out.println("authentication : " + authentication);
        String email = authentication.getName();

        AuthenticatedMember authenticatedMember = (AuthenticatedMember) customUserDetailsService.loadUserByUsername(email);
        System.out.println(" customUserDetailsService.loadUserByUsername(email); .....");

        // password Match
        return authenticatedMember.generateAuthenticationToken();
    }

}
