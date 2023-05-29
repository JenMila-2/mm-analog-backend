INSERT INTO projectfolders (id, project_title, project_note)
VALUES
    (1, 'In between dreams', 'A poetic, photographic meditation on the subtlety of everyday life. Ongoing project.'),
    (2, 'Faces', 'A collection of intimate portraits. Planning on shooting more portraits in 2023.'),
    (3, 'Free Spirits', 'This is a documentary project about youth and freedom.'),
    (4, 'Flower Memories', 'A recently started project about the beauty of flowers, plants and our connection to mother earth.'),
    (5, 'Night Garden', 'An extended project referencing sleepless nights, dealing with insomnia. Ongoing project.'),
    (6, 'The house of Goddesses', 'Celebrating womanhood. Honouring the important women in my life.'),
    (7, 'The Constructed Self', 'Collection of self portraits.'),
    (8, 'Portraits of my mother', 'Personal project reflecting on my relationship with my mother.'),
    (9, 'At midnight', 'Photographing the streets of Amsterdam by night.'),
    (10, 'Family Portraits', 'Photographers of different families. Ongoing project.'),
    (11, 'By the River', 'New project. Planning on shooting portraits and landscape photos for this project.'),
    (12, 'Mix', 'Photographs who are not part of a project yet.');

--Photos for project folder 1
INSERT INTO photos (id, photo_title, camera, film_stock, film_format, developed_by, iso, f_stop, shutter_speed, exposure_compensation, project_folder_id)
VALUES
    (10, 'Dreaming_1', 'Minolta SRT101', 'Kodak Portra 400', '35mm', 'Fotowereld Utrecht', 400, 'f8', '1/60', '+1', 1),
    (11, 'Dreaming_2', 'Minolta SRT101', 'Kodak Portra 400', '35mm', 'Fotowereld Utrecht', 400, 'f8', '1/60', '0', 1),
    (12, 'Dreaming_3', 'Minolta SRT101', 'Kodak Portra 400', '35mm', 'Fotowereld Utrecht', 400, 'f8', '1/60', '+1', 1),
    (13, 'Dreaming_4', 'Minolta SRT101', 'Kodak Portra 400', '35mm', 'Fotowereld Utrecht', 400, 'f5.6', '1/80', '0', 1),
    (14, 'Dreaming_5', 'Minolta SRT101', 'Kodak Portra 400', '35mm', 'Fotowereld Utrecht', 400, 'f4', '1/125', '0', 1),
    (15, 'Dreaming_6', 'Minolta SRT101', 'Kodak Gold', '35mm', 'Fotowereld Utrecht', 200, 'f5.6', '1/60', '0', 1),
    (16, 'Dreaming_7', 'Minolta SRT101', 'Kodak Gold', '35mm', 'Fotowereld Utrecht', 200, 'f4', '1/80', '+1', 1),
    (17, 'Dreaming_8', 'Minolta SRT101', 'Kodak Gold', '35mm', 'Fotowereld Utrecht', 200, 'f5.6', '1/125', '+1', 1);

--Photos for project folder 2
INSERT INTO photos (id, photo_title, camera, film_stock, film_format, developed_by, iso, f_stop, shutter_speed, exposure_compensation, project_folder_id)
VALUES
    (18, 'Faces_1', 'Mamiya M645', 'Ilford HP5 Plus', 'Medium - 120', 'Foto Verweij Nijmegen', 400, 'f4', '1/60', '0', 2),
    (19, 'Faces_2', 'Mamiya M645', 'Ilford HP5 Plus', 'Medium - 120', 'Foto Verweij Nijmegen', 400, 'f4', '1/60', '0', 2),
    (20, 'Faces_3', 'Mamiya M645', 'Ilford HP5 Plus', 'Medium - 120', 'Foto Verweij Nijmegen', 400, 'f4', '1/80', '+1', 2),
    (21, 'Faces_4', 'Mamiya M645', 'Ilford HP5 Plus', 'Medium - 120', 'Foto Verweij Nijmegen', 400, 'f2.8', '1/125', '+1', 2),
    (22, 'Faces_5', 'Mamiya M645', 'Ilford HP5 Plus', 'Medium - 120', 'Foto Verweij Nijmegen', 400, 'f2.8', '1/125', '+1', 2),
    (23, 'Faces_6', 'Mamiya M645', 'Ilford HP5 Plus', 'Medium - 120', 'Foto Verweij Nijmegen', 400, 'f2.8', '1/80', '+1', 2);

