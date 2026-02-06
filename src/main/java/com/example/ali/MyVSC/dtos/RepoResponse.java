package com.example.ali.MyVSC.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class RepoResponse {
    private Long id;
    private String name;
    private String description;
    private String ownerUsername;
    private String defaultBranch;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}