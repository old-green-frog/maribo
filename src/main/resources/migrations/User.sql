CREATE TABLE IF NOT EXISTS MariboUser(
    id SERIAL PRIMARY KEY,
    name VARCHAR(50),
    email VARCHAR(50),
    role_id integer REFERENCES role(id),
    password VARCHAR,
    birthday DATE
)
