package com.security.startsecurity.api.dto;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;


public class AuthenticatedMember extends User {

    private LoginPrincipal loginPrincipal;

    public AuthenticatedMember(Long memberId,
                               String email,
                               String password,
                               Collection<? extends GrantedAuthority> authorities) {
        super(email, password, authorities);
        this.loginPrincipal = new LoginPrincipal(memberId, email);
    }

    public Authentication generateAuthenticationToken() {
        return new UsernamePasswordAuthenticationToken(
                this.loginPrincipal,
                getPassword(),
                getAuthorities()
        );
    }
}
