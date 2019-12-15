comment on schema public is 'standard public schema';

alter schema public owner to postgres;

create sequence hibernate_sequence;

alter sequence hibernate_sequence owner to postgres;

create table if not exists cinema.public.movie
(
	id bigserial not null
		constraint movie_pkey
			primary key,
	title varchar(255) not null
		constraint uk_movie__title
			unique
);

alter table cinema.public.movie owner to postgres;

create table if not exists cinema.public.room
(
	id bigserial not null
		constraint room_pkey
			primary key,
	columns_number bigint,
	name varchar(255) not null
		constraint uk_room__name
			unique,
	rows_number bigint
);

alter table cinema.public.room owner to postgres;

create table if not exists cinema.public.screening
(
	id bigserial not null
		constraint screening_pkey
			primary key,
	start_time timestamp not null,
	movie_id bigint
		constraint fk_screening__movie
			references cinema.public.movie,
	room_id bigint
		constraint fk_screening__room
			references cinema.public.room
);

alter table cinema.public.screening owner to postgres;

create table if not exists cinema.public.reservation
(
	id bigserial not null
		constraint reservation_pkey
			primary key,
	name varchar(255) not null,
	surname varchar(255) not null,
	uuid uuid not null
		constraint uk_reservation__uuid
			unique,
	screening_id bigint
		constraint fk_reservation__screening
			references cinema.public.screening
);

alter table cinema.public.reservation owner to postgres;

create table if not exists cinema.public.ticket_type
(
	id bigserial not null
		constraint ticket_type_pkey
			primary key,
	name varchar(255) not null
		constraint uk_ticket_type__name
			unique,
	price numeric(32, 2) not null
);

alter table cinema.public.ticket_type owner to postgres;

create table if not exists cinema.public.ticket
(
	id bigserial not null
		constraint ticket_pkey
			primary key,
	seat_column bigint not null,
	seat_row bigint not null,
	reservation_id bigint
		constraint fk_ticket__reservation
			references cinema.public.reservation,
	ticket_type_id bigint
		constraint fk_ticket__ticket_type
			references cinema.public.ticket_type
);

alter table cinema.public.ticket owner to postgres;
