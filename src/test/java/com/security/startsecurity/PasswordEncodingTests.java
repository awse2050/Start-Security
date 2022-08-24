package com.security.startsecurity;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class PasswordEncodingTests {


    @Autowired
    private PasswordEncoder passwordEncoder;

    @DisplayName("패스워드 인코딩 테스트")
    @Test
    void passwordEncodingTest() {

        String encode = passwordEncoder.encode("12121212");
        System.out.println("encode .. : " + encode);

    }

}
