# Stones/books schema

# --- !Ups

CREATE TABLE Stone (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    number varchar(255) NOT NULL,
    name varchar(255),
    notes varchar(4095),
    location_id bigint(20) NOT NULL,
    characteristics_id bigint(20) NOT NULL,
    typology_id bigint(20) NOT NULL,
    gps boolean NOT NULL,
    gps_precision float,
    commune varchar(255),
    town varchar(255),
    place_name varchar(255),
    map int,
    longitude float,
    latitude float,
    altitude float,
    location_panoramic boolean NOT NULL,
    location_near_path boolean NOT NULL,
    location_in_woods boolean NOT NULL,
    location_in_slope boolean NOT NULL,
    location_in_meadow boolean NOT NULL,
    location_in_group boolean NOT NULL,
    location_comments varchar(4095),
    existing boolean NOT NULL,
    type_erratic boolean NOT NULL,
    type_in_scree boolean NOT NULL,
    type_rock boolean NOT NULL,
    type_comments varchar(4095),
    rock_nature varchar(4095),
    size_width float,
    size_height float,
    size_depth float,
    signs_cup_count int,
    signs_canal_count int,
    signs_other varchar(4095),
    PRIMARY KEY (id)
);

CREATE TABLE Book (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    number varchar(255) NOT NULL,
    title varchar(255) NOT NULL,
    authors varchar(255),
    publication date,
    isbn varchar(63),
    publisher varchar(255),
    publisher_place varchar(255),
    edition int,
    page_count int,
    notes varchar(4095),
    PRIMARY KEY (id)
);

# --- !Downs

DROP TABLE Stone;
DROP TABLE Book;
