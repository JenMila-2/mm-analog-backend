--Users
INSERT INTO users (username, name, email, password, enabled)
VALUES
    ('Pedro20', 'Pedro Perez','pedro@mail.com', '$2a$12$rBImbI7IECYBlcDSdUIXu.m4yPC8q15W1N4q5BaZtF0z2H6dRjaE2', true),
    ('Marisol2023', 'Marisol Lopez', 'marisol@mail.com', '$2a$12$YGKrv7J7xvuajKHXY/GtYOdyElM2riBOZgxBbhK1ZEOepfDvWAD5.', true),
    ('Jensy2222', 'Jensy Franco', 'jensyphotography@mail.com', '$2a$12$rYbeEqmxx.ElKC5PimWc1OVccCAJwy4Qyki93yc8Y8cHjhGHuMpuu', true),
    ('Benniezzzzzz80', 'Scott Bennie', 'bennie@mail.com', '$2a$12$h8YyS5u4hMHCC8E7tEMODetux9cZxZqgQf2UH/JVCfnK4kpdseusG', true),
    ('Admin2', 'Administrator', 'admin@mail.com', '$2a$12$lzHg9K/PNrkjzUCJUUWol.ojbHuHwCbbNueirHxVWY5GKEGAId0vi', true),
    ('User2', 'Test User', 'user@mail.com', '$2a$12$ILqWKVyZIThtkt61gPCfLeQHuGuHFnxkC7CDSSAOCuCXjZOPfdopS', true);

INSERT INTO authorities (username, authority) VALUES ('Pedro20', 'ROLE_USER');
INSERT INTO authorities (username, authority) VALUES ('Marisol2023', 'ROLE_USER');
INSERT INTO authorities (username, authority) VALUES ('Benniezzzzzz80', 'ROLE_USER');
INSERT INTO authorities (username, authority) VALUES ('Jensy2222', 'ROLE_USER');
INSERT INTO authorities (username, authority) VALUES ('Admin2', 'ROLE_ADMIN');
INSERT INTO authorities (username, authority) VALUES ('User2', 'ROLE_USER');

--Film Stock Inventory user Pedro20
INSERT INTO filmstockinventories (id, film_stock_name, remaining_rolls, brand, stock, format, iso, development_process, storage, rolls_shot, film_expiration_date, username)
VALUES
    (100, 'Professional T-Max 100', 5, 'Kodak', 'T-Max 100', '120 film (Medium)', 100, 'Black & White', 'Freezer', 3, '2024-01-01', 'Pedro20'),
    (101, 'Kodak Ultra Max', 7, 'Kodak', 'Ultra Max', '35mm', 400, 'C-41 Color', 'Freezer', 15, '2026-01-01', 'Pedro20'),
    (102, 'Kodak Gold', 12, 'Kodak', 'Gold 200', '35mm', 200, 'C-41 Color', 'Freezer', 9, '2025-04-29', 'Pedro20'),
    (103, 'Cinestill Xpro 50D', 1, 'CineStill', 'Xpro 50D', '35mm', 50, 'C-41 Color', 'Freezer', 1, '2026-02-01', 'Pedro20'),
    (104, 'AgfaPhoto APX 100', 4, 'Ilford', ' AgfaPhoto', '35mm', 100, 'Black & White', 'Freezer', 3, '2023-11-01', 'Pedro20');

--Film Stock Inventory user Marisol2023
INSERT INTO filmstockinventories (id, film_stock_name, remaining_rolls, brand, stock, format, iso, development_process, storage, rolls_shot, film_expiration_date, username)
VALUES
    (105, 'Kodak Portra 160', 4, 'Kodak', 'Portra 160', '35mm', 160, 'C-41 Color', 'Freezer', 2, '2024-06-01', 'Marisol2023'),
    (106, 'Kodak Portra 400', 8, 'Kodak', 'Portra 400 (120 film)', '120 film (Medium)', 400, 'C-41 Color', 'Freezer', 2, '2024-02-01', 'Marisol2023'),
    (107, 'Kodak Gold', 8, 'Kodak', 'Gold 200', '35mm', 200, 'C-41 Color', 'Freezer', 5, '2025-06-01', 'Marisol2023'),
    (108, 'CineStill 800T', 4, 'CineStill', 'CS 800T', '35mm', 800, 'C-41 Color', 'Freezer', 2, '2025-05-01', 'Marisol2023'),
    (109, 'Ilford HP5 Plus', 10, 'Ilford', 'HP5Plus', '35mm', 400, 'Black & White', 'Freezer', 6, '2026-01-20', 'Marisol2023');

