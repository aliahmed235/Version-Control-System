package com.example.ali.MyVSC.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateRefRequest {
    @NotBlank(message = "Commit hash is required")
    private String commitHash;
}