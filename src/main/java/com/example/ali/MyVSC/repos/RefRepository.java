package com.example.ali.MyVSC.repos;

import com.example.ali.MyVSC.entities.Ref;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RefRepository extends JpaRepository<Ref, Long> {
    Optional<Ref> findByRepoIdAndName(Long repoId, String name);
    List<Ref> findByRepoId(Long repoId);
    boolean existsByRepoIdAndName(Long repoId, String name);
}