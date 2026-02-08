package com.example.ali.MyVSC.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeEntry {
    private String name;
    private String type;
    private String hash;
    private Long mode;
}