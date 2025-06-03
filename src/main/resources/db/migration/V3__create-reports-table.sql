CREATE TABLE reports (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    reported_id UUID,
    FOREIGN KEY (reported_id) REFERENCES users(id),
    whistleblower_id UUID,
    FOREIGN KEY (whistleblower_id) REFERENCES users(id),
    origin_transaction_id UUID,
    FOREIGN KEY (origin_transaction_id) REFERENCES transactions(id),
    reason VARCHAR(50) NOT NULL,
    description VARCHAR(250) NOT NULL,
    report_timestamp TIMESTAMP NOT NULL,
    status VARCHAR(50) NOT NULL
);