--Film Stock Inventory user Jensy2222
INSERT INTO filmstockinventories (id, film_stock_name, remaining_rolls, brand, stock, format, iso, development_process, storage, rolls_shot, film_expiration_date, username)
VALUES
    (110, 'Lomo Lady Grey 400', 4, 'Lomography', 'Lady Grey 400', '35mm', 400, 'Black & White', 'Freezer', 1, '2023-06-16', 'Jensy2222'),
    (111, 'CineStill 800T', 2, 'CineStill', 'CS 800T', '35mm', 800, 'C-41 Color', 'Freezer', 0, '2024-04-01', 'Jensy2222'),
    (112, 'Ilford HP5 Plus', 8, 'Ilford', 'HP5Plus', '35mm', 400, 'Black & White', 'Freezer', 4, '2023-02-01', 'Jensy2222'),
    (113, 'Kodak Gold', 3, 'Kodak', 'Gold 200', '35mm', 200, 'C-41 Color', 'Freezer', 2, '2024-04-10', 'Jensy2222'),
    (114, 'Ilford Delta 3200', 2, 'Ilford', 'Delta3200', '35mm', 3200, 'Black & White', 'Freezer', 4, '2025-04-20', 'Jensy2222');

--Film Stock Inventory user Benniezzzzzz80
INSERT INTO filmstockinventories (id, film_stock_name, remaining_rolls, brand, stock, format, iso, development_process, storage, rolls_shot, film_expiration_date, username)
VALUES
    (115, 'Professional RO 400H 120 film', 6, 'Fujifilm', 'PRO 400H', '120 film (Medium)', 400, 'C-41 Color', 'Freezer', 4, '2022-04-25', 'Benniezzzzzz80'),
    (116, 'Lomography Color Negative 800', 4, 'Lomography', ' 800 Color Negative', '35mm', 800, 'C-41 Color', 'Freezer', 6, '2024-04-20', 'Benniezzzzzz80'),
    (117, 'CineStill BwXX ', 2, 'CineStill', 'CineStill BwXX ', '35mm', 200, 'Black & White', 'Freezer', 0, '2023-12-01', 'Benniezzzzzz80'),
    (118, 'Professional Kodak Tri-X 400 ', 8, 'Kodak', 'Tri-X 400', '35mm', 400, 'Black & White', 'Freezer', 7, '2025-02-20', 'Benniezzzzzz80'),
    (119, 'Professional Kodak Tri-X 400 ', 6, 'Kodak', 'Tri-X 400', '120 film (Medium)', 400, 'Black & White', 'Freezer', 9, '2026-02-01', 'Benniezzzzzz80');

--Film Stock Inventory user User2
INSERT INTO filmstockinventories (id, film_stock_name, remaining_rolls, brand, stock, format, iso, development_process, storage, rolls_shot, film_expiration_date, username)
VALUES
    (120, 'Kodak Gold', 8, 'Kodak', 'Gold 200', '35mm', 200, 'C-41 Color', 'Freezer', 4, '2023-04-25', 'User2'),
    (121, 'Kodak Portra 400', 4, 'Kodak', 'Portra 400', '35mm', 400, 'C-41 Color', 'Freezer', 6, '2024-04-20', 'User2'),
    (122, 'Ilford HP5 Plus', 6, 'Ilford', 'HP5Plus', '35mm', 400, 'Black & White', 'Freezer', 10, '2025-01-20', 'User2'),
    (123, 'Kodak Ultra Max', 5, 'Kodak', 'Ultra Max', '35mm', 400, 'C-41 Color', 'Freezer', 9, '2026-01-01', 'User2'),
    (124, 'Lomo Lady Grey 400', 6, 'Lomography', 'Lady Grey 400', '35mm', 400, 'Black & White', 'Freezer', 3, '2023-05-01', 'User2');

