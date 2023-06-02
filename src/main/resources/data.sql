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
    (101, 'Barry Allen', 'barry@gmail.com', 'B23A@_Flash9', true, 1),
    (102, 'Jensy Franco', 'jenmilafran@gmail.com', '2@2J29SolER_56', true, 2),
    (103, 'Marisol', 'marisol-mendez@gmail.com', 'Mari_47_&99_0', true, 3),
    (104, 'Scott Bennie', 'bennie2@gmail.com', 'BS7355@@67&Sgh', true, 4),
    (105, 'Lucy Cho', 'lucy.cho@gmail.com', 'lC0&3921@@cho', true, 5),
    (106, 'Kevin Spratt', 'Spratt.Kevin@gmail.com', 'spratt@BHjkl90&2', true, 6);

--Project Folders
INSERT INTO projectfolders (id, project_title, project_note, user_folder_id)
VALUES
    (1, 'In between dreams', 'A poetic, photographic meditation on the subtlety of everyday life. Ongoing project.', 102),
    (2, 'Faces', 'A collection of intimate portraits. Planning on shooting more portraits in 2023.', 106),
    (3, 'Free Spirits', 'This is a documentary project about youth and freedom.', 101),
    (4, 'Flower Memories', 'A recently started project about the beauty of flowers, plants and our connection to mother earth.', 103),
    (5, 'Night Garden', 'An extended project referencing sleepless nights, dealing with insomnia. Ongoing project.', 101),
    (6, 'The house of Goddesses', 'Celebrating womanhood. Honouring the important women in my life.', 105),
    (7, 'The Constructed Self', 'Collection of self portraits.', 102),
    (8, 'Portraits of my mother', 'Personal project reflecting on my relationship with my mother.', 106),
    (9, 'At midnight', 'Photographing the streets of Amsterdam by night.', 104),
    (10, 'Family Portraits', 'Photographers of different families. Ongoing project.', 105),
    (11, 'By the River', 'New project. Planning on shooting portraits and landscape photos for this project.', 104),
    (12, 'Mix', 'Photographs who are not part of a project yet.', 103);

--Photos for project folder 1, user 102, photo gallery 2
INSERT INTO photos (id, photo_title, camera, film_stock, film_format, developed_by, iso, f_stop, shutter_speed, exposure_compensation, photo_gallery_id, project_folder_id, user_photo_id)
VALUES
    (10, 'Dreaming_1', 'Minolta SRT101', 'Kodak Portra 400', '35mm', 'Fotowereld Utrecht', 400, 'f8', '1/60', '+1', 2, 1, 102),
    (11, 'Dreaming_2', 'Minolta SRT101', 'Kodak Portra 400', '35mm', 'Fotowereld Utrecht', 400, 'f8', '1/60', '0', 2, 1, 102),
    (12, 'Dreaming_3', 'Minolta SRT101', 'Kodak Portra 400', '35mm', 'Fotowereld Utrecht', 400, 'f8', '1/60', '+1', 2, 1, 102),
    (13, 'Dreaming_4', 'Minolta SRT101', 'Kodak Portra 400', '35mm', 'Fotowereld Utrecht', 400, 'f5.6', '1/80', '0', 2, 1, 102),
    (14, 'Dreaming_5', 'Minolta SRT101', 'Kodak Portra 400', '35mm', 'Fotowereld Utrecht', 400, 'f4', '1/125', '0', 2, 1, 102),
    (15, 'Dreaming_6', 'Minolta SRT101', 'Kodak Gold', '35mm', 'Fotowereld Utrecht', 200, 'f5.6', '1/60', '0', 2, 1, 102),
    (16, 'Dreaming_7', 'Minolta SRT101', 'Kodak Gold', '35mm', 'Fotowereld Utrecht', 200, 'f4', '1/80', '+1', 2, 1, 102),
    (17, 'Dreaming_8', 'Minolta SRT101', 'Kodak Gold', '35mm', 'Fotowereld Utrecht', 200, 'f5.6', '1/125', '+1', 2, 1, 102);

