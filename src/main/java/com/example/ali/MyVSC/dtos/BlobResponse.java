package com.example.ali.MyVSC.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlobResponse {
    private String sha1Hash;
    private Long size;
    private LocalDateTime createdAt;
}