package com.williamfeliciano.login_registration_jwt_spring_security_6.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo-controller")
public class SecuredExampleController {

    @GetMapping
    public ResponseEntity<String> securedEndpoint(){
        return ResponseEntity.ok("Hello from secured endpoint");
    }
}
