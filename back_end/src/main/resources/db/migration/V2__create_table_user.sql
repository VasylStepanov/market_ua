CREATE TABLE user_data.users(
    id INT CONSTRAINT user_id_pk PRIMARY KEY,
    login VARCHAR(32) NOT NULL,
    password VARCHAR(128) NOT NULL,
    CONSTRAINT login_unique UNIQUE(login)
)