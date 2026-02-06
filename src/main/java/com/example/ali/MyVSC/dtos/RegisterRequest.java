package com.example.ali.MyVSC.dtos;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank
    @Size(min = 3, max = 50)
    private String username;

    @Email
    private String email;

    @NotBlank
    @Size(min = 6)
    private String password;
}