CREATE TABLE user_data.userprofile(
    id INT CONSTRAINT userprofile_id_pk PRIMARY KEY,
    firstname VARCHAR(32),
    lastname VARCHAR(32),
    email VARCHAR(80) NOT NULL,
    phone_number VARCHAR(9),
    user_id INT NOT NULL,
    CONSTRAINT email_unique UNIQUE(email),
    CONSTRAINT phone_number_unique UNIQUE(phone_number),
    FOREIGN KEY(user_id) REFERENCES user_data.users(id) ON DELETE CASCADE
)