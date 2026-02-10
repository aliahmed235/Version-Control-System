package com.example.ali.MyVSC.controllers;

import com.example.ali.MyVSC.dtos.CommitResponse;
import com.example.ali.MyVSC.dtos.CreateCommitRequest;
import com.example.ali.MyVSC.service.CommitService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/commits")
@RequiredArgsConstructor
public class CommitController {
    private final CommitService commitService;

    @PostMapping
    public ResponseEntity<CommitResponse> createCommit(
            @Valid @RequestBody CreateCommitRequest request
    ) {
        CommitResponse response = commitService.createCommit(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{sha1Hash}")
    public ResponseEntity<CommitResponse> getCommit(
            @PathVariable String sha1Hash
    ) {
        CommitResponse response = commitService.getCommit(sha1Hash);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/repo/{repoId}/history")
    public ResponseEntity<List<CommitResponse>> getCommitHistory(
            @PathVariable Long repoId
    ) {
        List<CommitResponse> commits = commitService.getCommitHistory(repoId);
        return ResponseEntity.ok(commits);
    }
}