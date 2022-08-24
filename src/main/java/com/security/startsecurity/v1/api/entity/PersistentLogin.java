package com.security.startsecurity.v1.api.entity;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Getter
@Table(name = "persistent_logins")
public class PersistentLogin {

    @Id
    private String series;

    @Column(name = "username")
    private String username;

    @Column(name = "token")
    private String token;

    @Column(name = "last_used")
    private LocalDateTime lastLoginAt;

}
