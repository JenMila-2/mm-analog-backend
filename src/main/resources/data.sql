--Users
INSERT INTO users (id, name, username, email, password, enabled)
VALUES
    (100, 'Pedro Perez', 'Pedro_20','pedro.p@gmail.com', 'B23A@_Flash9', true),
    (101, 'Marisol Mendez', 'Marisol_M', 'marisol-mendez@gmail.com', 'Mari_47_&99_0', true),
    (102, 'Jensy Franco', 'Jensy_@_Photo', 'jenmilafran@gmail.com', '2@2J29SolER_56', true),
    (103, 'Scott Bennie', 'Benniezzzzzz80', 'bennie2@gmail.com', 'BS7355@@67&Sgh', true);

--Film Stock Inventory
INSERT INTO filmstockinventories (id, film_stock_name, remaining_rolls, brand, stock, format, iso, development_process, storage, rolls_shot, film_expiration_date, user_id)
VALUES
    (1, 'Lomo Lady Grey 400', 4, 'Lomography', 'Lady Greay 400', '35mm', 400, 'B&W', 'Freezer', 1, '2023-06-16', 102);


--Project Folders
INSERT INTO projectfolders (id, project_title, project_concept, user_id)
VALUES
    (1, 'Portraits of my mother', 'Personal project reflecting on my relationship with my mother.', 100),
    (2, 'Night Garden', 'An extended project referencing sleepless nights, dealing with insomnia. Ongoing project.', 100),
    (3, 'The house of Goddesses', 'Celebrating womanhood. Honouring the important women in my life.', 101),
    (4, 'By the River', 'New project. Planning on shooting portraits and landscape photos for this project.', 101),
    (5, 'In between dreams', 'A poetic, photographic meditation on the subtlety of everyday life. Ongoing project.', 102),
    (6, 'The Constructed Self', 'Collection of self portraits.', 102),
    (7, 'At midnight', 'Photographing the streets of London by night.', 103),
    (8, 'Family Portraits', 'Photographers of different families. Ongoing project.', 103);

/*INSERT INTO photos (id, photo_title, camera, film_stock, film_format, developed_by, iso, aperture, shutter_speed, exposure_compensation, photo_gallery_id, project_folder_id, user_photo_id)
VALUES
    (10, 'Dreaming_1', 'Minolta SRT101', 'Kodak Portra 400', '35mm', 'Fotowereld Utrecht', 400, 'f8', '1/60', '+1', 2, 1, 102);
*/