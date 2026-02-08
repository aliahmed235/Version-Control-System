package com.example.ali.MyVSC.dtos;

import com.example.ali.MyVSC.entities.TreeEntry;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeResponse {
    private String sha1Hash;
    private List<TreeEntry> entries;
    private LocalDateTime createdAt;
}