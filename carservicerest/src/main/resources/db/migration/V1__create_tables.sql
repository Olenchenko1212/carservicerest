-- SCHEMA: carservicerest

--DROP SCHEMA IF EXISTS carservicerest ;
CREATE TABLE IF NOT EXISTS carservicerest.cars
(
	id bigserial NOT NULL,    
	car_code character(50) COLLATE pg_catalog."default",
    make character(50) COLLATE pg_catalog."default",
    model character(50) COLLATE pg_catalog."default",
    year integer,
    CONSTRAINT cars_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;
    
CREATE TABLE IF NOT EXISTS carservicerest.categories
(
    id bigserial NOT NULL,
    category_name character(50) COLLATE pg_catalog."default",
    CONSTRAINT categories_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

CREATE TABLE IF NOT EXISTS carservicerest.car_category
(
    car_id bigserial NOT NULL,
    category_id bigserial NOT NULL,
    CONSTRAINT car_category_pkey PRIMARY KEY (car_id, category_id),
    CONSTRAINT fk_car_id FOREIGN KEY (car_id)
        REFERENCES carservicerest.cars (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT fk_category_id FOREIGN KEY (category_id)
        REFERENCES carservicerest.categories (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;
