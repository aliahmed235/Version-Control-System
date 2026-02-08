CREATE TABLE blobs (
                       id              BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                       sha1_hash       CHAR(40)         NOT NULL,
                       content         LONGBLOB         NOT NULL,
                       size            BIGINT UNSIGNED  NOT NULL,
                       created_at      DATETIME(3)      NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
                       PRIMARY KEY (id),
                       UNIQUE KEY uk_blobs_sha1 (sha1_hash),
                       KEY idx_blobs_size (size)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;