--Photos for project folder 2, user 106
INSERT INTO photos (id, photo_title, camera, film_stock, film_format, developed_by, iso, f_stop, shutter_speed, exposure_compensation, project_folder_id, user_photo_id)
VALUES
    (18, 'Faces_1', 'Mamiya M645', 'Ilford HP5 Plus', 'Medium - 120', 'Foto Verweij Nijmegen', 400, 'f4', '1/60', '0', 2, 106),
    (19, 'Faces_2', 'Mamiya M645', 'Ilford HP5 Plus', 'Medium - 120', 'Foto Verweij Nijmegen', 400, 'f4', '1/60', '0', 2, 106),
    (20, 'Faces_3', 'Mamiya M645', 'Ilford HP5 Plus', 'Medium - 120', 'Foto Verweij Nijmegen', 400, 'f4', '1/80', '+1', 2, 106),
    (21, 'Faces_4', 'Mamiya M645', 'Ilford HP5 Plus', 'Medium - 120', 'Foto Verweij Nijmegen', 400, 'f2.8', '1/125', '+1', 2, 106),
    (22, 'Faces_5', 'Mamiya M645', 'Ilford HP5 Plus', 'Medium - 120', 'Foto Verweij Nijmegen', 400, 'f2.8', '1/125', '+1', 2, 106),
    (23, 'Faces_6', 'Mamiya M645', 'Ilford HP5 Plus', 'Medium - 120', 'Foto Verweij Nijmegen', 400, 'f2.8', '1/80', '+1', 2, 106);

--Photos for project folder 3, user 101
INSERT INTO photos (id, photo_title, camera, film_stock, film_format, developed_by, iso, f_stop, shutter_speed, exposure_compensation, project_folder_id, user_photo_id)
VALUES
    (24, 'Free_Spirits_1', 'Pentax 6x7', 'Kodak Gold', 'Medium - 120', 'Self developed at home', 200, 'f5.6', '1/60', '0', 3, 101),
    (25, 'Free_Spirits_2', 'Pentax 6x7', 'Kodak Gold', 'Medium - 120', 'Self developed at home', 200, 'f5.6', '1/60', '+1', 3, 101),
    (26, 'Free_Spirits_3', 'Pentax 6x7', 'Kodak Gold', 'Medium - 120', 'Self developed at home', 200, 'f4', '1/80', '+1', 3, 101),
    (27, 'Free_Spirits_4', 'Pentax 6x7', 'Kodak Gold', 'Medium - 120', 'Self developed at home', 200, 'f5.6', '1/60', '0', 3, 101),
    (28, 'Free_Spirits_5', 'Pentax 6x7', 'Kodak Gold', 'Medium - 120', 'Self developed at home', 200, 'f8', '1/125', '+1', 3, 101),
    (29, 'Free_Spirits_6', 'Pentax 6x7', 'Kodak Gold', 'Medium - 120', 'Self developed at home', 200, 'f5.6', '1/60', '0', 3, 101),
    (30, 'Free_Spirits_7', 'Pentax 6x7', 'Kodak Gold', 'Medium - 120', 'Self developed at home', 200, 'f11', '1/60', '+1', 3, 101);

--Photos for project folder 4, user 103
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

--Photos for project folder 5, user 101
INSERT INTO photos (id, photo_title, camera, film_stock, film_format, developed_by, iso, f_stop, shutter_speed, exposure_compensation, project_folder_id, user_photo_id)
VALUES
    (39, 'Sleepless_night_1', 'Canon AE-1 black', 'Ilford Delta 3200', '35mm', 'Self developed at home', 3200, 'f8', '1/60', '+1', 5, 101),
    (40, 'Sleepless_night_2', 'Canon AE-1 black', 'Ilford Delta 3200', '35mm', 'Self developed at home', 3200, 'f8', '1/60', '+1', 5, 101),
    (41, 'Sleepless_night_3', 'Canon AE-1 black', 'Ilford Delta 3200', '35mm', 'Self developed at home', 3200, 'f5.6', '1/60', '+1', 5, 101),
    (42, 'Sleepless_night_4', 'Canon AE-1 black', 'Ilford Delta 3200', '35mm', 'Self developed at home', 3200, 'f2.8', '1/125', '0', 5, 101),
    (43, 'Sleepless_night_5', 'Canon AE-1 black', 'Ilford Delta 3200', '35mm', 'Self developed at home', 3200, 'f2.8', '1/125', '0', 5, 101),
    (44, 'Sleepless_night_6', 'Canon AE-1 black', 'Ilford Delta 3200', '35mm', 'Self developed at home', 3200, 'f2.8', '1/125', '0', 5, 101),
    (45, 'Sleepless_night_7', 'Canon AE-1 black', 'Ilford Delta 3200', '35mm', 'Self developed at home', 3200, 'f4', '1/80', '0', 5, 101),
    (46, 'Sleepless_night_8', 'Canon AE-1 black', 'Ilford Delta 3200', '35mm', 'Self developed at home', 3200, 'f4', '1/80', '0', 5, 101),
    (47, 'Sleepless_night_9', 'Canon AE-1 black', 'Ilford Delta 3200', '35mm', 'Self developed at home', 3200, 'f4', '1/80', '0', 5, 101),
    (48, 'Sleepless_night_10', 'Canon AE-1 black', 'Ilford Delta 3200', '35mm', 'Self developed at home', 3200, 'f4', '1/80', '0', 5, 101);

