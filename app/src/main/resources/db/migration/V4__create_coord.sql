CREATE TABLE coord
(
    coord_name VARCHAR(255) NOT NULL,
    cep VARCHAR(50) NOT NULL UNIQUE,
    latitude  DOUBLE PRECISION NOT NULL,
    longitude DOUBLE PRECISION NOT NULL,
    PRIMARY KEY (latitude, longitude)
);
