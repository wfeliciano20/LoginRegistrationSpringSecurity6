package com.williamfeliciano.login_registration_jwt_spring_security_6.controllers;

import com.williamfeliciano.login_registration_jwt_spring_security_6.dtos.LoginRequest;
import com.williamfeliciano.login_registration_jwt_spring_security_6.dtos.LoginResponse;
import com.williamfeliciano.login_registration_jwt_spring_security_6.dtos.RegistrationRequest;
import com.williamfeliciano.login_registration_jwt_spring_security_6.dtos.RegistrationResponse;
import com.williamfeliciano.login_registration_jwt_spring_security_6.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    @PostMapping("/register")
    public ResponseEntity<RegistrationResponse> registe(
            @RequestBody RegistrationRequest request
    ){
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest loginRequest
    ){
        return ResponseEntity.ok(authenticationService.login(loginRequest));
    }


}
