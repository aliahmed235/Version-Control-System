package com.example.ali.MyVSC.service;

import com.example.ali.MyVSC.dtos.CreateRepoRequest;
import com.example.ali.MyVSC.dtos.RepoResponse;
import com.example.ali.MyVSC.entities.Repo;
import com.example.ali.MyVSC.entities.User;
import com.example.ali.MyVSC.repos.RepoRepository;
import com.example.ali.MyVSC.repos.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RepoService {
    private final RepoRepository repoRepository;
    private final UserRepository userRepository;

    public RepoResponse createRepo(String username, CreateRepoRequest request) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (repoRepository.existsByOwnerIdAndName(user.getId(), request.getName())) {
            throw new RuntimeException("Repository with name '" + request.getName() + "' already exists");
        }

        Repo repo = new Repo();
        repo.setOwnerId(user.getId());
        repo.setName(request.getName());
        repo.setDescription(request.getDescription());
        repo.setDefaultBranch(request.getDefaultBranch());

        repo = repoRepository.save(repo);

        return new RepoResponse(
                repo.getId(),
                repo.getName(),
                repo.getDescription(),
                user.getUsername(),
                repo.getDefaultBranch(),
                repo.getCreatedAt(),
                repo.getUpdatedAt()
        );
    }

    public List<RepoResponse> getUserRepos(String username) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Repo> repos = repoRepository.findByOwnerId(user.getId());

        return repos.stream()
                .map(repo -> new RepoResponse(
                        repo.getId(),
                        repo.getName(),
                        repo.getDescription(),
                        user.getUsername(),
                        repo.getDefaultBranch(),
                        repo.getCreatedAt(),
                        repo.getUpdatedAt()
                ))
                .collect(Collectors.toList());
    }

    public RepoResponse getRepo(String username, String repoName) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Repo repo = repoRepository.findByOwnerIdAndName(user.getId(), repoName)
                .orElseThrow(() -> new RuntimeException("Repository not found"));

        return new RepoResponse(
                repo.getId(),
                repo.getName(),
                repo.getDescription(),
                user.getUsername(),
                repo.getDefaultBranch(),
                repo.getCreatedAt(),
                repo.getUpdatedAt()
        );
    }
}