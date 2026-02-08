package com.example.ali.MyVSC.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateBlobRequest {
    @NotNull(message = "Content is required")
    private byte[] content;
}