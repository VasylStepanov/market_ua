CREATE TABLE user_data.users(
    id UUID CONSTRAINT user_id_pk PRIMARY KEY,
    login VARCHAR(32) NOT NULL,
    password VARCHAR(128) NOT NULL,
    role VARCHAR(16) NOT NULL,
    enabled BOOLEAN NOT NULL,
    CONSTRAINT login_unique UNIQUE(login)
)