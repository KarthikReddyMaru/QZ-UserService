package com.qz.userservice.controller;

import com.qz.userservice.client.KeyCloakTokenService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final KeyCloakTokenService tokenService;

    @GetMapping("/")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok(tokenService.fetchToken().toString());
    }

    @GetMapping("/csrf-token")
    public ResponseEntity<String> getCsrfToken(HttpServletRequest request) {
        CsrfToken token = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        return ResponseEntity.ok(token.getToken());
    }
}
