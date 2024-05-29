package com.boiko.backend.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SignUpRequest {
    private String email;
    private String password;
    private String nickname;
    private LocalDate birthday;
}
