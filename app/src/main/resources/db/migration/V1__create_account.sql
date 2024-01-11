CREATE TABLE IF NOT EXISTS ACCOUNT(
    ID UUID primary key,
    NAME VARCHAR(100) NOT NULL,
    EMAIL VARCHAR(50) NOT NULL UNIQUE,
    PHONE VARCHAR(13) NOT NULL,
    IMAGE_PATH VARCHAR(255),
    ACCOUNT_TYPE NUMERIC NOT NULL
);
