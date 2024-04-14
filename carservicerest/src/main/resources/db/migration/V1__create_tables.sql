-- SCHEMA: carservicerest

-- DROP SCHEMA IF EXISTS carservicerest ;
CREATE TABLE IF NOT EXISTS cars
(
    id bigint NOT NULL,
    car_code character(50) COLLATE pg_catalog."default",
    make character(50) COLLATE pg_catalog."default",
    model character(50) COLLATE pg_catalog."default",
    year integer,
    CONSTRAINT cars_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS cars
    OWNER to postgres;
    
CREATE TABLE IF NOT EXISTS categories
(
    id bigint NOT NULL,
    category_name character(50) COLLATE pg_catalog."default",
    CONSTRAINT categories_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS categories
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS car_category
(
    car_id bigint,
    category_id bigint
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS car_category
    OWNER to postgres;
    
CREATE TABLE IF NOT EXISTS catters
(
    id bigint NOT NULL,
    catter_name character(50) COLLATE pg_catalog."default",
    car_id bigint,
    CONSTRAINT catters_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS catters
    OWNER to postgres;