--Photos for project folder 6, user 105
INSERT INTO photos (id, photo_title, camera, film_stock, film_format, developed_by, iso, f_stop, shutter_speed, exposure_compensation, project_folder_id, user_photo_id)
VALUES
    (49, 'Goddess_1', 'Pentax 6x7', 'Ilford Delta 400', 'Medium - 120', 'Foto Verweij Nijmegen', 400, 'f2.8', '1/125', '0', 6, 105),
    (50, 'Goddess_2', 'Pentax 6x7', 'Ilford Delta 400', 'Medium - 120', 'Foto Verweij Nijmegen', 400, 'f2.8', '1/125', '0', 6, 105),
    (51, 'Goddess_3', 'Pentax 6x7', 'Ilford Delta 400', 'Medium - 120', 'Foto Verweij Nijmegen', 400, 'f2.8', '1/125', '0', 6, 105),
    (52, 'Goddess_4', 'Pentax 6x7', 'Ilford Delta 400', 'Medium - 120', 'Foto Verweij Nijmegen', 400, 'f2.8', '1/125', '0', 6, 105),
    (53, 'Goddess_5', 'Pentax 6x7', 'Kodak Portra 400', 'Medium - 120', 'Foto Verweij Nijmegen', 400, 'f2.8', '1/125', '+1', 6, 105),
    (54, 'Goddess_6', 'Pentax 6x7', 'Kodak Portra 400', 'Medium - 120', 'Foto Verweij Nijmegen', 400, 'f2.8', '1/125', '+1', 6, 105),
    (55, 'Goddess_7', 'Pentax 6x7', 'Kodak Portra 400', 'Medium - 120', 'Foto Verweij Nijmegen', 400, 'f2.8', '1/125', '+1', 6, 105),
    (56, 'Goddess_8', 'Pentax 6x7', 'Kodak Portra 400', 'Medium - 120', 'Foto Verweij Nijmegen', 400, 'f2.8', '1/125', '+1', 6, 105);

--Photos for project folder 7, user 102
INSERT INTO photos (id, photo_title, camera, film_stock, film_format, developed_by, iso, f_stop, shutter_speed, exposure_compensation, project_folder_id, user_photo_id)
VALUES
    (57, 'Self_portrait_1', 'Mamiya M645', 'Kodak Portra 400', 'Medium - 120', 'Fotowereld Utrecht', 400, 'f5.6', '1/80', '0', 7, 102),
    (58, 'Self_portrait_2', 'Mamiya M645', 'Kodak Portra 400', 'Medium - 120', 'Fotowereld Utrecht', 400, 'f5.6', '1/80', '0', 7, 102),
    (59, 'Self_portrait_3', 'Mamiya M645', 'Kodak Portra 400', 'Medium - 120', 'Fotowereld Utrecht', 400, 'f8', '1/60', '+1', 7, 102),
    (60, 'Self_portrait_4', 'Mamiya M645', 'Kodak Portra 400', 'Medium - 120', 'Fotowereld Utrecht', 400, 'f8', '1/60', '+1', 7, 102),
    (61, 'Self_portrait_5', 'Mamiya M645', 'Kodak Portra 400', 'Medium - 120', 'Fotowereld Utrecht', 400, 'f8', '1/60', '+1', 7, 102),
    (62, 'Self_portrait_6', 'Mamiya M645', 'Kodak Portra 400', 'Medium - 120', 'Fotowereld Utrecht', 400, 'f8', '1/60', '+1', 7, 102);

