CREATE TABLE IF NOT EXISTS person (
    id VARCHAR(255) NOT NULL,
    'name' VARCHAR(255),
    gender VARCHAR(50),
    age INT,
    direction VARCHAR(255),
    phone VARCHAR(50),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS client (
    id VARCHAR(255) NOT NULL,
    personId VARCHAR(255) NOT NULL,
    password VARCHAR(255),
    status BOOLEAN,
    PRIMARY KEY (id),
    FOREIGN KEY (personId) REFERENCES person(id)
);
