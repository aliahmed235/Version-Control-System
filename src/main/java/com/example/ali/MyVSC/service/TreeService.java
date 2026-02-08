package com.example.ali.MyVSC.service;

import com.example.ali.MyVSC.dtos.CreateTreeRequest;
import com.example.ali.MyVSC.dtos.TreeResponse;
import com.example.ali.MyVSC.entities.Tree;
import com.example.ali.MyVSC.entities.TreeEntry;
import com.example.ali.MyVSC.repos.BlobRepository;
import com.example.ali.MyVSC.repos.TreeRepository;
import com.example.ali.MyVSC.utils.HashUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TreeService {
    private final TreeRepository treeRepository;
    private final BlobRepository blobRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public TreeResponse createTree(CreateTreeRequest request) {
        for (TreeEntry entry : request.getEntries()) {
            if ("blob".equals(entry.getType())) {
                if (!blobRepository.existsBySha1Hash(entry.getHash())) {
                    throw new RuntimeException("Blob not found: " + entry.getHash());
                }
            } else if ("tree".equals(entry.getType())) {
                if (!treeRepository.existsBySha1Hash(entry.getHash())) {
                    throw new RuntimeException("Tree not found: " + entry.getHash());
                }
            }
        }

        String sha1Hash = calculateTreeHash(request.getEntries());
        if (treeRepository.existsBySha1Hash(sha1Hash)) {
            Tree existingTree = treeRepository.findBySha1Hash(sha1Hash)
                    .orElseThrow(() -> new RuntimeException("Tree not found"));
            return new TreeResponse(
                    existingTree.getSha1Hash(),
                    existingTree.getEntriesList(),
                    existingTree.getCreatedAt()
            );
        }

        Tree tree = new Tree();
        tree.setSha1Hash(sha1Hash);
        tree.setEntriesList(request.getEntries());

        tree = treeRepository.save(tree);

        return new TreeResponse(
                tree.getSha1Hash(),
                tree.getEntriesList(),
                tree.getCreatedAt()
        );
    }

    public TreeResponse getTree(String sha1Hash) {
        Tree tree = treeRepository.findBySha1Hash(sha1Hash)
                .orElseThrow(() -> new RuntimeException("Tree not found: " + sha1Hash));

        return new TreeResponse(
                tree.getSha1Hash(),
                tree.getEntriesList(),
                tree.getCreatedAt()
        );
    }

    private String calculateTreeHash(List<TreeEntry> entries) {
        try {
            entries.sort((a, b) -> a.getName().compareTo(b.getName()));

            String json = objectMapper.writeValueAsString(entries);
            return HashUtil.calculateSha1(json.getBytes());
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to calculate tree hash", e);
        }
    }
}