--Project Folders
INSERT INTO projectfolders (id, project_title, project_concept, username)
VALUES
    (100, 'Portraits of my mother', 'Personal project reflecting on my relationship with my mother.', 'Pedro20'),
    (102, 'Night Garden', 'An extended project referencing sleepless nights, dealing with insomnia. Ongoing project.', 'Pedro20'),
    (103, 'The house of Goddesses', 'Celebrating womanhood. Honouring the important women in my life.', 'Marisol2023'),
    (104, 'By the River', 'New project. Planning on shooting portraits and landscape photos for this project.', 'Marisol2023'),
    (105, 'In between dreams', 'A poetic, photographic meditation on the subtlety of everyday life. Ongoing project.', 'Jensy2222'),
    (106, 'The Constructed Self', 'Collection of self portraits.', 'Jensy2222'),
    (107, 'At midnight', 'Photographing the streets of London by night.', 'Benniezzzzzz80'),
    (108, 'Family Portraits', 'Photographers of different families. Ongoing project.', 'Benniezzzzzz80'),
    (109, 'Project color', 'Collection of photos shot with Kodak Portra 400.', 'User2'),
    (110, 'Project B&W', 'Collection of B&W portraits.', 'User2');

--Photo Logs user Pedro20
INSERT INTO photologs (id, title, camera, stock, iso, aperture, shutter_speed, exposure_compensation, date_taken, notes, folder_id, username)
VALUES
    (100, 'Portrait_Mom_1', 'Minolta X-570','Kodak Portra 400', 400, 'f/4', '1/60', '+1', '2022-10-01', 'Portrait of mom', 100, 'Pedro20'),
    (101, 'Portrait_Mom_2', 'Minolta X-570','Kodak Portra 400', 400, 'f/4', '1/60', '+1', '2022-10-01', 'Portrait of mom', 100, 'Pedro20'),
    (102, 'Portrait_Mom_3', 'Minolta X-570','Kodak Portra 400', 400, 'f/4', '1/60', '+1', '2022-10-01', 'Portrait of mom', 100, 'Pedro20'),
    (103, 'Portrait_Mom_4', 'Minolta X-570','Kodak Portra 400', 400, 'f/4', '1/60', '+1', '2022-10-01', 'Portrait of mom', 100, 'Pedro20'),
    (104, 'Portrait_Mom_5', 'Minolta X-570','Kodak Portra 400', 400, 'f/4', '1/60', '+1', '2022-10-01', 'Portrait of mom', 100, 'Pedro20');

--Photo Logs user Marisol2023
INSERT INTO photologs (id, title, camera, stock, iso, aperture, shutter_speed, exposure_compensation, date_taken, notes, folder_id, username)
VALUES
    (105, 'Goddess_1', 'Nikon F6','Kodak Portra 800', 800, 'f/4', '1/60', '0', '2022-06-10', 'Portrait of Jenny', 103, 'Marisol2023'),
    (106, 'Goddess_2', 'Nikon F6','Kodak Portra 800', 800, 'f/4', '1/60', '+1', '2022-07-01', 'Portrait of Nicolle', 103, 'Marisol2023'),
    (107, 'Goddess_3', 'Nikon F6','Kodak Portra 800', 800, 'f/4', '1/60', '0', '2023-02-01', 'Portrait of Nathalie', 103, 'Marisol2023'),
    (108, 'Goddess_4', 'Nikon F6','Kodak Portra 800', 800, 'f/5.6', '1/60', '0', '2023-02-01', 'Portrait of Solange', 103, 'Marisol2023'),
    (109, 'Goddess_5', 'Nikon F6','Kodak Portra 800', 800, 'f/5.6', '1/60', '0', '2023-03-14', 'Portrait of Sharine', 103, 'Marisol2023');

