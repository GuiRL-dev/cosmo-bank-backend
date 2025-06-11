CREATE TABLE general_score (
    id UUID DEFAULT get_random_uuid() PRIMARY KEY,
    FOREIGN KEY (user_id) references users(id),
    reason VARCHAR(255) NOT NULL,
    changed_value NUMERIC NOT NULL,
    timestamp TIMESTAMP
);