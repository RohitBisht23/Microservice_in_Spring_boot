-- Insert data into Orders table (without specifying id, assuming auto-increment)
INSERT INTO orders (total_price, order_status) VALUES
(100.0, 'PENDING'),
(250.5, 'CONFIRMED'),
(180.75, 'CANCELLED'),
(300.0, 'PENDING'),
(120.5, 'CONFIRMED'),
(220.25, 'CANCELLED'),
(150.0, 'PENDING'),
(400.0, 'CONFIRMED'),
(50.0, 'CANCELLED'),
(320.0, 'PENDING');




INSERT INTO order_items (order_id, product_id, quantity) VALUES
(1, 102, 1),
(1, 104, 2),
(1, 108, 4),
(1, 111, 6),
(1, 115, 8),

(2, 101, 1),
(2, 106, 3),
(2, 110, 5),
(2, 113, 7),
(2, 117, 9),

(3, 103, 2),
(3, 109, 5),
(3, 114, 7),
(3, 118, 9),

(4, 107, 4),
(4, 116, 8),

(5, 105, 3),
(5, 119, 10),

(6, 112, 6);