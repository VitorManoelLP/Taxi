CREATE TABLE IF NOT EXISTS ACCOUNT_TYPE
(
    ID          NUMERIC PRIMARY KEY,
    DESCRIPTION VARCHAR(50) NOT NULL UNIQUE
);

INSERT INTO ACCOUNT_TYPE
VALUES (1, 'Driver')
ON CONFLICT DO NOTHING;
INSERT INTO ACCOUNT_TYPE
VALUES (2, 'PASSENGER')
ON CONFLICT DO NOTHING;
