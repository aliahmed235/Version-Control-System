package com.example.ali.MyVSC.repos;

import com.example.ali.MyVSC.entities.Blob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlobRepository extends JpaRepository<Blob, Long> {
    Optional<Blob> findBySha1Hash(String sha1Hash);
    boolean existsBySha1Hash(String sha1Hash);
}