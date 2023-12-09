create table if not exists game.levels (
       id serial primary key,
       name varchar UNIQUE,
       pattern varchar[]
);

create schema if not exists game;

insert into game.levels
(name, pattern)
values ('%s', '%s')
ON CONFLICT (name) DO NOTHING

delete from game.levels where id = %d;

UPDATE game.levels SET name = '%s'
WHERE id = %d;

UPDATE game.levels SET pattern = '%s'
WHERE id = %d;