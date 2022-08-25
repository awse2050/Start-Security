package com.security.startsecurity.v1.api.dto;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class LoginPrincipal {

    private Long memberId;

    private String email;

    public LoginPrincipal(Long memberId, String email) {
        this.memberId = memberId;
        this.email = email;
    }
}
