CREATE TABLE User
(
    id       SERIAL PRIMARY KEY,
    login    VARCHAR(50) UNIQUE NOT NULL,
    email    VARCHAR(100)       NOT NULL,
    password VARCHAR(255)       NOT NULL,
    langKey  VARCHAR(5),
    isActive BOOLEAN DEFAULT FALSE
);

CREATE TABLE Device
(
    id          SERIAL PRIMARY KEY,
    codigo      VARCHAR(50) UNIQUE NOT NULL,
    nombre      VARCHAR(100)       NOT NULL,
    descripcion TEXT               NOT NULL,
    precioBase  DECIMAL(10, 2)     NOT NULL,
    moneda      VARCHAR(3)         NOT NULL
);

CREATE TABLE DeviceCharacteristic
(
    id          SERIAL PRIMARY KEY,
    deviceId    INT REFERENCES Device (id),
    nombre      VARCHAR(100) NOT NULL,
    descripcion TEXT         NOT NULL
);

CREATE TABLE DevicePersonalization
(
    id          SERIAL PRIMARY KEY,
    deviceId    INT REFERENCES Device (id),
    nombre      VARCHAR(100) NOT NULL,
    descripcion TEXT         NOT NULL
);

CREATE TABLE PersonalizationOption
(
    id                SERIAL PRIMARY KEY,
    personalizationId INT REFERENCES DevicePersonalization (id),
    codigo            VARCHAR(50)    NOT NULL,
    nombre            VARCHAR(100)   NOT NULL,
    descripcion       TEXT           NOT NULL,
    precioAdicional   DECIMAL(10, 2) NOT NULL
);

CREATE TABLE AdditionalItem
(
    id           SERIAL PRIMARY KEY,
    deviceId     INT REFERENCES Device (id),
    nombre       VARCHAR(100)   NOT NULL,
    descripcion  TEXT           NOT NULL,
    precio       DECIMAL(10, 2) NOT NULL,
    precioGratis DECIMAL(10, 2) DEFAULT -1
);

CREATE TABLE Sale
(
    id          SERIAL PRIMARY KEY,
    userId      INT REFERENCES User (id),
    deviceId    INT REFERENCES Device (id),
    precioFinal DECIMAL(10, 2) NOT NULL,
    fechaVenta  TIMESTAMP      NOT NULL
);

CREATE TABLE SalePersonalization
(
    id                      SERIAL PRIMARY KEY,
    saleId                  INT REFERENCES Sale (id),
    personalizationOptionId INT REFERENCES PersonalizationOption (id),
    precio                  DECIMAL(10, 2) NOT NULL
);

CREATE TABLE SaleAdditionalItem
(
    id               SERIAL PRIMARY KEY,
    saleId           INT REFERENCES Sale (id),
    additionalItemId INT REFERENCES AdditionalItem (id),
    precio           DECIMAL(10, 2) NOT NULL
);
