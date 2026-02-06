package com.example.ali.MyVSC.repos;

import com.example.ali.MyVSC.entities.Repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepoRepository extends JpaRepository<Repo, Long> {
    List<Repo> findByOwnerId(Long ownerId);
    Optional<Repo> findByOwnerIdAndName(Long ownerId, String name);
    boolean existsByOwnerIdAndName(Long ownerId, String name);
}