CREATE TABLE refs (
                      id              BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                      repo_id         BIGINT UNSIGNED NOT NULL,
                      name            VARCHAR(255)     NOT NULL,
                      commit_hash     CHAR(40)         NOT NULL,
                      ref_type        ENUM('branch', 'tag') NOT NULL,
                      created_at      DATETIME(3)      NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
                      updated_at      DATETIME(3)      NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
                      PRIMARY KEY (id),
                      UNIQUE KEY uk_refs_repo_name (repo_id, name),
                      KEY idx_refs_commit (commit_hash),
                      FOREIGN KEY (repo_id) REFERENCES repos(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;