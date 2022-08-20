package com.security.startsecurity.api.service;

import com.security.startsecurity.api.dto.AuthenticatedMember;
import com.security.startsecurity.api.entity.Member;
import com.security.startsecurity.api.entity.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @PostConstruct
    void setup() {
        memberRepository.save(new Member("user@email.com", "121212"));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("인증된 유저를 찾아봅시다.");
        System.out.println("email : " + email);

        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("없는 사람이에요"));

        AuthenticatedMember authenticatedMember = new AuthenticatedMember(
                member.getEmail(),
                member.getPassword(),
                Stream.of("ROLE_MEMBER")
                        .map(role -> new SimpleGrantedAuthority(role))
                        .collect(Collectors.toSet()) // 임시 권한
        );

        return authenticatedMember;
    }
}
