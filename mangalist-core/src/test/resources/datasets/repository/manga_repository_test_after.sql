DELETE FROM mangas;
DELETE FROM genres;
DELETE FROM genre_manga;

ALTER SEQUENCE genres_id_seq RESTART WITH 1;
ALTER SEQUENCE mangas_id_seq RESTART WITH 1;