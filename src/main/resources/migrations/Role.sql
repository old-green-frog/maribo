CREATE TABLE IF NOT EXISTS Role (
    id SERIAL PRIMARY KEY,
    name VARCHAR(20)
);

INSERT INTO Role(id, name) VALUES(1, 'User') ON CONFLICT DO NOTHING;
INSERT INTO Role(id, name) VALUES(2, 'Admin') ON CONFLICT DO NOTHING;