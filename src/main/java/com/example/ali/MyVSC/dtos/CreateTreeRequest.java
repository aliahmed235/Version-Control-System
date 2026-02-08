package com.example.ali.MyVSC.dtos;

import com.example.ali.MyVSC.entities.TreeEntry;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class CreateTreeRequest {
    @NotEmpty(message = "Tree must have at least one entry")
    private List<TreeEntry> entries;
}