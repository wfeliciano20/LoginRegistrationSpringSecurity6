package com.williamfeliciano.login_registration_jwt_spring_security_6.services;

import com.williamfeliciano.login_registration_jwt_spring_security_6.dtos.LoginRequest;
import com.williamfeliciano.login_registration_jwt_spring_security_6.dtos.LoginResponse;
import com.williamfeliciano.login_registration_jwt_spring_security_6.dtos.RegistrationRequest;
import com.williamfeliciano.login_registration_jwt_spring_security_6.dtos.RegistrationResponse;
import com.williamfeliciano.login_registration_jwt_spring_security_6.models.Role;
import com.williamfeliciano.login_registration_jwt_spring_security_6.models.User;
import com.williamfeliciano.login_registration_jwt_spring_security_6.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public RegistrationResponse register(RegistrationRequest request) {
        var user  = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .pwd(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return RegistrationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public LoginResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        var user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not registered"));
        var jwtToken = jwtService.generateToken(user);
        return LoginResponse.builder()
                .token(jwtToken)
                .build();
    }
}
