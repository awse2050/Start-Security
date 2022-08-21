package com.security.startsecurity.api.service;

import com.security.startsecurity.api.dto.AuthenticatedMember;
import com.security.startsecurity.api.entity.Member;
import com.security.startsecurity.api.entity.MemberRepository;
import com.security.startsecurity.api.entity.MemberRole;
import com.security.startsecurity.api.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @PostConstruct
    void setup() {
        Member member = new Member("user@email.com", "121212");
        member.addMemberRoles(new MemberRole(member, Role.USER));

        memberRepository.save(member);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("email : " + email);

        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("없는 사람이에요"));

        AuthenticatedMember authenticatedMember = member.bindToAuthenticatedMember();

        return authenticatedMember;
    }
}
