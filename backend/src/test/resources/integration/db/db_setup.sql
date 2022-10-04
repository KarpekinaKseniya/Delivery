SET DATABASE SQL SYNTAX PGS TRUE;

CREATE TABLE area
(
    id           BIGINT       NOT NULL,
    name         VARCHAR(100) NOT NULL,
    base_charge  FLOAT,
    has_delivery BOOLEAN,

    CONSTRAINT areas_pk PRIMARY KEY (id),
    CONSTRAINT areas_name_seq UNIQUE (name)
);
CREATE SEQUENCE areas_id_seq START WITH 1;

CREATE TABLE extra_charge
(
    id         BIGINT NOT NULL,
    min_weight FLOAT  NOT NULL,
    max_weight FLOAT  NOT NULL,
    charge     FLOAT  NOT NULL,
    area_id    BIGINT NOT NULL,

    CONSTRAINT charge_pk PRIMARY KEY (id)
);
CREATE SEQUENCE extra_charges_id_seq START WITH 1;

ALTER TABLE extra_charge
    ADD FOREIGN KEY (area_id) REFERENCES area (id);