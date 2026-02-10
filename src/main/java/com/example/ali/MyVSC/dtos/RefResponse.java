package com.example.ali.MyVSC.dtos;

import com.example.ali.MyVSC.entities.Ref;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefResponse {
    private Long id;
    private Long repoId;
    private String name;
    private String commitHash;
    private Ref.RefType refType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}