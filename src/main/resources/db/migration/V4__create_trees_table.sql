CREATE TABLE trees (
                       id              BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                       sha1_hash       CHAR(40)         NOT NULL,
                       entries         JSON             NOT NULL,
                       created_at      DATETIME(3)      NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
                       PRIMARY KEY (id),
                       UNIQUE KEY uk_trees_sha1 (sha1_hash)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;