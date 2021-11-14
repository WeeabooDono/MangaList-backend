INSERT INTO MANGAS (entity_version, title, description, author, image, created_by, created_date, last_modified_by, last_modified_date)
VALUES (0, 'test_1', 'description', 'author', 'path/to/image.png', 'liquibase', current_timestamp, null, null),
       (0, 'test_2', 'description', 'author', 'path/to/image.png', 'liquibase', current_timestamp, null, null),
       (0, 'test_3', 'description', 'author', 'path/to/image.png', 'liquibase', current_timestamp, null, null);

INSERT INTO GENRES (entity_version, name)
VALUES (0, 'test_1'),
       (0, 'test_2'),
       (0, 'test_3');

INSERT INTO GENRE_MANGA(manga_id, genre_id)
VALUES (1, 1),
       (1, 3),
       (2, 1),
       (2, 2),
       (2, 3),
       (3, 2);