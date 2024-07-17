CREATE SCHEMA IF NOT EXISTS site156;
CREATE TABLE IF NOT EXISTS site156.product
(
    id           SERIAL PRIMARY KEY,
    name         VARCHAR(100)     NOT NULL,
    description  VARCHAR(100)     NOT NULL,
    price        DOUBLE PRECISION NOT NULL DEFAULT 0,
    color        VARCHAR(100)     NOT NULL,
    brand        VARCHAR(100)     NOT NULL,
    category     VARCHAR(100)     NOT NULL,
    availability BOOLEAN          NOT NULL DEFAULT false,
    rating       DOUBLE PRECISION NOT NULL DEFAULT 5.0,
    weight       VARCHAR(100)     NOT NULL,
    warranty VARCHAR(100)     NOT NULL
);

CREATE TABLE IF NOT EXISTS site156.feature
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS site156.product_images
(
    id        SERIAL PRIMARY KEY,
    product_id INT REFERENCES site156.product(id),
    image_url VARCHAR(255) NOT NULL
);

-- Many-to-many relationship table for Product and Feature
CREATE TABLE IF NOT EXISTS site156.feature_map
(
    feature_id INTEGER NOT NULL,
    product_id INTEGER NOT NULL,
    PRIMARY KEY (feature_id, product_id),
    FOREIGN KEY (feature_id) REFERENCES site156.feature (id),
    FOREIGN KEY (product_id) REFERENCES site156.product (id)
);

