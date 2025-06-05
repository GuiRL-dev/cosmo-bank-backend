CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE users (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL,
    cpf VARCHAR(11) UNIQUE NOT NULL,
    email VARCHAR(50) UNIQUE NOT NULL,
    number VARCHAR(11) UNIQUE NOT NULL,
    balance NUMERIC(19, 2) NOT NULL,
    general_score INTEGER NOT NULL,
    bank_score INTEGER NOT NULL,
    update_date TIMESTAMP NOT NULL,
    creation_date TIMESTAMP NOT NULL,
    image_filename VARCHAR(100),
    cpf_key_pix BOOLEAN NOT NULL,
    email_key_pix BOOLEAN NOT NULL,
    number_key_pix BOOLEAN NOT NULL
);