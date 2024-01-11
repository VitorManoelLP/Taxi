CREATE TABLE rides
(
    id             UUID PRIMARY KEY,
    price          NUMERIC          NOT NULL,
    id_driver      UUID             NOT NULL,
    id_passenger   UUID             NOT NULL,
    ride_status    VARCHAR          NOT NULL,
    latitude_from  DOUBLE PRECISION NOT NULL,
    longitude_from DOUBLE PRECISION NOT NULL,
    latitude_to    DOUBLE PRECISION NOT NULL,
    longitude_to   DOUBLE PRECISION NOT NULL,
    CONSTRAINT fk_driver FOREIGN KEY (id_driver) REFERENCES account (id),
    CONSTRAINT fk_passenger FOREIGN KEY (id_passenger) REFERENCES account (id),
    CONSTRAINT fk_ride_from FOREIGN KEY (latitude_from, longitude_from) REFERENCES coord (latitude, longitude),
    CONSTRAINT fk_ride_to FOREIGN KEY (latitude_to, longitude_to) REFERENCES coord (latitude, longitude)
);
