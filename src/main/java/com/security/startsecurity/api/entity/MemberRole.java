package com.security.startsecurity.api.entity;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MemberRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROLE_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE_NAME")
    private Role role;

    public MemberRole(Member member, Role role) {
        this.member = member;
        this.role = role;
    }

    public SimpleGrantedAuthority bindToRoleName() {
        return new SimpleGrantedAuthority(this.role.getRoleName());
    }
}
