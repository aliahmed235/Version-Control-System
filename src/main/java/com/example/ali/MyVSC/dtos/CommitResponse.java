package com.example.ali.MyVSC.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommitResponse {
    private String sha1Hash;
    private Long repoId;
    private String treeHash;
    private String parentHash;
    private String author;
    private String message;
    private LocalDateTime timestamp;
    private LocalDateTime createdAt;
}