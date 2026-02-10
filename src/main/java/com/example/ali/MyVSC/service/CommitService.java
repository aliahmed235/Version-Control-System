package com.example.ali.MyVSC.service;

import com.example.ali.MyVSC.dtos.CommitResponse;
import com.example.ali.MyVSC.dtos.CreateCommitRequest;
import com.example.ali.MyVSC.entities.Commit;
import com.example.ali.MyVSC.repos.CommitRepository;
import com.example.ali.MyVSC.repos.RepoRepository;
import com.example.ali.MyVSC.repos.TreeRepository;
import com.example.ali.MyVSC.utils.HashUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommitService {
    private final CommitRepository commitRepository;
    private final RepoRepository repoRepository;
    private final TreeRepository treeRepository;

    public CommitResponse createCommit(CreateCommitRequest request) {
        // 1. Validate repo exists
        if (!repoRepository.existsById(request.getRepoId())) {
            throw new RuntimeException("Repository not found: " + request.getRepoId());
        }

        // 2. Validate tree exists
        if (!treeRepository.existsBySha1Hash(request.getTreeHash())) {
            throw new RuntimeException("Tree not found: " + request.getTreeHash());
        }

        // 3. Validate parent commit exists (if provided)
        if (request.getParentHash() != null && !request.getParentHash().isEmpty()) {
            if (!commitRepository.existsBySha1Hash(request.getParentHash())) {
                throw new RuntimeException("Parent commit not found: " + request.getParentHash());
            }
        }

        // 4. Calculate commit hash
        String commitData = String.format(
                "tree %s\nparent %s\nauthor %s\nmessage %s\ntime %s",
                request.getTreeHash(),
                request.getParentHash() != null ? request.getParentHash() : "",
                request.getAuthor(),
                request.getMessage(),
                LocalDateTime.now().toString()
        );
        String sha1Hash = HashUtil.calculateSha1(commitData.getBytes());

        // 5. Create commit
        Commit commit = new Commit();
        commit.setRepoId(request.getRepoId());
        commit.setSha1Hash(sha1Hash);
        commit.setTreeHash(request.getTreeHash());
        commit.setParentHash(request.getParentHash());
        commit.setAuthor(request.getAuthor());
        commit.setMessage(request.getMessage());
        commit.setTimestamp(LocalDateTime.now());

        // 6. Save to database
        commit = commitRepository.save(commit);

        // 7. Return response
        return toResponse(commit);
    }

    public CommitResponse getCommit(String sha1Hash) {
        Commit commit = commitRepository.findBySha1Hash(sha1Hash)
                .orElseThrow(() -> new RuntimeException("Commit not found: " + sha1Hash));
        return toResponse(commit);
    }

    public List<CommitResponse> getCommitHistory(Long repoId) {
        return commitRepository.findByRepoIdOrderByTimestampDesc(repoId)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private CommitResponse toResponse(Commit commit) {
        return new CommitResponse(
                commit.getSha1Hash(),
                commit.getRepoId(),
                commit.getTreeHash(),
                commit.getParentHash(),
                commit.getAuthor(),
                commit.getMessage(),
                commit.getTimestamp(),
                commit.getCreatedAt()
        );
    }
}