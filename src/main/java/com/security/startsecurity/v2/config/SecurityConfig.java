package com.security.startsecurity.v2.config;

import com.security.startsecurity.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;
    private final DataSource dataSource;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

//        AuthenticationManagerBuilder sharedObject = http.getSharedObject(AuthenticationManagerBuilder.class);
//        // AuthenticaionManager의 기본동작방식에 적용만 시키려면 아래처럼 하고,
//        // 중간에 동작과정얼 커스텀한다면 다른 방식으로 등록해야한다.
//        sharedObject.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//        http.authenticationManager(sharedObject.build());

        http
                .csrf().disable()   // html 방식을 쓰지 않으므로 csrf를 고려할 필요성 X
                .formLogin() // Rest Api이므로 Form 방식 X
                .and().authorizeRequests()
                .antMatchers("/auth/**").authenticated()
                .and()

                .headers()
                .frameOptions()
                .sameOrigin()

                .and()
                .rememberMe()
                .tokenRepository(tokenRepository())
                .userDetailsService(userDetailsService)
                .tokenValiditySeconds(1700);

        return http.build();
    }

    // 기본 세팅으로는 테이블관련 Insert를 구현하지 않아도 JPA로그가 나오지않고 테이블에 추가가 된다.
    @Bean
    public PersistentTokenRepository tokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        // 정적 리소스 spring security 대상에서 제외
        return (web) -> {
            web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
