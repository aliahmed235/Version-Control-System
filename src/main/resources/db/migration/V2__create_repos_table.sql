CREATE TABLE repos (
                       id              BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                       owner_id        BIGINT UNSIGNED NOT NULL,
                       name            VARCHAR(100)     NOT NULL,
                       description     TEXT             NULL,
                       default_branch  VARCHAR(100)     NOT NULL DEFAULT 'main',
                       created_at      DATETIME(3)      NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
                       updated_at      DATETIME(3)      NOT NULL DEFAULT CURRENT_TIMESTAMP(3)
                           ON UPDATE CURRENT_TIMESTAMP(3),
                       PRIMARY KEY (id),
                       UNIQUE KEY uk_repos_owner_name (owner_id, name),
                       KEY idx_repos_owner_id (owner_id),
                       CONSTRAINT fk_repos_owner
                           FOREIGN KEY (owner_id) REFERENCES users(id)
                               ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;