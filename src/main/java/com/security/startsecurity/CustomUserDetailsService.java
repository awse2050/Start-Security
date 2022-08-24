package com.security.startsecurity;

import com.security.startsecurity.v1.api.dto.AuthenticatedMember;
import com.security.startsecurity.v1.api.entity.Member;
import com.security.startsecurity.v1.api.entity.MemberRepository;
import com.security.startsecurity.v1.api.entity.MemberRole;
import com.security.startsecurity.v1.api.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @PostConstruct
    void setup() {
        Member member = new Member("user@email.com",
                "$2a$10$2shDwQspIeYoMXDx.MYFMOqtmTCx6ius1SoXIi.3CUQVZSPGuH5y.");
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
