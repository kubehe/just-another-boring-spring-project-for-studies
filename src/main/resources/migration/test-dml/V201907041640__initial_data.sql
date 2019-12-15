insert into ticket_type (name, price)
values ('adult', 18),
       ('student', 18),
       ('child', 12.5);

insert into movie (title)
values ('Pulp Fiction'),
       ('Killer'),
       ('Chłopaki nie płaczą');

insert into room (columns_number, name, rows_number)
values (15, 'Pok. A', 50),
       (20, 'Pok. B', 60),
       (10, 'Pok. C', 20);

insert into screening (start_time, movie_id, room_id)
values ('2019-12-20 12:00:00.0'::timestamp, (select distinct m.id from movie m where m.title = 'Pulp Fiction'), (select distinct r.id from room r where r.name = 'Pok. A')),
       ('2019-12-20 14:00:00.0'::timestamp, (select distinct m.id from movie m where m.title = 'Killer'), (select distinct r.id from room r where r.name = 'Pok. A')),
       ('2019-12-20 16:00:00.0'::timestamp, (select distinct m.id from movie m where m.title = 'Chłopaki nie płaczą'), (select distinct r.id from room r where r.name = 'Pok. A')),
       ('2019-12-20 12:00:00.0'::timestamp, (select distinct m.id from movie m where m.title = 'Pulp Fiction'), (select distinct r.id from room r where r.name = 'Pok. B')),
       ('2019-12-20 14:00:00.0'::timestamp, (select distinct m.id from movie m where m.title = 'Killer'), (select distinct r.id from room r where r.name = 'Pok. B')),
       ('2019-12-20 16:00:00.0'::timestamp, (select distinct m.id from movie m where m.title = 'Chłopaki nie płaczą'), (select distinct r.id from room r where r.name = 'Pok. B')),
       ('2019-12-20 12:00:00.0'::timestamp, (select distinct m.id from movie m where m.title = 'Pulp Fiction'), (select distinct r.id from room r where r.name = 'Pok. C')),
       ('2019-12-20 14:00:00.0'::timestamp, (select distinct m.id from movie m where m.title = 'Killer'), (select distinct r.id from room r where r.name = 'Pok. C')),
       ('2019-12-20 16:00:00.0'::timestamp, (select distinct m.id from movie m where m.title = 'Chłopaki nie płaczą'), (select distinct r.id from room r where r.name = 'Pok. C'));