--Photos for project folder 3
INSERT INTO photos (id, photo_title, camera, film_stock, film_format, developed_by, iso, f_stop, shutter_speed, exposure_compensation, project_folder_id)
VALUES
    (24, 'Free_Spirits_1', 'Pentax 6x7', 'Kodak Gold', 'Medium - 120', 'Self developed at home', 200, 'f5.6', '1/60', '0', 3),
    (25, 'Free_Spirits_2', 'Pentax 6x7', 'Kodak Gold', 'Medium - 120', 'Self developed at home', 200, 'f5.6', '1/60', '+1', 3),
    (26, 'Free_Spirits_3', 'Pentax 6x7', 'Kodak Gold', 'Medium - 120', 'Self developed at home', 200, 'f4', '1/80', '+1', 3),
    (27, 'Free_Spirits_4', 'Pentax 6x7', 'Kodak Gold', 'Medium - 120', 'Self developed at home', 200, 'f5.6', '1/60', '0', 3),
    (28, 'Free_Spirits_5', 'Pentax 6x7', 'Kodak Gold', 'Medium - 120', 'Self developed at home', 200, 'f8', '1/125', '+1', 3),
    (29, 'Free_Spirits_6', 'Pentax 6x7', 'Kodak Gold', 'Medium - 120', 'Self developed at home', 200, 'f5.6', '1/60', '0', 3),
    (30, 'Free_Spirits_7', 'Pentax 6x7', 'Kodak Gold', 'Medium - 120', 'Self developed at home', 200, 'f11', '1/60', '+1', 3);

--Photos for project folder 4
INSERT INTO photos (id, photo_title, camera, film_stock, film_format, developed_by, iso, f_stop, shutter_speed, exposure_compensation, project_folder_id)
VALUES
    (31, 'Flowers_1', 'Pentax 6x7', 'Kodak Portra 400', 'Medium - 120', 'Fotowereld Utrecht', 400, 'f11', '1/60', '+1', 4),
    (32, 'Flowers_2', 'Pentax 6x7', 'Kodak Portra 400', 'Medium - 120', 'Fotowereld Utrecht', 400, 'f11', '1/60', '+1', 4),
    (33, 'Flowers_3', 'Pentax 6x7', 'Kodak Portra 400', 'Medium - 120', 'Fotowereld Utrecht', 400, 'f8', '1/60', '+1', 4),
    (34, 'Flowers_4', 'Pentax K2 Black', 'Kodak Portra 400', '35mm', 'Fotowereld Utrecht', 400, 'f5.6', '1/80', '0', 4),
    (35, 'Flowers_5', 'Pentax K2 Black', 'Kodak Portra 400', '35mm', 'Fotowereld Utrecht', 400, 'f5.6', '1/60', '0', 4),
    (36, 'Flowers_6', 'Pentax K2 Black', 'Kodak Portra 400', '35mm', 'Fotowereld Utrecht', 400, 'f2.8', '1/80', '-1', 4),
    (37, 'Flowers_7', 'Pentax K2 Black', 'Kodak Portra 400', '35mm', 'Fotowereld Utrecht', 400, 'f5.6', '1/80', '0', 4),
    (38, 'Flowers_8', 'Pentax K2 Black', 'Kodak Portra 400', '35mm', 'Fotowereld Utrecht', 400, 'f8', '1/80', '+1', 4);

