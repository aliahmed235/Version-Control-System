package com.example.ali.MyVSC.entities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "trees")
@Data
public class Tree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 40)
    private String sha1Hash;

    @Column(nullable = false, columnDefinition = "JSON")
    private String entries;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    // Helper methods to work with entries
    @Transient
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public List<TreeEntry> getEntriesList() {
        try {
            return objectMapper.readValue(entries, new TypeReference<List<TreeEntry>>() {});
        } catch (JsonProcessingException e) {
            return new ArrayList<>();
        }
    }

    public void setEntriesList(List<TreeEntry> entriesList) {
        try {
            this.entries = objectMapper.writeValueAsString(entriesList);
        } catch (JsonProcessingException e) {
            this.entries = "[]";
        }
    }
}