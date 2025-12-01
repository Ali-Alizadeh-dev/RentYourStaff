package com.example.rentyourtool.controller;

import com.example.rentyourtool.dto.AuthResponse;
import com.example.rentyourtool.dto.RegisterRequest;
import com.example.rentyourtool.entity.User;
import com.example.rentyourtool.jwt.JwtService;
import com.example.rentyourtool.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @PostMapping("/register")
    public AuthResponse register(@Valid @RequestBody RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("E-Mail bereits vergeben");
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .address(request.getAddress())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .build();

        userRepository.save(user);

        // Auto-Login nach Registrierung
        String token = jwtService.createAccessToken(user.getId(), user.getEmail());

        return AuthResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .accessToken(token)
                .expiresInSeconds(jwtService.getAccessTokenValiditySeconds())
                .build();
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Ungültige Anmeldedaten"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new IllegalArgumentException("Ungültige Anmeldedaten");
        }

        String token = jwtService.createAccessToken(user.getId(), user.getEmail());

        return AuthResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .accessToken(token)
                .expiresInSeconds(jwtService.getAccessTokenValiditySeconds())
                .build();
    }

    @GetMapping("/me")
    public AuthResponse me(@RequestAttribute(name = "user", required = false) User ignored) {
        // Einfacher Weg: Hole Authentifizierten User aus SecurityContext
        User user = (User) org.springframework.security.core.context.SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();

        return AuthResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .accessToken(null) // kein neues Token hier
                .expiresInSeconds(0)
                .build();
    }
}