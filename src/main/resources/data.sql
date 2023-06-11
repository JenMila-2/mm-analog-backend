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
