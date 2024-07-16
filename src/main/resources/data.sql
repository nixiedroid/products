-- Insert dummy data into the product table
INSERT INTO site156.product (name, description, price, color, brand, category, availability, rating, image_url, weight, warranty)
VALUES
    ('Product A', 'Description for product A', 19.99, 'Red', 'Brand A', 'Category 1', true, 4.5, 'http://example.com/imageA.jpg', '15 г' ,  '12 месяцев'),
    ('Product B', 'Description for product B', 29.99, 'Blue', 'Brand B', 'Category 2', false, 4.0, 'http://example.com/imageB.jpg', '20 г',  '24 месяцев'),
    ('Product C', 'Description for product C', 39.99, 'Green', 'Brand C', 'Category 1', true, 3.5, 'http://example.com/imageC.jpg', '3 кг',  '6 месяцев'),
    ('Product D', 'Description for product D', 49.99, 'Yellow', 'Brand D', 'Category 3', true, 5.0, 'http://example.com/imageD.jpg', '500 г',  '36 месяцев'),
    ('Product E', 'Description for product E', 59.99, 'Black', 'Brand E', 'Category 2', false, 4.8, 'http://example.com/imageE.jpg', '1 кг',  '12 месяцев');

-- Insert dummy data into the feature table
INSERT INTO site156.feature (name)
VALUES
    ('Feature 1'),
    ('Feature 2'),
    ('Feature 3'),
    ('Feature 4'),
    ('Feature 5');

-- Insert dummy data into the feature_map table to create relationships between products and features
INSERT INTO site156.feature_map (feature_id, product_id)
VALUES
    (1, 1),
    (2, 1),
    (3, 2),
    (4, 2),
    (5, 3),
    (1, 4),
    (2, 4),
    (3, 5),
    (4, 5),
    (5, 5);
