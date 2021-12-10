DELETE FROM customers;
SELECT setval('customers_id_seq', 1, false );
INSERT INTO "customers" (name, phone, password)
VALUES ('Semen', '999-99-99', 'testPass1'),
       ('Alexandr','777-77-77', 'testPass2'),
       ('Maxim', '111-11-11', 'testPass3');