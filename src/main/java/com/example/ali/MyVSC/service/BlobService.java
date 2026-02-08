package com.example.ali.MyVSC.service;

import com.example.ali.MyVSC.dtos.BlobResponse;
import com.example.ali.MyVSC.entities.Blob;
import com.example.ali.MyVSC.repos.BlobRepository;
import com.example.ali.MyVSC.utils.HashUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlobService {
    private final BlobRepository blobRepository;

    public BlobResponse createBlob(byte[] content) {
        // 1. Calculate SHA-1 hash
        String sha1Hash = HashUtil.calculateSha1(content);

        // 2. Check if blob already exists (deduplication)
        if (blobRepository.existsBySha1Hash(sha1Hash)) {
            Blob existingBlob = blobRepository.findBySha1Hash(sha1Hash)
                    .orElseThrow(() -> new RuntimeException("Blob not found"));
            return new BlobResponse(
                    existingBlob.getSha1Hash(),
                    existingBlob.getSize(),
                    existingBlob.getCreatedAt()
            );
        }

        // 3. Create new blob
        Blob blob = new Blob();
        blob.setSha1Hash(sha1Hash);
        blob.setContent(content);
        blob.setSize((long) content.length);

        // 4. Save to database
        blob = blobRepository.save(blob);

        // 5. Return response
        return new BlobResponse(
                blob.getSha1Hash(),
                blob.getSize(),
                blob.getCreatedAt()
        );
    }

    public Blob getBlob(String sha1Hash) {
        return blobRepository.findBySha1Hash(sha1Hash)
                .orElseThrow(() -> new RuntimeException("Blob not found: " + sha1Hash));
    }
}