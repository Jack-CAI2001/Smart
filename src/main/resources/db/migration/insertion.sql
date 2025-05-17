-- Catégories
INSERT INTO categories (name)
VALUES ('Fruits & Légumes'),
       ('Épicerie'),
       ('Boissons'),
       ('Hygiène');

-- Produits
INSERT INTO products (name, description, price, category_id, image_url)
VALUES ('Pommes', 'Pommes rouges bio', 2.99, 1, 'https://example.com/images/pommes.jpg'),
       ('Pâtes', 'Pâtes complètes 500g', 1.49, 2, 'https://example.com/images/pates.jpg'),
       ('Jus d''orange', 'Jus 100% pur jus 1L', 3.20, 3, 'https://example.com/images/jus.jpg'),
       ('Shampoing', 'Shampoing doux 250ml', 4.99, 4, 'https://example.com/images/shampoing.jpg');

-- Utilisateurs
INSERT INTO users (name, email, password, role)
VALUES ('Alice Martin', 'alice@example.com', 'hashed_password_1', 'CUSTOMER'),
       ('Bob Dupont', 'bob@example.com', 'hashed_password_2', 'CUSTOMER');

-- Favoris
INSERT INTO user_favorites (user_id, product_id)
VALUES (1, 1);
-- Alice aime les Pommes

-- Promotions
INSERT INTO promotions (title, description, start_date, end_date, discount_type, discount_value)
VALUES ('Promo été -20%', '20% de réduction sur les jus d''orange', '2025-06-01', '2025-06-30', 'PERCENTAGE', 20.0),
       ('Réduction pâtes', '0.50€ de remise immédiate', '2025-05-01', '2025-05-31', 'FIXED_AMOUNT', 0.50);

-- Produits en promotion
INSERT INTO product_promotions (product_id, promotion_id)
VALUES (3, 1), -- Jus d'orange
       (2, 2);
-- Pâtes

-- Article dans le panier d'Alice (user_id = 1)
INSERT INTO cart_items (user_id, product_id, quantity)
VALUES (1, 1, 3);
-- 3 Pommes pour Alice


-- Commande de Bob (user_id = 2)
INSERT INTO orders (user_id, status, total_amount)
VALUES (2, 'PENDING', 6.40);

-- Article dans la commande de Bob
INSERT INTO order_items (order_id, product_id, quantity, unit_price)
VALUES (1, 3, 2, 3.20); -- 2 Jus d'orange à 3.20€
