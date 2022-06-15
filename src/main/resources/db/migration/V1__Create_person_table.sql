CREATE TABLE person
(
    id         serial PRIMARY KEY,
    first_name varchar,
    last_name  varchar
);

INSERT INTO person (id, first_name, last_name)
VALUES
       (DEFAULT, 'Lubna', 'Terry'),
       (DEFAULT, 'Daniela', 'Boyce'),
       (DEFAULT, 'Mira', 'Santos'),
       (DEFAULT, 'Cecelia', 'Coffey'),
       (DEFAULT, 'Dwayne', 'Stevenson'),
       (DEFAULT, 'Gracey', 'Sullivan'),
       (DEFAULT, 'Dulcie', ' Branch'),
       (DEFAULT, 'Kristy', 'Garrison'),
       (DEFAULT, 'Aaryan', 'Bowers'),
       (DEFAULT, 'Khadeejah', 'Delarosa');