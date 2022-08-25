package com.security.startsecurity.v1.api.entity;

import com.security.startsecurity.v1.api.dto.AuthenticatedMember;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;

    private String email;

    private String password;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Set<MemberRole> memberRoles = new HashSet<>();

    public Member(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public void addMemberRoles(MemberRole memberRoles) {
        this.memberRoles.add(memberRoles);
    }

    public AuthenticatedMember bindToAuthenticatedMember() {
        return new AuthenticatedMember(
                this.id,
                this.email,
                this.password,
                this.memberRoles.stream()
                        .map(MemberRole::bindToRoleName)
                        .collect(Collectors.toSet())
        );
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
