package com.security.startsecurity.api.dto;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class AuthenticatedMember extends User {

    private String email;
    private String password;

    public AuthenticatedMember(String email,
                               String password,
                               Collection<? extends GrantedAuthority> authorities) {
        super(email, password, authorities);
        this.email = email;
        this.password = password;
    }

    public Authentication generateAuthenticationToken() {
        // 권한까지 적용시키는 생성자를 호출해야 authenticated = true 가된다.
        return new UsernamePasswordAuthenticationToken(this,
                this.password,
                Stream.of("ROLE_MEMBER")
                .map(role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toSet()));
    }

    @Override
    public String toString() {
        return "AuthenticatedMember{" +
                "email='" + email + '\'' +
                '}';
    }
}