--Photos for project folder 5
INSERT INTO photos (id, photo_title, camera, film_stock, film_format, developed_by, iso, f_stop, shutter_speed, exposure_compensation, project_folder_id)
VALUES
    (39, 'Sleepless_night_1', 'Canon AE-1 black', 'Ilford Delta 3200', '35mm', 'Self developed at home', 3200, 'f8', '1/60', '+1', 5),
    (40, 'Sleepless_night_2', 'Canon AE-1 black', 'Ilford Delta 3200', '35mm', 'Self developed at home', 3200, 'f8', '1/60', '+1', 5),
    (41, 'Sleepless_night_3', 'Canon AE-1 black', 'Ilford Delta 3200', '35mm', 'Self developed at home', 3200, 'f5.6', '1/60', '+1', 5),
    (42, 'Sleepless_night_4', 'Canon AE-1 black', 'Ilford Delta 3200', '35mm', 'Self developed at home', 3200, 'f2.8', '1/125', '0', 5),
    (43, 'Sleepless_night_5', 'Canon AE-1 black', 'Ilford Delta 3200', '35mm', 'Self developed at home', 3200, 'f2.8', '1/125', '0', 5),
    (44, 'Sleepless_night_6', 'Canon AE-1 black', 'Ilford Delta 3200', '35mm', 'Self developed at home', 3200, 'f2.8', '1/125', '0', 5),
    (45, 'Sleepless_night_7', 'Canon AE-1 black', 'Ilford Delta 3200', '35mm', 'Self developed at home', 3200, 'f4', '1/80', '0', 5),
    (46, 'Sleepless_night_8', 'Canon AE-1 black', 'Ilford Delta 3200', '35mm', 'Self developed at home', 3200, 'f4', '1/80', '0', 5),
    (47, 'Sleepless_night_9', 'Canon AE-1 black', 'Ilford Delta 3200', '35mm', 'Self developed at home', 3200, 'f4', '1/80', '0', 5),
    (48, 'Sleepless_night_10', 'Canon AE-1 black', 'Ilford Delta 3200', '35mm', 'Self developed at home', 3200, 'f4', '1/80', '0', 5);

--Photos for project folder 6
INSERT INTO photos (id, photo_title, camera, film_stock, film_format, developed_by, iso, f_stop, shutter_speed, exposure_compensation, project_folder_id)
VALUES
    (49, 'Goddess_1', 'Pentax 6x7', 'Ilford Delta 400', 'Medium - 120', 'Foto Verweij Nijmegen', 400, 'f2.8', '1/125', '0', 6),
    (50, 'Goddess_2', 'Pentax 6x7', 'Ilford Delta 400', 'Medium - 120', 'Foto Verweij Nijmegen', 400, 'f2.8', '1/125', '0', 6),
    (51, 'Goddess_3', 'Pentax 6x7', 'Ilford Delta 400', 'Medium - 120', 'Foto Verweij Nijmegen', 400, 'f2.8', '1/125', '0', 6),
    (52, 'Goddess_4', 'Pentax 6x7', 'Ilford Delta 400', 'Medium - 120', 'Foto Verweij Nijmegen', 400, 'f2.8', '1/125', '0', 6),
    (53, 'Goddess_5', 'Pentax 6x7', 'Kodak Portra 400', 'Medium - 120', 'Foto Verweij Nijmegen', 400, 'f2.8', '1/125', '+1', 6),
    (54, 'Goddess_6', 'Pentax 6x7', 'Kodak Portra 400', 'Medium - 120', 'Foto Verweij Nijmegen', 400, 'f2.8', '1/125', '+1', 6),
    (55, 'Goddess_7', 'Pentax 6x7', 'Kodak Portra 400', 'Medium - 120', 'Foto Verweij Nijmegen', 400, 'f2.8', '1/125', '+1', 6),
    (56, 'Goddess_8', 'Pentax 6x7', 'Kodak Portra 400', 'Medium - 120', 'Foto Verweij Nijmegen', 400, 'f2.8', '1/125', '+1', 6);

