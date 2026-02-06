package com.example.ali.MyVSC.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateRepoRequest {
    @NotBlank(message = "Repository name is required")
    @Size(min = 1, max = 100, message = "Repository name must be between 1 and 100 characters")
    @Pattern(regexp = "^[a-zA-Z0-9_-]+$", message = "Repository name can only contain letters, numbers, hyphens, and underscores")
    private String name;

    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;

    private String defaultBranch = "main";
}