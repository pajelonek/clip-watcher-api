CREATE TABLE IF NOT EXISTS EMPLOYEES
(
    id     INTEGER AUTO_INCREMENT,
    name   VARCHAR(100) NOT NULL,
    dept   VARCHAR(100) NOT NULL,
    salary DOUBLE       NOT NULL,
    PRIMARY KEY (id)
);