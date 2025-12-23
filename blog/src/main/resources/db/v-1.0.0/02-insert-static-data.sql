INSERT INTO users (user_id, username, email, password) VALUES
                                                           ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'john_doe', 'john@example.com', 'hashed_password_1'),
                                                           ('b0eebc99-9c0b-4ef8-bb6d-6bb9bd380a12', 'jane_smith', 'jane@example.com', 'hashed_password_2'),
                                                           ('c0eebc99-9c0b-4ef8-bb6d-6bb9bd380a13', 'alex_wong', 'alex@example.com', 'hashed_password_3');

INSERT INTO posts (post_id, title, content, created_at, updated_at, user_id) VALUES
                                                                                 ('d0eebc99-9c0b-4ef8-bb6d-6bb9bd380a14', 'Первая запись в блоге', 'Содержимое первой записи...',
                                                                                  NOW() - INTERVAL '2 days', NOW(), 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11'),
                                                                                 ('e0eebc99-9c0b-4ef8-bb6d-6bb9bd380a15', 'Вторая запись', 'Содержимое второй записи...',
                                                                                  NOW() - INTERVAL '1 day', NOW(), 'b0eebc99-9c0b-4ef8-bb6d-6bb9bd380a12'),
                                                                                 ('f0eebc99-9c0b-4ef8-bb6d-6bb9bd380a16', 'Третья запись', 'Содержимое третьей записи...',
                                                                                  NOW(), NOW(), 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11');

INSERT INTO comments (comment_id, text, created_at, user_id, post_id) VALUES
                                                                          ('10eebc99-9c0b-4ef8-bb6d-6bb9bd380a17', 'Отличный пост!', NOW() - INTERVAL '1 day',
                                                                           'b0eebc99-9c0b-4ef8-bb6d-6bb9bd380a12', 'd0eebc99-9c0b-4ef8-bb6d-6bb9bd380a14'),
                                                                          ('20eebc99-9c0b-4ef8-bb6d-6bb9bd380a18', 'Спасибо за информацию', NOW(),
                                                                           'c0eebc99-9c0b-4ef8-bb6d-6bb9bd380a13', 'd0eebc99-9c0b-4ef8-bb6d-6bb9bd380a14'),
                                                                          ('30eebc99-9c0b-4ef8-bb6d-6bb9bd380a19', 'Интересная точка зрения', NOW(),
                                                                           'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'e0eebc99-9c0b-4ef8-bb6d-6bb9bd380a15');