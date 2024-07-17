-- Insert dummy data into the product table
INSERT INTO site156.product (name, description, price, color, brand, category, availability, rating, weight, warranty)
VALUES
    ('Product 1', 'Description 1', 99.99, 'Red', 'Brand A', 'Category A', true, 4.5, '1kg', '1 year'),
    ('Product 2', 'Description 2', 199.99, 'Blue', 'Brand B', 'Category B', true, 4.8, '1.5kg', '2 years'),
    ('Product 3', 'Description 3', 299.99, 'Green', 'Brand C', 'Category A', true, 5.0, '2kg', '1 year'),
    ('Product 4', 'Description 4', 399.99, 'Yellow', 'Brand A', 'Category C', false, 3.5, '2.5kg', '3 years'),
    ('Product 5', 'Description 5', 499.99, 'Black', 'Brand D', 'Category B', true, 4.9, '3kg', '2 years'),
    ('Product 6', 'Description 6', 599.99, 'White', 'Brand E', 'Category C', true, 4.7, '3.5kg', '1 year'),
    ('Product 7', 'Description 7', 699.99, 'Orange', 'Brand F', 'Category A', true, 4.3, '4kg', '2 years'),
    ('Product 8', 'Description 8', 799.99, 'Purple', 'Brand G', 'Category B', false, 4.2, '4.5kg', '1 year'),
    ('Product 9', 'Description 9', 899.99, 'Pink', 'Brand H', 'Category C', true, 4.6, '5kg', '3 years'),
    ('Product 10', 'Description 10', 999.99, 'Grey', 'Brand I', 'Category A', true, 4.1, '5.5kg', '2 years');

-- Insert dummy data into the feature table
INSERT INTO site156.feature (name)
VALUES
    ('Feature 1'),
    ('Feature 2'),
    ('Feature 3'),
    ('Feature 4'),
    ('Feature 5'),
    ('Feature 6');

-- Insert dummy data into the product_images table
INSERT INTO site156.product_images (product_id, image_url)
VALUES
    (1, 'http://example.com/image1.jpg'),
    (1, 'http://example.com/image2.jpg'),
    (2, 'http://example.com/image3.jpg'),
    (3, 'http://example.com/image4.jpg'),
    (4, 'http://example.com/image5.jpg'),
    (5, 'http://example.com/image6.jpg'),
    (5, 'http://example.com/image7.jpg'),
    (6, 'http://example.com/image8.jpg'),
    (7, 'http://example.com/image9.jpg'),
    (8, 'http://example.com/image10.jpg'),
    (9, 'http://example.com/image11.jpg'),
    (10, 'http://example.com/image12.jpg');

-- Insert dummy data into the feature_map table
INSERT INTO site156.feature_map (feature_id, product_id)
VALUES
    (1, 1),
    (2, 1),
    (3, 2),
    (1, 3),
    (2, 3),
    (3, 4),
    (1, 5),
    (2, 6),
    (3, 7),
    (4, 8),
    (5, 9),
    (6, 10),
    (1, 2),
    (2, 4),
    (3, 5),
    (4, 6),
    (5, 7),
    (6, 8),
    (1, 9),
    (2, 10);