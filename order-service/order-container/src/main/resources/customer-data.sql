-- Insert customers
INSERT INTO customer.customers(id, username, first_name, last_name)
VALUES ('d215b5f8-0249-4dc5-89a3-51fd148cfb41', 'customer_1', 'John', 'Doe');
INSERT INTO customer.customers(id, username, first_name, last_name)
VALUES ('d215b5f8-0249-4dc5-89a3-51fd148cfb42', 'customer_2', 'Jane', 'Smith');

-- Insert addresses
INSERT INTO customer.addresses(id, street, city, postal_code, is_default)
VALUES ('d215b5f8-0249-4dc5-89a3-51fd148cfb43', 'street_1', 'Amsterdam', '1000AB', TRUE);
INSERT INTO customer.addresses(id, street, city, postal_code, is_default)
VALUES ('d215b5f8-0249-4dc5-89a3-51fd148cfb44', 'street_2', 'Rotterdam', '3000CD', TRUE);

-- Insert associations between customers and addresses
INSERT INTO customer.customer_addresses(id, customer_id, address_id)
VALUES ('d215b5f8-0249-4dc5-89a3-51fd148cfb45', 'd215b5f8-0249-4dc5-89a3-51fd148cfb41', 'd215b5f8-0249-4dc5-89a3-51fd148cfb43');
INSERT INTO customer.customer_addresses(id, customer_id, address_id)
VALUES ('d215b5f8-0249-4dc5-89a3-51fd148cfb46', 'd215b5f8-0249-4dc5-89a3-51fd148cfb42', 'd215b5f8-0249-4dc5-89a3-51fd148cfb44');
