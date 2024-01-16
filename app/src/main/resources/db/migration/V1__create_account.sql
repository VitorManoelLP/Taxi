CREATE TABLE IF NOT EXISTS roles
(
    id        BIGINT PRIMARY KEY,
    authority VARCHAR(255) NOT NULL
);

INSERT INTO roles (id, authority) VALUES (1, 'ROLE_USER') ON CONFLICT DO NOTHING;
INSERT INTO roles (id, authority) VALUES (2, 'ROLE_ADMIN') ON CONFLICT DO NOTHING;

CREATE TABLE IF NOT EXISTS ACCOUNT
(
    ID           UUID primary key,
    NAME         VARCHAR(100) NOT NULL,
    EMAIL        VARCHAR(50)  NOT NULL UNIQUE,
    PASSWORD     VARCHAR(255)  NOT NULL,
    PHONE        VARCHAR(13)  NOT NULL,
    IMAGE_PATH   VARCHAR(255),
    ACCOUNT_TYPE NUMERIC      NOT NULL
);

CREATE TABLE IF NOT EXISTS user_roles
(
    id      SERIAL PRIMARY KEY ,
    user_id UUID,
    role_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES ACCOUNT (id),
    FOREIGN KEY (role_id) REFERENCES roles (id)
);
