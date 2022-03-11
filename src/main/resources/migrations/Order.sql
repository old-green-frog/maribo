CREATE TABLE IF NOT EXISTS MariboOrder(
    id SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES MariboUser(id),
    status_id INTEGER REFERENCES OrderStatus(id)
)