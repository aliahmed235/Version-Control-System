package com.example.ali.MyVSC.controllers;

import com.example.ali.MyVSC.dtos.CreateTreeRequest;
import com.example.ali.MyVSC.dtos.TreeResponse;
import com.example.ali.MyVSC.service.TreeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trees")
@RequiredArgsConstructor
public class TreeController {
    private final TreeService treeService;

    @PostMapping
    public ResponseEntity<TreeResponse> createTree(
            @Valid @RequestBody CreateTreeRequest request,
            Authentication authentication
    ) {
        TreeResponse response = treeService.createTree(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{sha1Hash}")
    public ResponseEntity<TreeResponse> getTree(
            @PathVariable String sha1Hash,
            Authentication authentication
    ) {
        TreeResponse response = treeService.getTree(sha1Hash);
        return ResponseEntity.ok(response);
    }
}