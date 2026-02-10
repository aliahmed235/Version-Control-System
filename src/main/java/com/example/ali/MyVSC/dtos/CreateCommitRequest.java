package com.example.ali.MyVSC.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateCommitRequest {
    @NotNull(message = "Repository ID is required")
    private Long repoId;

    @NotBlank(message = "Tree hash is required")
    private String treeHash;

    private String parentHash;

    @NotBlank(message = "Author is required")
    private String author;

    @NotBlank(message = "Commit message is required")
    private String message;
}