--Photo Logs user Jensy2222
INSERT INTO photologs (id, title, camera, stock, iso, aperture, shutter_speed, exposure_compensation, date_taken, notes, folder_id, username)
VALUES
    (110, 'Dreaming_1', 'Minolta SRT101','Kodak Gold 200', 200, 'f/5.6', '1/60', '+1', '2023-01-01', 'Shot this at moms home in Amsterdam', 105, 'Jensy2222'),
    (111, 'Dreaming_2', 'Minolta SRT101','Kodak Gold 200', 200, 'f/4', '1/60', '+1', '2023-01-10', 'Re-take this image but in B&W', 105, 'Jensy2222'),
    (112, 'Dreaming_3', 'Minolta SRT101','Ilford Delta 3200', 1000, 'f/8', '1/60', '0', '2023-04-14', 'Love the look of the Delta 3200. I need to include this film stock more in my projects.', 105, 'Jensy2222'),
    (113, 'Dreaming_4', 'Minolta SRT101','Ilford Delta 3200', 1000, 'f/8', '1/60', '0', '2023-05-21', 'Shot at home during a Sunday morning', 105, 'Jensy2222'),
    (114, 'Dreaming_5', 'Minolta SRT101','Ilford Delta 3200', 1000, 'f/5.6', '1/60', '0', '2023-05-21', 'Sunday morning at home photo shoot session', 105, 'Jensy2222');

--Photo Logs user Benniezzzzzz80
INSERT INTO photologs (id, title, camera, stock, iso, aperture, shutter_speed, exposure_compensation, date_taken, notes, folder_id, username)
VALUES
    (115, 'Night_1', 'Nikon F3','CineStill 800T', 800, 'f/8', '1/60', '+2', '2023-03-01', 'Night photo walk', 107, 'Benniezzzzzz80'),
    (116, 'Night_2', 'Nikon F3','CineStill 800T', 800, 'f/8', '1/60', '+2', '2023-03-01', 'Night photo walk', 107, 'Benniezzzzzz80'),
    (117, 'Night_3', 'Nikon F3','CineStill 800T', 800, 'f/8', '1/60', '+2', '2023-03-01', 'Night photo walk', 107, 'Benniezzzzzz80'),
    (118, 'Night_4', 'Nikon F3','CineStill 800T', 800, 'f/8', '1/60', '+2', '2023-03-01', 'Night photo walk', 107, 'Benniezzzzzz80'),
    (119, 'Night_5', 'Nikon F3','CineStill 800T', 800, 'f/8', '1/60', '+2', '2023-03-01', 'Night photo walk', 107, 'Benniezzzzzz80');

--Photo Logs user User2
INSERT INTO photologs (id, title, camera, stock, iso, aperture, shutter_speed, exposure_compensation, date_taken, notes, folder_id, username)
VALUES
    (121, 'Color_2', 'Canon AE-1','Kodak Ultra Max', 400, 'f/8', '1/60', '0', '2023-04-01', 'Shot on a trip to Paris', 109, 'User2'),
    (122, 'Color_3', 'Canon AE-1','Kodak Ultra Max', 400, 'f/5.6', '1/60', '0', '2023-04-01', 'Shot on a trip to Paris', 109, 'User2'),
    (123, 'Color_4', 'Canon AE-1','Kodak Ultra Max', 400, 'f/8', '1/60', '0', '2023-04-01', 'Shot on a trip to Paris', 109, 'User2'),
    (124, 'Color_5', 'Canon AE-1','Kodak Ultra Max', 400, 'f/8', '1/60', '0', '2023-04-01', 'Shot on a trip to Paris', 109, 'User2');


INSERT INTO files (file_name, content_type, url)
VALUES
    ('Dasha_Urvachova_1.jpg', 'image/jpeg', 'http://localhost:8080/download/Dasha_Urvachova_1.jpg');

INSERT INTO photologs (id, title, camera, stock, iso, aperture, shutter_speed, exposure_compensation, date_taken, notes, file_file_name, folder_id, username)
VALUES
    (120, 'Color_1', 'Canon AE-1','Kodak Ultra Max', 400, 'f/5.6', '1/60', '+1', '2023-04-01', 'Shot on a trip to Paris', 'Dasha_Urvachova_1.jpg', 109, 'User2');

