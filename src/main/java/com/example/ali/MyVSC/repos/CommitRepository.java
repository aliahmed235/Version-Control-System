package com.example.ali.MyVSC.repos;

import com.example.ali.MyVSC.entities.Commit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommitRepository extends JpaRepository<Commit, Long> {
    Optional<Commit> findBySha1Hash(String sha1Hash);
    boolean existsBySha1Hash(String sha1Hash);
    List<Commit> findByRepoIdOrderByTimestampDesc(Long repoId);
}