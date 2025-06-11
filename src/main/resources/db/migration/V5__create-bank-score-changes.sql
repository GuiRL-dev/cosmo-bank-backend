CREATE TABLE bank_score (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    user_id UUID,
    FOREIGN KEY (user_id) references users(id),
    reason VARCHAR(255) NOT NULL,
    changed_value NUMERIC NOT NULL,
    timestamp TIMESTAMP
);