--Photos for project folder 8, user 106
INSERT INTO photos (id, photo_title, camera, film_stock, film_format, developed_by, iso, f_stop, shutter_speed, exposure_compensation, project_folder_id, user_photo_id)
VALUES
    (63, 'Mother_1', 'Pentax 6x7', 'Ilford HP5 Plus', 'Medium - 120', 'Self developed at home', 400, 'f4', '1/125', '0', 8, 106),
    (64, 'Mother_2', 'Pentax 6x7', 'Ilford HP5 Plus', 'Medium - 120', 'Self developed at home', 400, 'f4', '1/125', '0', 8, 106),
    (65, 'Mother_3', 'Pentax 6x7', 'Ilford HP5 Plus', 'Medium - 120', 'Self developed at home', 400, 'f4', '1/125', '0', 8, 106),
    (66, 'Mother_4', 'Pentax 6x7', 'Ilford HP5 Plus', 'Medium - 120', 'Self developed at home', 400, 'f5.6', '1/80', '+1', 8, 106),
    (67, 'Mother_5', 'Pentax 6x7', 'Ilford HP5 Plus', 'Medium - 120', 'Self developed at home', 400, 'f5.6', '1/80', '+1', 8, 106),
    (68, 'Mother_6', 'Pentax 6x7', 'Ilford HP5 Plus', 'Medium - 120', 'Self developed at home', 400, 'f5.6', '1/80', '+1', 8, 106),
    (69, 'Mother_7', 'Pentax 6x7', 'Ilford HP5 Plus', 'Medium - 120', 'Self developed at home', 400, 'f8', '1/60', '+1', 8, 106),
    (70, 'Mother_8', 'Pentax 6x7', 'Ilford HP5 Plus', 'Medium - 120', 'Self developed at home', 400, 'f8', '1/60', '+1', 8, 106);

--Photos for project folder 9, user 104
INSERT INTO photos (id, photo_title, camera, film_stock, film_format, developed_by, iso, f_stop, shutter_speed, exposure_compensation, project_folder_id, user_photo_id)
VALUES
    (71, 'Midnight_Streets_1', 'Canon AE-1 black', 'Cinestill 800 Tungsten', '35mm', 'Key Color Fotolab Amsterdam', 800, 'f8', '1/60', '+1', 9, 104),
    (72, 'Midnight_Streets_2', 'Canon AE-1 black', 'Cinestill 800 Tungsten', '35mm', 'Key Color Fotolab Amsterdam', 800, 'f8', '1/60', '+1', 9, 104),
    (73, 'Midnight_Streets_3', 'Canon AE-1 black', 'Cinestill 800 Tungsten', '35mm', 'Key Color Fotolab Amsterdam', 800, 'f8', '1/60', '+1', 9, 104),
    (74, 'Midnight_Streets_4', 'Canon AE-1 black', 'Cinestill 800 Tungsten', '35mm', 'Key Color Fotolab Amsterdam', 800, 'f8', '1/60', '+1', 9, 104),
    (75, 'Midnight_Streets_5', 'Canon AE-1 black', 'Cinestill 800 Tungsten', '35mm', 'Key Color Fotolab Amsterdam', 800, 'f11', '1/60', '+2', 9, 104),
    (76, 'Midnight_Streets_6', 'Canon AE-1 black', 'Cinestill 800 Tungsten', '35mm', 'Key Color Fotolab Amsterdam', 800, 'f11', '1/60', '+2', 9, 104),
    (77, 'Midnight_Streets_7', 'Canon AE-1 black', 'Cinestill 800 Tungsten', '35mm', 'Key Color Fotolab Amsterdam', 800, 'f11', '1/60', '+2', 9, 104),
    (78, 'Midnight_Streets_8', 'Canon AE-1 black', 'Cinestill 800 Tungsten', '35mm', 'Key Color Fotolab Amsterdam', 800, 'f11', '1/60', '+2', 9, 104),
    (79, 'Midnight_Streets_9', 'Canon AE-1 black', 'Cinestill 800 Tungsten', '35mm', 'Key Color Fotolab Amsterdam', 800, 'f8', '1/60', '0', 9, 104),
    (80, 'Midnight_Streets_10', 'Canon AE-1 black', 'Cinestill 800 Tungsten', '35mm', 'Key Color Fotolab Amsterdam', 800, 'f8', '1/60', '0', 9, 104);

