CREATE TABLE coord
(
    coord_name VARCHAR(255) NOT NULL,
    cep VARCHAR(50) NOT NULL UNIQUE,
    latitude  DOUBLE PRECISION NOT NULL,
    longitude DOUBLE PRECISION NOT NULL,
    place_id VARCHAR(255),
    PRIMARY KEY (latitude, longitude)
);