--Film Development logs user Pedro20
INSERT INTO filmdevelopmentlogs (id, roll_name, project, camera, stock, format, shot_at_iso, development_process, status, roll_started, roll_finished, exposed, developed, scanned, developed_by_lab, username)
VALUES
    (100, 'Roll 100 - Kodak Portra 800', 'Varia, no specific project','Minolta X-57', 'Kodak Portra 800', '35mm', 800, 'C-41 Color', 'Done', '2023-04-01', '2023-04-20', true, true, true, 'Ontwikkelservice Foto Verweij', 'Pedro20'),
    (101, 'Roll 101 - HP5 Plus', 'Street photos in B&W','Minolta X-57', 'HP5 Plus', '35mm', 400, 'Black & White', 'Done', '2023-02-01', '2023-04-30', true, true, true, 'Ontwikkelservice Foto Verweij', 'Pedro20');

--Film Development logs user Marisol2023
INSERT INTO filmdevelopmentlogs (id, roll_name, project, camera, stock, format, shot_at_iso, development_process, status, roll_started, roll_finished, exposed, developed, scanned, developed_by_lab, username)
VALUES
    (102, 'Roll 1 - By the River Project', 'By the river','Nikon F6', 'Lady Grey 400', '35mm', 400, 'Black & White', 'Done', '2023-01-21', '2023-03-10', true, true, true, 'Ontwikkelservice Foto Verweij', 'Marisol2023'),
    (103, 'Roll 2 - By the River Project', 'By the River','Nikon F6', 'HP5 Plus', '35mm', 400, 'Black & White', 'Done', '2023-02-01', '2023-04-30', true, true, false, 'Ontwikkelservice Foto Verweij', 'Marisol2023');

--Film Development logs user Jensy2222
INSERT INTO filmdevelopmentlogs (id, roll_name, project, camera, stock, format, shot_at_iso, development_process, status, roll_started, roll_finished, exposed, developed, scanned, developed_by_lab, username)
VALUES
    (104, 'Roll 20 - Kodak Ultra Max', 'New project','Minolta Riva Zoom 115', 'Kodak Ultra Max', '35mm', 400, 'C-41 Color', 'Done', '2023-03-01', '2023-03-31', true, true, true, 'Ontwikkelservice Foto Verweij', 'Jensy2222'),
    (105, 'Roll 21 - Kodak Ultra Max', 'New project','Minolta Riva Zoom 115', 'Kodak Ultra Max', '35mm', 400, 'C-41 Color', 'Done', '2023-04-01', '2023-04-20', true, true, true, 'Ontwikkelservice Foto Verweij', 'Jensy2222');

--Film Development logs user Benniezzzzzz80
INSERT INTO filmdevelopmentlogs (id, roll_name, project, camera, stock, format, shot_at_iso, development_process, status, roll_started, roll_finished, exposed, developed, scanned, developed_by_lab, username)
VALUES
    (106, 'Test roll 1 - Cinestill Xpro 50D', 'Testing new film','Nikon F3', 'Cinestill Xpro 50D', '35mm', 50, 'C-41 Color', 'Done', '2023-06-01', '2023-06-15', true, true, true, 'Fotowereld Utrecht', 'Benniezzzzzz80'),
    (107, 'Test roll 2 - Cinestill Xpro 50D', 'testing new film','Nikon F3', 'Cinestill Xpro 50D', '35mm', 50, 'C-41 Color', 'Done', '2023-06-16', '2023-06-30', true, true, true, 'Fotowereld Utrecht', 'Benniezzzzzz80');

--Film Development logs user User2
INSERT INTO filmdevelopmentlogs (id, roll_name, project, camera, stock, format, shot_at_iso, development_process, status, roll_started, roll_finished, exposed, developed, scanned, developed_by_lab, username)
VALUES
    (108, 'Roll 10 - Kodak Portra', 'Project Color','Canon AE-1', 'Kodak Portra 400', '35mm', 400, 'C-41 Color', 'Done', '2023-04-01', '2023-05-01', true, true, true, 'Ontwikkelservice Foto Verweij', 'User2'),
    (109, 'Roll 11 - Kodak Portra', 'Project Color','Canon AE-1', 'Kodak Portra 400', '35mm', 400, 'C-41 Color', 'Done', '2023-05-16', '2023-05-28', true, false, false, 'Ontwikkelservice Foto Verweij', 'User2');