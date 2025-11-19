package com.mayank.SpringSecurity.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("hello")
    public String hell(HttpServletRequest request){
        return  "I am god of your server..."  + (String) request.getSession().getId();
    }
    @GetMapping("go")
    public String go(){
        System.out.println("I am here");
        return "Bullshit";

    }
}