--Photos for project folder 7
INSERT INTO photos (id, photo_title, camera, film_stock, film_format, developed_by, iso, f_stop, shutter_speed, exposure_compensation, project_folder_id)
VALUES
    (57, 'Self_portrait_1', 'Mamiya M645', 'Kodak Portra 400', 'Medium - 120', 'Fotowereld Utrecht', 400, 'f5.6', '1/80', '0', 7),
    (58, 'Self_portrait_2', 'Mamiya M645', 'Kodak Portra 400', 'Medium - 120', 'Fotowereld Utrecht', 400, 'f5.6', '1/80', '0', 7),
    (59, 'Self_portrait_3', 'Mamiya M645', 'Kodak Portra 400', 'Medium - 120', 'Fotowereld Utrecht', 400, 'f8', '1/60', '+1', 7),
    (60, 'Self_portrait_4', 'Mamiya M645', 'Kodak Portra 400', 'Medium - 120', 'Fotowereld Utrecht', 400, 'f8', '1/60', '+1', 7),
    (61, 'Self_portrait_5', 'Mamiya M645', 'Kodak Portra 400', 'Medium - 120', 'Fotowereld Utrecht', 400, 'f8', '1/60', '+1', 7),
    (62, 'Self_portrait_6', 'Mamiya M645', 'Kodak Portra 400', 'Medium - 120', 'Fotowereld Utrecht', 400, 'f8', '1/60', '+1', 7);

--Photos for project folder 8
INSERT INTO photos (id, photo_title, camera, film_stock, film_format, developed_by, iso, f_stop, shutter_speed, exposure_compensation, project_folder_id)
VALUES
    (63, 'Mother_1', 'Pentax 6x7', 'Ilford HP5 Plus', 'Medium - 120', 'Self developed at home', 400, 'f4', '1/125', '0', 8),
    (64, 'Mother_2', 'Pentax 6x7', 'Ilford HP5 Plus', 'Medium - 120', 'Self developed at home', 400, 'f4', '1/125', '0', 8),
    (65, 'Mother_3', 'Pentax 6x7', 'Ilford HP5 Plus', 'Medium - 120', 'Self developed at home', 400, 'f4', '1/125', '0', 8),
    (66, 'Mother_4', 'Pentax 6x7', 'Ilford HP5 Plus', 'Medium - 120', 'Self developed at home', 400, 'f5.6', '1/80', '+1', 8),
    (67, 'Mother_5', 'Pentax 6x7', 'Ilford HP5 Plus', 'Medium - 120', 'Self developed at home', 400, 'f5.6', '1/80', '+1', 8),
    (68, 'Mother_6', 'Pentax 6x7', 'Ilford HP5 Plus', 'Medium - 120', 'Self developed at home', 400, 'f5.6', '1/80', '+1', 8),
    (69, 'Mother_7', 'Pentax 6x7', 'Ilford HP5 Plus', 'Medium - 120', 'Self developed at home', 400, 'f8', '1/60', '+1', 8),
    (70, 'Mother_8', 'Pentax 6x7', 'Ilford HP5 Plus', 'Medium - 120', 'Self developed at home', 400, 'f8', '1/60', '+1', 8);

