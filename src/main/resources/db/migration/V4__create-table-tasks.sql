CREATE TABLE tasks (
    id VARCHAR(36) NOT NULL PRIMARY KEY,
    name VARCHAR(250) NOT NULL,
    description VARCHAR(250),
    user_fk_id VARCHAR(36) REFERENCES users(id),
    started_at TIMESTAMP NOT NULL,
    ended_at TIMESTAMP NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP
)