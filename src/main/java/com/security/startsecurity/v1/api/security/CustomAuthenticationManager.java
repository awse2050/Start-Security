package com.security.startsecurity.v1.api.security;

import com.security.startsecurity.v1.api.dto.AuthenticatedMember;
import com.security.startsecurity.v1.api.service.CustomUserDetailsService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

//@Component
public class CustomAuthenticationManager implements AuthenticationManager {

    private final CustomUserDetailsService customUserDetailsService;

    public CustomAuthenticationManager(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        // 실제 가입한 유저인지 조회한다.
        AuthenticatedMember authenticatedMember = (AuthenticatedMember) customUserDetailsService.loadUserByUsername(email);

        /*
            TODO : 복호화 후 비교
         */

        return authenticatedMember.generateAuthenticationToken();
    }

}