--Photos for project folder 9
INSERT INTO photos (id, photo_title, camera, film_stock, film_format, developed_by, iso, f_stop, shutter_speed, exposure_compensation, project_folder_id)
VALUES
    (71, 'Midnight_Streets_1', 'Canon AE-1 black', 'Cinestill 800 Tungsten', '35mm', 'Key Color Fotolab Amsterdam', 800, 'f8', '1/60', '+1', 9),
    (72, 'Midnight_Streets_2', 'Canon AE-1 black', 'Cinestill 800 Tungsten', '35mm', 'Key Color Fotolab Amsterdam', 800, 'f8', '1/60', '+1', 9),
    (73, 'Midnight_Streets_3', 'Canon AE-1 black', 'Cinestill 800 Tungsten', '35mm', 'Key Color Fotolab Amsterdam', 800, 'f8', '1/60', '+1', 9),
    (74, 'Midnight_Streets_4', 'Canon AE-1 black', 'Cinestill 800 Tungsten', '35mm', 'Key Color Fotolab Amsterdam', 800, 'f8', '1/60', '+1', 9),
    (75, 'Midnight_Streets_5', 'Canon AE-1 black', 'Cinestill 800 Tungsten', '35mm', 'Key Color Fotolab Amsterdam', 800, 'f11', '1/60', '+2', 9),
    (76, 'Midnight_Streets_6', 'Canon AE-1 black', 'Cinestill 800 Tungsten', '35mm', 'Key Color Fotolab Amsterdam', 800, 'f11', '1/60', '+2', 9),
    (77, 'Midnight_Streets_7', 'Canon AE-1 black', 'Cinestill 800 Tungsten', '35mm', 'Key Color Fotolab Amsterdam', 800, 'f11', '1/60', '+2', 9),
    (78, 'Midnight_Streets_8', 'Canon AE-1 black', 'Cinestill 800 Tungsten', '35mm', 'Key Color Fotolab Amsterdam', 800, 'f11', '1/60', '+2', 9),
    (79, 'Midnight_Streets_9', 'Canon AE-1 black', 'Cinestill 800 Tungsten', '35mm', 'Key Color Fotolab Amsterdam', 800, 'f8', '1/60', '0', 9),
    (80, 'Midnight_Streets_10', 'Canon AE-1 black', 'Cinestill 800 Tungsten', '35mm', 'Key Color Fotolab Amsterdam', 800, 'f8', '1/60', '0', 9);

--Photos for project folder 10
INSERT INTO photos (id, photo_title, camera, film_stock, film_format, developed_by, iso, f_stop, shutter_speed, exposure_compensation, project_folder_id)
VALUES
    (81, 'Family_Lopez', 'Mamiya M645', 'Kodak Portra 400', 'Medium - 120', 'Foto Verweij Nijmegen', 400, 'f11', '1/125', '+1', 10),
    (82, 'Family_Mendez', 'Mamiya M645', 'Kodak Portra 400', 'Medium - 120', 'Foto Verweij Nijmegen', 400, 'f11', '1/125', '+1', 10),
    (83, 'Family_Smith', 'Mamiya M645', 'Kodak Portra 400', 'Medium - 120', 'Foto Verweij Nijmegen', 400, 'f11', '1/125', '+1', 10),
    (84, 'Family_Bakker', 'Mamiya M645', 'Kodak Portra 400', 'Medium - 120', 'Foto Verweij Nijmegen', 400, 'f11', '1/125', '+1', 10),
    (85, 'Family_van_Geelen', 'Mamiya M645', 'Kodak Portra 400', 'Medium - 120', 'Foto Verweij Nijmegen', 400, 'f11', '1/125', '+1', 10),
    (86, 'Family_Henery', 'Mamiya M645', 'Kodak Portra 400', 'Medium - 120', 'Foto Verweij Nijmegen', 400, 'f11', '1/125', '+1', 10),
    (87, 'Family_Reekers', 'Mamiya M645', 'Kodak Portra 400', 'Medium - 120', 'Foto Verweij Nijmegen', 400, 'f11', '1/125', '+1', 10),
    (88, 'Family_Rijnschot', 'Mamiya M645', 'Kodak Portra 400', 'Medium - 120', 'Foto Verweij Nijmegen', 400, 'f11', '1/125', '+1', 10);

