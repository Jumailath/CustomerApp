drop table if exists customer;


CREATE TABLE customer
(
    id                  integer NOT NULL PRIMARY KEY,
    customer_name       varchar(50) NOT NULL,
    address_line_1      varchar(50),
    address_line_2      varchar(50),
    town                varchar(50),
    county              varchar(50),
    country             varchar(50),
    postcode            varchar(10),
    created_date        timestamp with time zone DEFAULT current_timestamp NOT NULL
);