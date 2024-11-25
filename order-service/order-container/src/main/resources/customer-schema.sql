DROP SCHEMA IF EXISTS customer CASCADE;

CREATE SCHEMA customer;

CREATE EXTENSION IF NOT EXISTS plpgsql;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

DROP TABLE IF EXISTS customer.customers CASCADE;

CREATE TABLE customer.customers
(
    id uuid NOT NULL,
    username character varying COLLATE pg_catalog."default" NOT NULL,
    first_name character varying COLLATE pg_catalog."default" NOT NULL,
    last_name character varying,
    CONSTRAINT customers_pkey PRIMARY KEY (id)
);

DROP TABLE IF EXISTS customer.addresses CASCADE;

CREATE TABLE customer.addresses
(
    id uuid NOT NULL,
    street character varying COLLATE pg_catalog."default" NOT NULL,
    city character varying COLLATE pg_catalog."default" NOT NULL,
    postal_code character varying COLLATE pg_catalog."default" NOT NULL,
    is_default boolean NOT NULL,
    CONSTRAINT addresses_pkey PRIMARY KEY (id)
);

DROP TABLE IF EXISTS customer.customer_addresses CASCADE;

CREATE TABLE customer.customer_addresses
(
    id uuid NOT NULL,
    customer_id uuid NOT NULL,
    address_id uuid NOT NULL,
    CONSTRAINT customer_addresses_pkey PRIMARY KEY (id)
);

ALTER TABLE customer.customer_addresses
    ADD CONSTRAINT "FK_CUSTOMER_ID" FOREIGN KEY (customer_id)
        REFERENCES customer.customers (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE RESTRICT
    NOT VALID;

ALTER TABLE customer.customer_addresses
    ADD CONSTRAINT "FK_ADDRESS_ID" FOREIGN KEY (address_id)
        REFERENCES customer.addresses (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE RESTRICT
    NOT VALID;

DROP MATERIALIZED VIEW IF EXISTS customer.order_customer_m_view;

CREATE MATERIALIZED VIEW customer.order_customer_m_view
TABLESPACE pg_default
AS
 SELECT r.id AS id,
    r.username AS username,
    r.first_name AS first_name,
    r.last_name AS last_name,
    p.id AS address_id,
    p.street AS address_street,
    p.city AS address_city,
    p.postal_code AS address_postal_code,
    p.is_default AS address_is_default
   FROM customer.customers r,
    customer.addresses p,
    customer.customer_addresses rp
  WHERE r.id = rp.customer_id AND p.id = rp.address_id
WITH DATA;

refresh materialized VIEW customer.order_customer_m_view;

DROP function IF EXISTS customer.refresh_order_customer_m_view;

CREATE OR replace function customer.refresh_order_customer_m_view()
returns trigger
AS $$
BEGIN
    refresh materialized VIEW customer.order_customer_m_view;
    return null;
END;
$$  LANGUAGE plpgsql;

DROP trigger IF EXISTS order_customer_m_view ON customer.customer_addresses;

CREATE trigger refresh_order_customer_m_view
after INSERT OR UPDATE OR DELETE OR TRUNCATE
ON customer.customer_addresses FOR each statement
EXECUTE PROCEDURE customer.refresh_order_customer_m_view();