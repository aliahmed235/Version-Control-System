package com.example.ali.MyVSC.controllers;

import com.example.ali.MyVSC.dtos.CreateRefRequest;
import com.example.ali.MyVSC.dtos.RefResponse;
import com.example.ali.MyVSC.dtos.UpdateRefRequest;
import com.example.ali.MyVSC.service.RefService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/repos/{repoId}/refs")
@RequiredArgsConstructor
public class RefController {
    private final RefService refService;

    @PostMapping
    public ResponseEntity<RefResponse> createRef(
            @PathVariable Long repoId,
            @Valid @RequestBody CreateRefRequest request
    ) {
        RefResponse response = refService.createRef(repoId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<RefResponse>> listRefs(
            @PathVariable Long repoId
    ) {
        List<RefResponse> refs = refService.listRefs(repoId);
        return ResponseEntity.ok(refs);
    }

    @GetMapping("/{name}")
    public ResponseEntity<RefResponse> getRef(
            @PathVariable Long repoId,
            @PathVariable String name
    ) {
        RefResponse response = refService.getRef(repoId, name);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{name}")
    public ResponseEntity<RefResponse> updateRef(
            @PathVariable Long repoId,
            @PathVariable String name,
            @Valid @RequestBody UpdateRefRequest request
    ) {
        RefResponse response = refService.updateRef(repoId, name, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteRef(
            @PathVariable Long repoId,
            @PathVariable String name
    ) {
        refService.deleteRef(repoId, name);
        return ResponseEntity.noContent().build();
    }
}