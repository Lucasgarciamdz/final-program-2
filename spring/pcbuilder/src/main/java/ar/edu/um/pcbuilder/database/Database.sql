CREATE DATABASE IF NOT EXISTS pcbuilder;

USE pcbuilder;

CREATE TABLE Users
(
    id       SERIAL PRIMARY KEY,
    email    VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE Devices
(
    id                    SERIAL PRIMARY KEY,
    code                  VARCHAR(50) UNIQUE NOT NULL,
    name                  VARCHAR(100)       NOT NULL,
    description           TEXT               NOT NULL,
    base_price            DECIMAL(10, 2)     NOT NULL,
    currency              VARCHAR(3)         NOT NULL,
    stock                 INT                NOT NULL DEFAULT 0,
    supplier_device_number VARCHAR(50)       NOT NULL
);

CREATE TABLE DevicesCharacteristic
(
    id          SERIAL PRIMARY KEY,
    device_id   INT REFERENCES Devices (id) ON DELETE CASCADE ON UPDATE CASCADE,
    name        VARCHAR(100) NOT NULL,
    description TEXT         NOT NULL
);

CREATE TABLE DevicesPersonalization
(
    id          SERIAL PRIMARY KEY,
    device_id   INT REFERENCES Devices (id) ON DELETE CASCADE ON UPDATE CASCADE,
    name        VARCHAR(100) NOT NULL,
    description TEXT         NOT NULL
);

CREATE TABLE PersonalizationsOption
(
    id                 SERIAL PRIMARY KEY,
    personalization_id INT REFERENCES DevicesPersonalization (id) ON DELETE CASCADE ON UPDATE CASCADE,
    code               VARCHAR(50)    NOT NULL,
    name               VARCHAR(100)   NOT NULL,
    description        TEXT           NOT NULL,
    additional_price   DECIMAL(10, 2) NOT NULL
);

CREATE TABLE AdditionalItem
(
    id          SERIAL PRIMARY KEY,
    device_id   INT REFERENCES Devices (id) ON DELETE CASCADE ON UPDATE CASCADE,
    name        VARCHAR(100)   NOT NULL,
    description TEXT           NOT NULL,
    price       DECIMAL(10, 2) NOT NULL,
    free_price  DECIMAL(10, 2) DEFAULT -1
);

CREATE TABLE Sales
(
    id          SERIAL PRIMARY KEY,
    user_id     INT REFERENCES Users (id) ON DELETE CASCADE ON UPDATE CASCADE,
    device_id   INT REFERENCES Devices (id) ON DELETE CASCADE ON UPDATE CASCADE,
    final_price DECIMAL(10, 2) NOT NULL,
    sale_date   TIMESTAMP      NOT NULL
);

CREATE TABLE SalesPersonalization
(
    id                        SERIAL PRIMARY KEY,
    sale_id                   INT REFERENCES Sales (id) ON DELETE CASCADE ON UPDATE CASCADE,
    personalization_option_id INT REFERENCES PersonalizationsOption (id) ON DELETE CASCADE ON UPDATE CASCADE,
    price                     DECIMAL(10, 2) NOT NULL
);

CREATE TABLE SalesAdditionalItem
(
    id                 SERIAL PRIMARY KEY,
    sale_id            INT REFERENCES Sales (id) ON DELETE CASCADE ON UPDATE CASCADE,
    additional_item_id INT REFERENCES AdditionalItem (id) ON DELETE CASCADE ON UPDATE CASCADE,
    price              DECIMAL(10, 2) NOT NULL
);