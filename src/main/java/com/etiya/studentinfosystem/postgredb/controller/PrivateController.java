package com.etiya.studentinfosystem.postgredb.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/private")
@Tag(name = "Private Endpoints", description = "Özel erişime sahip endpointleri içerir.")
public class PrivateController {

    @GetMapping
    @Operation(summary = "Özel endpointten dönen yanıtı getirir.",
            description = "Bu endpoint, sadece yetkilendirilmiş kullanıcılara yanıt döndürür.")
    public String privateRequestHandler(Authentication authentication) {
        System.out.println(authentication.getPrincipal());
        return "Response from private endpoint deneme";
    }
}
