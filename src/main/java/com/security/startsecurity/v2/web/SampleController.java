package com.security.startsecurity.v2.web;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SampleController {

    @GetMapping("/auth/auth-user")
    public String authenticated() {
        System.out.println("authenticated...");
        System.out.println("contextHodler : " + SecurityContextHolder.getContext().getAuthentication());
        return "result";
    }

}