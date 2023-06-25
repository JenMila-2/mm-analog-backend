--Users
INSERT INTO users (id, name, username, email, password, enabled)
VALUES
    (100, 'Pedro Perez', 'Pedro20','pedro@mail.com', '$2a$12$rBImbI7IECYBlcDSdUIXu.m4yPC8q15W1N4q5BaZtF0z2H6dRjaE2', true),
    (101, 'Marisol Lopez', 'Marisol2023', 'marisol@mail.com', '$2a$12$YGKrv7J7xvuajKHXY/GtYOdyElM2riBOZgxBbhK1ZEOepfDvWAD5.', true),
    (102, 'Jensy Franco', 'Jensy2222', 'jensyhotography@mail.com', '$2a$12$rYbeEqmxx.ElKC5PimWc1OVccCAJwy4Qyki93yc8Y8cHjhGHuMpuu', true),
    (103, 'Scott Bennie', 'Benniezzzzzz80', 'bennie@mail.com', '$2a$12$h8YyS5u4hMHCC8E7tEMODetux9cZxZqgQf2UH/JVCfnK4kpdseusG', true);

INSERT INTO authorities (username, authority) VALUES ('Pedro20', 'ROLE_USER');
INSERT INTO authorities (username, authority) VALUES ('Marisol2023', 'ROLE_USER');
INSERT INTO authorities (username, authority) VALUES ('Benniezzzzzz80', 'ROLE_USER');
INSERT INTO authorities (username, authority) VALUES ('Jensy2222', 'ROLE_ADMIN');

--Film Stock Inventory user 100
INSERT INTO filmstockinventories (id, film_stock_name, remaining_rolls, brand, stock, format, iso, development_process, storage, rolls_shot, film_expiration_date, user_id)
VALUES
    (100, 'Professional T-Max 100', 5, 'Kodak', 'T-Max 100', '120 film', 100, 'B&W', 'Freezer', 3, '2024-01-01', 100),
    (101, 'Kodak Ultra Max', 7, 'Kodak', 'Ultra Max', '35mm', 400, 'C41', 'Freezer', 15, '2026-01-01', 100),
    (102, 'Kodak Gold', 12, 'Kodak', 'Gold 200', '35mm', 200, 'C41', 'Freezer', 9, '2025-04-29', 100),
    (103, 'Cinestill Xpro 50D', 1, 'CineStill', 'Xpro 50D', '35mm', 50, 'C41', 'Freezer', 1, '2026-02-01', 100),
    (104, 'AgfaPhoto APX 100', 4, 'Ilford', ' AgfaPhoto', '35mm', 100, 'B&W', 'Freezer', 3, '2023-11-01', 100);

--Film Stock Inventory user 101
INSERT INTO filmstockinventories (id, film_stock_name, remaining_rolls, brand, stock, format, iso, development_process, storage, rolls_shot, film_expiration_date, user_id)
VALUES
    (105, 'Kodak Portra 160', 4, 'Kodak', 'Portra 160', '35mm', 160, 'C41', 'Freezer', 2, '2024-06-01', 101),
    (106, 'Kodak Portra 400', 8, 'Kodak', 'Portra 400 (120 film)', '120 film', 400, 'C41', 'Freezer', 2, '2024-02-01', 101),
    (107, 'Kodak Gold', 8, 'Kodak', 'Gold 200', '35mm', 200, 'C41', 'Freezer', 5, '2025-06-01', 101),
    (108, 'CineStill 800T', 4, 'CineStill', 'CS 800T', '35mm', 800, 'C41', 'Freezer', 2, '2025-05-01', 101),
    (109, 'Ilford HP5 Plus', 10, 'Ilford', 'HP5Plus', '35mm', 400, 'B&W', 'Freezer', 6, '2026-01-20', 101);

--Film Stock Inventory user 102
INSERT INTO filmstockinventories (id, film_stock_name, remaining_rolls, brand, stock, format, iso, development_process, storage, rolls_shot, film_expiration_date, user_id)
VALUES
    (110, 'Lomo Lady Grey 400', 4, 'Lomography', 'Lady Greay 400', '35mm', 400, 'B&W', 'Freezer', 1, '2023-06-16', 102),
    (111, 'CineStill 800T', 2, 'CineStill', 'CS 800T', '35mm', 800, 'C41', 'Freezer', 0, '2024-04-01', 102),
    (112, 'Ilford HP5 Plus', 8, 'Ilford', 'HP5Plus', '35mm', 400, 'B&W', 'Freezer', 4, '2023-02-01', 102),
    (113, 'Kodak Gold', 3, 'Kodak', 'Gold 200', '35mm', 200, 'C41', 'Freezer', 2, '2024-04-10', 102),
    (114, 'Ilford Delta 3200', 2, 'Ilford', 'Delta3200', '35mm', 3200, 'B&W', 'Freezer', 4, '2025-04-20', 102);

--Film Stock Inventory user 103
INSERT INTO filmstockinventories (id, film_stock_name, remaining_rolls, brand, stock, format, iso, development_process, storage, rolls_shot, film_expiration_date, user_id)
VALUES
    (115, 'Professional RO 400H 120 film', 6, 'Fujifilm', 'PRO 400H', '120 film', 400, 'C41', 'Freezer', 4, '2022-04-25', 103),
    (116, 'Lomography Color Negative 800', 4, ' Lomography ', ' 800 Color Negative', '35mm', 800, 'C41', 'Freezer', 6, '2024-04-20', 103),
    (117, 'CineStill BwXX ', 2, 'CineStill', ' CineStill BwXX ', '35mm', 200, 'B&W', 'Freezer', 0, '2023-12-01', 103),
    (118, 'Professional Kodak Tri-X 400 ', 8, 'Kodak', 'Tri-X 400', '35mm', 400, 'B&W', 'Freezer', 7, '2025-02-20', 103),
    (119, ' Professional Kodak Tri-X 400 ', 6, 'Kodak', 'Tri-X 400', '120 film', 400, 'B&W', 'Freezer', 9, '2026-02-01', 103);

--Project Folders
INSERT INTO projectfolders (id, project_title, project_concept, user_id)
VALUES
    (100, 'Portraits of my mother', 'Personal project reflecting on my relationship with my mother.', 100),
    (102, 'Night Garden', 'An extended project referencing sleepless nights, dealing with insomnia. Ongoing project.', 100),
    (103, 'The house of Goddesses', 'Celebrating womanhood. Honouring the important women in my life.', 101),
    (104, 'By the River', 'New project. Planning on shooting portraits and landscape photos for this project.', 101),
    (105, 'In between dreams', 'A poetic, photographic meditation on the subtlety of everyday life. Ongoing project.', 102),
    (106, 'The Constructed Self', 'Collection of self portraits.', 102),
    (107, 'At midnight', 'Photographing the streets of London by night.', 103),
    (108, 'Family Portraits', 'Photographers of different families. Ongoing project.', 103);

--Photo Logs
INSERT INTO photologs (id, photo_title, camera, film_stock, film_format, shot_at_iso, aperture, shutter_speed, exposure_compensation, date_taken, developed_by_lab, notes, user_id)
VALUES
    (100, 'Dreaming_1', 'Minolta SRT101','HP5Plus', '35mm', 100, 'f5.6', '1/60', '+1', '2023-01-01', 'Developed at home', 'Shot this at moms home in Amsterdam', 102);