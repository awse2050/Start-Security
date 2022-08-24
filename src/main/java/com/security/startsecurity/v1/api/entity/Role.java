package com.security.startsecurity.v1.api.entity;

import lombok.Getter;

@Getter
public enum Role {

    USER("ROLE_USER");

    private String roleName;

    Role(String roleName) {
        this.roleName = roleName;
    }
}
