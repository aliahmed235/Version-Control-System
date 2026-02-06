package com.example.ali.MyVSC.controllers;

import com.example.ali.MyVSC.dtos.CreateRepoRequest;
import com.example.ali.MyVSC.dtos.RepoResponse;
import com.example.ali.MyVSC.service.RepoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/repos")
@RequiredArgsConstructor
public class RepoController {
    private final RepoService repoService;

    @PostMapping
    public ResponseEntity<RepoResponse> createRepo(
            @Valid @RequestBody CreateRepoRequest request,
            Authentication authentication
    ) {
        String username = authentication.getName();
        RepoResponse response = repoService.createRepo(username, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<RepoResponse>> getUserRepos(Authentication authentication) {
        String username = authentication.getName();
        List<RepoResponse> repos = repoService.getUserRepos(username);
        return ResponseEntity.ok(repos);
    }

    @GetMapping("/{repoName}")
    public ResponseEntity<RepoResponse> getRepo(
            @PathVariable String repoName,
            Authentication authentication
    ) {
        String username = authentication.getName();
        RepoResponse response = repoService.getRepo(username, repoName);
        return ResponseEntity.ok(response);
    }
}