--Photos for project folder 10, user 105
INSERT INTO photos (id, photo_title, camera, film_stock, film_format, developed_by, iso, f_stop, shutter_speed, exposure_compensation, project_folder_id, user_photo_id)
VALUES
    (81, 'Family_Lopez', 'Mamiya M645', 'Kodak Portra 400', 'Medium - 120', 'Foto Verweij Nijmegen', 400, 'f11', '1/125', '+1', 10, 105),
    (82, 'Family_Mendez', 'Mamiya M645', 'Kodak Portra 400', 'Medium - 120', 'Foto Verweij Nijmegen', 400, 'f11', '1/125', '+1', 10, 105),
    (83, 'Family_Smith', 'Mamiya M645', 'Kodak Portra 400', 'Medium - 120', 'Foto Verweij Nijmegen', 400, 'f11', '1/125', '+1', 10, 105),
    (84, 'Family_Bakker', 'Mamiya M645', 'Kodak Portra 400', 'Medium - 120', 'Foto Verweij Nijmegen', 400, 'f11', '1/125', '+1', 10, 105),
    (85, 'Family_van_Geelen', 'Mamiya M645', 'Kodak Portra 400', 'Medium - 120', 'Foto Verweij Nijmegen', 400, 'f11', '1/125', '+1', 10, 105),
    (86, 'Family_Henery', 'Mamiya M645', 'Kodak Portra 400', 'Medium - 120', 'Foto Verweij Nijmegen', 400, 'f11', '1/125', '+1', 10, 105),
    (87, 'Family_Reekers', 'Mamiya M645', 'Kodak Portra 400', 'Medium - 120', 'Foto Verweij Nijmegen', 400, 'f11', '1/125', '+1', 10, 105),
    (88, 'Family_Rijnschot', 'Mamiya M645', 'Kodak Portra 400', 'Medium - 120', 'Foto Verweij Nijmegen', 400, 'f11', '1/125', '+1', 10, 105);

--Photos for project folder 11, user 104
INSERT INTO photos (id, photo_title, camera, film_stock, film_format, developed_by, iso, f_stop, shutter_speed, exposure_compensation, project_folder_id, user_photo_id)
VALUES
    (89, 'By_the_river_portrait_1', 'Linhof Super Technika V 4X5', 'Kodak Professional T-Max 400', 'Large - 4X5', 'Foto Verweij Nijmegen', 400, 'f5.6', '1/60', '0', 11, 104),
    (90, 'By_the_river_portrait_2', 'Linhof Super Technika V 4X5', 'Kodak Professional T-Max 400', 'Large - 4X5', 'Foto Verweij Nijmegen', 400, 'f5.6', '1/60', '0', 11, 104),
    (91, 'By_the_river_portrait_3', 'Linhof Super Technika V 4X5', 'Kodak Professional T-Max 400', 'Large - 4X5', 'Foto Verweij Nijmegen', 400, 'f5.6', '1/60', '0', 11, 104),
    (92, 'By_the_river_landscape_1', 'Linhof Super Technika V 4X5', 'Kodak Professional T-Max 400', 'Large - 4X5', 'Foto Verweij Nijmegen', 400, 'f16', '1/125', '0', 11, 104),
    (93, 'By_the_river_landscape_2', 'Linhof Super Technika V 4X5', 'Kodak Professional T-Max 400', 'Large - 4X5', 'Foto Verweij Nijmegen', 400, 'f16', '1/125', '0', 11, 104),
    (94, 'By_the_river_landscape_3', 'Linhof Super Technika V 4X5', 'Kodak Professional T-Max 400', 'Large - 4X5', 'Foto Verweij Nijmegen', 400, 'f16', '1/125', '0', 11, 104);

--Photos for project folder 12, user 103
INSERT INTO photos (id, photo_title, camera, film_stock, film_format, developed_by, iso, f_stop, shutter_speed, exposure_compensation, project_folder_id, user_photo_id)
VALUES
    (95, 'Untitled_1', 'Contax G2', 'Kodak Ultra Max', '35mm', 'Self developed at home', 400, 'f8', '1/60', '+1', 12, 103),
    (96, 'Untitled_2', 'Contax G2', 'Kodak Ultra Max', '35mm', 'Self developed at home', 400, 'f8', '1/60', '+1', 12, 103),
    (97, 'Untitled_3', 'Contax G2', 'Kodak Ultra Max', '35mm', 'Self developed at home', 400, 'f5.6', '1/125', '0', 12, 103),
    (98, 'Untitled_4', 'Contax G2', 'Kodak Ultra Max', '35mm', 'Self developed at home', 400, 'f5.6', '1/125', '0', 12, 103),
    (99, 'Untitled_5', 'Contax G2', 'Kodak Ultra Max', '35mm', 'Self developed at home', 400, 'f4', '1/250', '0', 12, 103),
    (100, 'Untitled_6', 'Contax G2', 'Kodak Ultra Max', '35mm', 'Self developed at home', 400, 'f3.5', '1/250', '0', 12, 103);


