package com.example.rentyourtool.dto;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthResponse {
    private Long id;
    private String name;
    private String email;
    private String accessToken;
    private long expiresInSeconds;
}