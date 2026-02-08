package com.example.ali.MyVSC.controllers;

import com.example.ali.MyVSC.dtos.BlobResponse;
import com.example.ali.MyVSC.entities.Blob;
import com.example.ali.MyVSC.service.BlobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blobs")
@RequiredArgsConstructor
public class BlobController {
    private final BlobService blobService;

    @PostMapping(consumes = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<BlobResponse> createBlob(
            @RequestBody byte[] content,
            Authentication authentication
    ) {
        BlobResponse response = blobService.createBlob(content);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{sha1Hash}")
    public ResponseEntity<byte[]> getBlob(
            @PathVariable String sha1Hash,
            Authentication authentication
    ) {
        Blob blob = blobService.getBlob(sha1Hash);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentLength(blob.getSize());

        return new ResponseEntity<>(blob.getContent(), headers, HttpStatus.OK);
    }
}