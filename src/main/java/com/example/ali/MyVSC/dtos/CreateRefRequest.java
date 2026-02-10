package com.example.ali.MyVSC.dtos;

import com.example.ali.MyVSC.entities.Ref;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateRefRequest {
    @NotBlank(message = "Ref name is required")
    private String name;

    @NotBlank(message = "Commit hash is required")
    private String commitHash;

    @NotNull(message = "Ref type is required")
    private Ref.RefType refType;
}