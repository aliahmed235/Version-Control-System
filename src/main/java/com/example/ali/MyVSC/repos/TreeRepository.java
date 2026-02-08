package com.example.ali.MyVSC.repos;

import com.example.ali.MyVSC.entities.Tree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TreeRepository extends JpaRepository<Tree, Long> {
    Optional<Tree> findBySha1Hash(String sha1Hash);
    boolean existsBySha1Hash(String sha1Hash);
}