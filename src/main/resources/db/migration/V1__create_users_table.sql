CREATE TABLE users (
                       id              BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                       username        VARCHAR(50)      NOT NULL,
                       email           VARCHAR(254)     NULL,
                       password_hash   VARCHAR(255)     NOT NULL,
                       created_at      DATETIME(3)      NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
                       updated_at      DATETIME(3)      NOT NULL DEFAULT CURRENT_TIMESTAMP(3)
                                     ON UPDATE CURRENT_TIMESTAMP(3),
                       PRIMARY KEY (id),
                       UNIQUE KEY uk_users_username (username),
                       UNIQUE KEY uk_users_email (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;