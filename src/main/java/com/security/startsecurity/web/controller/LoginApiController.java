package com.security.startsecurity.web.controller;

import com.security.startsecurity.api.dto.LoginPrincipal;
import com.security.startsecurity.api.dto.LoginRequest;
import com.security.startsecurity.api.security.CustomAuthenticationManager;
import com.security.startsecurity.api.security.annotation.LoginUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
public class LoginApiController {

    private final CustomAuthenticationManager authenticationManager;

    public LoginApiController(CustomAuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest,
                                        HttpSession httpSession) {
        System.out.println("login.....");

        Authentication authentication = authenticationManager.authenticate(loginRequest.generateAuthenticationToken());

        SecurityContextHolder.getContext().setAuthentication(authentication);
        httpSession.setAttribute("auth", authentication);

        return ResponseEntity.status(HttpStatus.OK).body("success..");
    }

    @GetMapping("/session")
    public ResponseEntity<String> session(@LoginUser LoginPrincipal loginPrincipal,
                                          HttpSession httpSession) {

        System.out.println("SessionAttribute: "+ loginPrincipal);
        System.out.println("httpSession : " +httpSession.getAttribute("auth"));

        return ResponseEntity.status(HttpStatus.OK).body("success..");
    }
}
