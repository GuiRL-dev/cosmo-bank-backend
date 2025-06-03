CREATE TABLE transactions (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    sender_id UUID,
    FOREIGN KEY (sender_id) REFERENCES users(id),
    receiver_id UUID,
    FOREIGN KEY (receiver_id) REFERENCES users(id),
    used_key VARCHAR(50) NOT NULL,
    amount NUMERIC NOT NULL,
    transaction_timestamp TIMESTAMP NOT NULL,
    status VARCHAR(50)
);