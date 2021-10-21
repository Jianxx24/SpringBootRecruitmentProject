INSERT INTO client(user_id, username, email_address, password) VALUES
(1, 'user1', 'user1@example.com', 'password123'),
(2, 'user2', 'user2@example.com', 'password123'),
(3, 'user3', 'user3@example.com', 'password123');
INSERT INTO product(product_id, product_name, price, quantity, comment, user_id) VALUES
(1, 'Mleko', 3.50, 17, 'Pierwszy Produkt', 1),
(2, 'Chleb', 4.20, 34, 'Drugi Produkt', 1),
(3, 'Produkt3', 5.80, 17, 'Trzeci Produkt', 2),
(4, 'Produkt4', 17.60, 4, 'Czwarty Produkt', 1);

--CREATE SEQUENCE product_sequence minvalue 5;