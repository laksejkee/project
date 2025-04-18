CREATE TABLE passport_date
(
    id         BIGSERIAL PRIMARY KEY ,
    firstname  VARCHAR(64) NOT NULL,
    lastname   VARCHAR(64) NOT NULL,
    patronymic VARCHAR(64) NOT NULL,
    birth_date DATE NOT NULL,
    passport_number BIGINT NOT NULL UNIQUE ,
    sex        VARCHAR(32) NOT NULL,
    birth_city VARCHAR(32) NOT NULL,
    passport_was_issued VARCHAR(64),
    subdivision_code DATE NOT NULL,
    unit_code BIGINT NOT NULL
);

DROP TABLE passport_date;