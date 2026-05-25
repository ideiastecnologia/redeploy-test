CREATE TABLE leads (
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    email       VARCHAR(255) NOT NULL,
    message     TEXT,
    created_at  TIMESTAMP NOT NULL DEFAULT NOW()
);
