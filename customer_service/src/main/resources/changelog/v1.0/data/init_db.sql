DELETE FROM customers;
SELECT setval('customers_id_seq', 1, false );
INSERT INTO "customers" (name, phone, password)
VALUES ('Semen', '999-99-99', '$2b$10$9vj1MlxIUeP2SGXwSrqGa.qKKveGJRHQ1lmVoE01B/JxA2QwXp3kS'),
       ('Alexandr','777-77-77', '$2b$10$vVb7kHhoMGYOpa/3YvjkRufeppmpIKjCJoQ3FmLj2s2tAkw3d2Bea'),
       ('Maxim', '111-11-11', '$2b$10$WjEhQn7fMVbjVoI2O7stGeBw5BuJ2KlgSkg7UujPuqFoQdRJZtr5G');