DROP TABLE if EXISTS passport_date CASCADE;

CREATE TABLE passport_date (
    id         BIGSERIAL PRIMARY KEY ,
    firstname  VARCHAR(64) NOT NULL,
    lastname   VARCHAR(64) NOT NULL,
    patronymic VARCHAR(64) NOT NULL,
    birth_date DATE NOT NULL,
    passport_series VARCHAR(4) NOT NULL,
    passport_number VARCHAR(6) NOT NULL UNIQUE,
    gender  VARCHAR(32) NOT NULL,
    place_of_birth VARCHAR(32) NOT NULL,
    passport_was_issued VARCHAR(64),
    date_of_issue DATE NOT NULL,
    unit_code VARCHAR(7) NOT NULL
);
