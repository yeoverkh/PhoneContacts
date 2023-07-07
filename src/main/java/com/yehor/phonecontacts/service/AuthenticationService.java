package com.yehor.phonecontacts.service;

import com.yehor.phonecontacts.api.dto.AuthenticationRequest;
import com.yehor.phonecontacts.api.dto.AuthenticationResponse;
import com.yehor.phonecontacts.api.dto.RegisterRequest;
import com.yehor.phonecontacts.api.model.User;
import com.yehor.phonecontacts.config.JwtService;
import com.yehor.phonecontacts.repository.UserRepository;
import jakarta.persistence.EntityExistsException;
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

    public AuthenticationResponse register(RegisterRequest request) {
        if (userRepository.findByLogin(request.getLogin()).isPresent()) {
            throw new EntityExistsException("Entity with this login is already exists");
        }

        User user = User.builder()
                .login(request.getLogin())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        userRepository.save(user);


        String jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword()));
        User user = userRepository.findByLogin(request.getLogin()).orElseThrow();

        String jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
