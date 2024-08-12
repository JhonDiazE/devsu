CREATE TABLE IF NOT EXISTS account (
    number VARCHAR(255) NOT NULL,
    'type' VARCHAR(255),
    initialBalance DOUBLE,
    status BOOLEAN,
    PRIMARY KEY (numeroCuenta)
    );

CREATE TABLE IF NOT EXISTS movement (
                                          id BIGINT AUTO_INCREMENT,
                                          timestamp TIMESTAMP,
                                          type VARCHAR(255),
    amount DOUBLE,
    balance DOUBLE,
    PRIMARY KEY (id)
    );
