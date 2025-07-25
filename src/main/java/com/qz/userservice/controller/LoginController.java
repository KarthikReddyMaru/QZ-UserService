package com.qz.userservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @GetMapping(params = {"error"})
    public ResponseEntity<String> loginFailed() {
        return new ResponseEntity<String>("Login Failed", HttpStatus.UNAUTHORIZED);
    }

}
