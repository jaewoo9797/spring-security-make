CREATE TABLE IF NOT EXISTS users
(
    username
    varchar
(
    45
) NOT NULL ,
    password TEXT NULL,
    PRIMARY KEY
(
    username
)
    );

CREATE TABLE IF NOT EXISTS otp
(
    username
    varchar
(
    45
) NOT NULL,
    code varchar
(
    45
) NULL,
    PRIMARY KEY
(
    username
)
    );