--Photos for project folder 11
INSERT INTO photos (id, photo_title, camera, film_stock, film_format, developed_by, iso, f_stop, shutter_speed, exposure_compensation, project_folder_id)
VALUES
    (89, 'By_the_river_portrait_1', 'Linhof Super Technika V 4X5', 'Kodak Professional T-Max 400', 'Large - 4X5', 'Foto Verweij Nijmegen', 400, 'f5.6', '1/60', '0', 11),
    (90, 'By_the_river_portrait_2', 'Linhof Super Technika V 4X5', 'Kodak Professional T-Max 400', 'Large - 4X5', 'Foto Verweij Nijmegen', 400, 'f5.6', '1/60', '0', 11),
    (91, 'By_the_river_portrait_3', 'Linhof Super Technika V 4X5', 'Kodak Professional T-Max 400', 'Large - 4X5', 'Foto Verweij Nijmegen', 400, 'f5.6', '1/60', '0', 11),
    (92, 'By_the_river_landscape_1', 'Linhof Super Technika V 4X5', 'Kodak Professional T-Max 400', 'Large - 4X5', 'Foto Verweij Nijmegen', 400, 'f16', '1/125', '0', 11),
    (93, 'By_the_river_landscape_2', 'Linhof Super Technika V 4X5', 'Kodak Professional T-Max 400', 'Large - 4X5', 'Foto Verweij Nijmegen', 400, 'f16', '1/125', '0', 11),
    (94, 'By_the_river_landscape_3', 'Linhof Super Technika V 4X5', 'Kodak Professional T-Max 400', 'Large - 4X5', 'Foto Verweij Nijmegen', 400, 'f16', '1/125', '0', 11);

--Photos for project folder 12
INSERT INTO photos (id, photo_title, camera, film_stock, film_format, developed_by, iso, f_stop, shutter_speed, exposure_compensation, project_folder_id)
VALUES
    (95, 'Untitled_1', 'Contax G2', 'Kodak Ultra Max', '35mm', 'Self developed at home', 400, 'f8', '1/60', '+1', 12),
    (96, 'Untitled_2', 'Contax G2', 'Kodak Ultra Max', '35mm', 'Self developed at home', 400, 'f8', '1/60', '+1', 12),
    (97, 'Untitled_3', 'Contax G2', 'Kodak Ultra Max', '35mm', 'Self developed at home', 400, 'f5.6', '1/125', '0', 12),
    (98, 'Untitled_4', 'Contax G2', 'Kodak Ultra Max', '35mm', 'Self developed at home', 400, 'f5.6', '1/125', '0', 12),
    (99, 'Untitled_5', 'Contax G2', 'Kodak Ultra Max', '35mm', 'Self developed at home', 400, 'f4', '1/250', '0', 12),
    (100, 'Untitled_6', 'Contax G2', 'Kodak Ultra Max', '35mm', 'Self developed at home', 400, 'f3.5', '1/250', '0', 12);

--Photo Galleries
INSERT INTO photogalleries (id, photographer_name, short_bio, publish)
VALUES
    (1, 'Barry Allen', 'Hi, I am Barry. I am a film photographer based in New York.', true),
    (2, 'Jensy Franco', 'Dutch photographer based in Utrecht, the Netherlands. Passionate about film and instant photography.', true),
    (3, 'Marisol Mendez', 'Hi, I am Marisol. Welcome to my profile, I hope you enjoy checking out my work.', true),
    (4, 'Scott Bennie', 'I am a landscape photographer based in Scotland. When I am not photographing the beautiful landscapes of my hometown I am traveling across the world.', true),
    (5, 'Lucy Cho', 'Twenty-something year old photography student, based in Amsterdam. Gear of choice, my trusted Canon AE-1.', true),
    (6, 'Kevin Spratt', 'Portrait, Wedding, Nature photographer.', true);

--Users
INSERT INTO users (id, name, email, password, enabled, user_gallery_id)
VALUES
    (1, 'Barry Allen', 'barry@gmail.com', 'B23A@_Flash9', true, 1),
    (2, 'Jensy Franco', 'jenmilafran@gmail.com', '2@2J29SolER_56', true, 2),
    (3, 'Marisol', 'marisol-mendez@gmail.com', 'Mari_47_&99_0', true, 3),
    (4, 'Scott Bennie', 'bennie2@gmail.com', 'BS7355@@67&Sgh', true, 4),
    (5, 'Lucy Cho', 'lucy.cho@gmail.com', 'lC0&3921@@cho', true, 5),
    (6, 'Kevin Spratt', 'Spratt.Kevin@gmail.com', 'spratt@BHjkl90&2', true, 6);

INSERT INTO exploregalleries (id) VALUES (1);
