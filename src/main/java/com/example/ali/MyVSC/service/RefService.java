package com.example.ali.MyVSC.service;

import com.example.ali.MyVSC.dtos.CreateRefRequest;
import com.example.ali.MyVSC.dtos.RefResponse;
import com.example.ali.MyVSC.dtos.UpdateRefRequest;
import com.example.ali.MyVSC.entities.Ref;
import com.example.ali.MyVSC.repos.CommitRepository;
import com.example.ali.MyVSC.repos.RefRepository;
import com.example.ali.MyVSC.repos.RepoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RefService {
    private final RefRepository refRepository;
    private final RepoRepository repoRepository;
    private final CommitRepository commitRepository;

    public RefResponse createRef(Long repoId, CreateRefRequest request) {
        if (!repoRepository.existsById(repoId)) {
            throw new RuntimeException("Repository not found: " + repoId);
        }

        if (!commitRepository.existsBySha1Hash(request.getCommitHash())) {
            throw new RuntimeException("Commit not found: " + request.getCommitHash());
        }

        if (refRepository.existsByRepoIdAndName(repoId, request.getName())) {
            throw new RuntimeException("Ref already exists: " + request.getName());
        }

        Ref ref = new Ref();
        ref.setRepoId(repoId);
        ref.setName(request.getName());
        ref.setCommitHash(request.getCommitHash());
        ref.setRefType(request.getRefType());

        ref = refRepository.save(ref);
        return toResponse(ref);
    }

    public RefResponse getRef(Long repoId, String name) {
        Ref ref = refRepository.findByRepoIdAndName(repoId, name)
                .orElseThrow(() -> new RuntimeException("Ref not found: " + name));
        return toResponse(ref);
    }

    public List<RefResponse> listRefs(Long repoId) {
        return refRepository.findByRepoId(repoId)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public RefResponse updateRef(Long repoId, String name, UpdateRefRequest request) {
        Ref ref = refRepository.findByRepoIdAndName(repoId, name)
                .orElseThrow(() -> new RuntimeException("Ref not found: " + name));

        if (!commitRepository.existsBySha1Hash(request.getCommitHash())) {
            throw new RuntimeException("Commit not found: " + request.getCommitHash());
        }

        ref.setCommitHash(request.getCommitHash());
        ref = refRepository.save(ref);
        return toResponse(ref);
    }

    public void deleteRef(Long repoId, String name) {
        Ref ref = refRepository.findByRepoIdAndName(repoId, name)
                .orElseThrow(() -> new RuntimeException("Ref not found: " + name));
        refRepository.delete(ref);
    }

    private RefResponse toResponse(Ref ref) {
        return new RefResponse(
                ref.getId(),
                ref.getRepoId(),
                ref.getName(),
                ref.getCommitHash(),
                ref.getRefType(),
                ref.getCreatedAt(),
                ref.getUpdatedAt()
        );
    }
}