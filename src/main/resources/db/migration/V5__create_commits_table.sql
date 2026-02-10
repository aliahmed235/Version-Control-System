CREATE TABLE commits (
                         id              BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                         repo_id         BIGINT UNSIGNED NOT NULL,
                         sha1_hash       CHAR(40)         NOT NULL,
                         tree_hash       CHAR(40)         NOT NULL,
                         parent_hash     CHAR(40),
                         author          VARCHAR(255)     NOT NULL,
                         message         TEXT             NOT NULL,
                         timestamp       DATETIME(3)      NOT NULL,
                         created_at      DATETIME(3)      NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
                         PRIMARY KEY (id),
                         UNIQUE KEY uk_commits_sha1 (sha1_hash),
                         KEY idx_commits_repo (repo_id),
                         KEY idx_commits_tree (tree_hash),
                         FOREIGN KEY (repo_id) REFERENCES repos(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;