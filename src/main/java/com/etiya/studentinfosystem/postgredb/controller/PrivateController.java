package com.etiya.studentinfosystem.postgredb.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/private")
@RestController
public class PrivateController {
    @GetMapping
    public String privateRequestHandler(Authentication authentication){
        System.out.println(authentication.getPrincipal());
        return "Response from private endpoint";
    }
}
