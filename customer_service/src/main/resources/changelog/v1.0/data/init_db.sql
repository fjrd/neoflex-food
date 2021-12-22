DELETE FROM customers;
--SELECT setval('customers_id_seq', 1, false );
INSERT INTO "customers" (customer_id, name, phone, password)
VALUES ('8d1208fc-f401-496c-9cb8-483fef121234','Semen', '999-99-99', '$2b$10$9vj1MlxIUeP2SGXwSrqGa.qKKveGJRHQ1lmVoE01B/JxA2QwXp3kS'),
       ('cce54793-00b8-4830-8f3c-e4cb97b8fb70','Alexandr','777-77-77', '$2b$10$vVb7kHhoMGYOpa/3YvjkRufeppmpIKjCJoQ3FmLj2s2tAkw3d2Bea'),
       ('1234bf02-0ffa-4b52-b22f-a10e76e31cb5', 'Maxim', '111-11-11', '$2b$10$WjEhQn7fMVbjVoI2O7stGeBw5BuJ2KlgSkg7UujPuqFoQdRJZtr5G');