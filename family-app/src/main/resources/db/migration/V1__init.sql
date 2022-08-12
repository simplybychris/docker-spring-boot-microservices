CREATE TABLE IF NOT EXISTS family
(
    id BIGSERIAL PRIMARY KEY,
    family_name VARCHAR NOT NULL,
    adults_no INTEGER,
    children_no INTEGER,
    infants_no INTEGER
);

CREATE TABLE IF NOT EXISTS family_member
(
    id BIGSERIAL PRIMARY KEY,
    family_id BIGSERIAL NOT NULL,
    family_name VARCHAR,
    given_name VARCHAR,
    age INTEGER
);
