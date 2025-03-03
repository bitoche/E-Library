-- при необходимости заменить public на нужное название схемы. изначально не предусмотрено
-- так же можно заменить pg_database_owner на нужное имя пользователя

-- DROP SCHEMA public;

CREATE SCHEMA public AUTHORIZATION pg_database_owner;

-- DROP SEQUENCE public.book_author_author_id_seq;

CREATE SEQUENCE public.book_author_author_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.book_author_seq;

CREATE SEQUENCE public.book_author_seq
	INCREMENT BY 50
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.book_book_id_seq;

CREATE SEQUENCE public.book_book_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.book_genre_genre_id_seq;

CREATE SEQUENCE public.book_genre_genre_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.book_language_language_id_seq;

CREATE SEQUENCE public.book_language_language_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.book_relations_relation_id_seq;

CREATE SEQUENCE public.book_relations_relation_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.book_relations_seq;

CREATE SEQUENCE public.book_relations_seq
	INCREMENT BY 50
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.book_seq;

CREATE SEQUENCE public.book_seq
	INCREMENT BY 50
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.book_status_book_status_id_seq;

CREATE SEQUENCE public.book_status_book_status_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.book_status_seq;

CREATE SEQUENCE public.book_status_seq
	INCREMENT BY 50
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.book_to_book_relation_relation_id_seq;

CREATE SEQUENCE public.book_to_book_relation_relation_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.book_to_book_relation_seq;

CREATE SEQUENCE public.book_to_book_relation_seq
	INCREMENT BY 50
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.books_arriving_arriving_id_seq;

CREATE SEQUENCE public.books_arriving_arriving_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.books_arriving_seq;

CREATE SEQUENCE public.books_arriving_seq
	INCREMENT BY 50
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.bookshelf_seq;

CREATE SEQUENCE public.bookshelf_seq
	INCREMENT BY 50
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.bookshelf_shelf_id_seq;

CREATE SEQUENCE public.bookshelf_shelf_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.borrow_borrow_id_seq;

CREATE SEQUENCE public.borrow_borrow_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.borrow_seq;

CREATE SEQUENCE public.borrow_seq
	INCREMENT BY 50
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.borrow_status_borrow_status_id_seq;

CREATE SEQUENCE public.borrow_status_borrow_status_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.borrow_status_seq;

CREATE SEQUENCE public.borrow_status_seq
	INCREMENT BY 50
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.cabinet_cabinet_id_seq;

CREATE SEQUENCE public.cabinet_cabinet_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.cabinet_seq;

CREATE SEQUENCE public.cabinet_seq
	INCREMENT BY 50
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.entry_code_code_id_seq;

CREATE SEQUENCE public.entry_code_code_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.entry_code_seq;

CREATE SEQUENCE public.entry_code_seq
	INCREMENT BY 50
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.notification_notification_id_seq;

CREATE SEQUENCE public.notification_notification_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.notification_seq;

CREATE SEQUENCE public.notification_seq
	INCREMENT BY 50
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.ordering_books_ordering_id_seq;

CREATE SEQUENCE public.ordering_books_ordering_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.ordering_books_seq;

CREATE SEQUENCE public.ordering_books_seq
	INCREMENT BY 50
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.publishing_house_publishing_house_id_seq;

CREATE SEQUENCE public.publishing_house_publishing_house_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.publishing_house_seq;

CREATE SEQUENCE public.publishing_house_seq
	INCREMENT BY 50
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.recovery_token_seq;

CREATE SEQUENCE public.recovery_token_seq
	INCREMENT BY 50
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.recovery_token_token_id_seq;

CREATE SEQUENCE public.recovery_token_token_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.user_role_role_id_seq;

CREATE SEQUENCE public.user_role_role_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.user_role_seq;

CREATE SEQUENCE public.user_role_seq
	INCREMENT BY 50
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.user_seq;

CREATE SEQUENCE public.user_seq
	INCREMENT BY 50
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.user_user_id_seq;

CREATE SEQUENCE public.user_user_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;-- public.book_author определение

-- Drop table

-- DROP TABLE public.book_author;

CREATE TABLE public.book_author (
	author_id bigserial NOT NULL,
	first_name varchar(255) NOT NULL,
	second_name varchar(255) NOT NULL,
	third_name varchar(255) NULL,
	date_of_birth date NULL,
	identifier varchar(255) NULL,
	CONSTRAINT book_author_pkey PRIMARY KEY (author_id)
);


-- public.book_genre определение

-- Drop table

-- DROP TABLE public.book_genre;

CREATE TABLE public.book_genre (
	genre_id serial4 NOT NULL,
	genre_name varchar(255) NOT NULL,
	CONSTRAINT book_genre_pkey PRIMARY KEY (genre_id)
);


-- public.book_language определение

-- Drop table

-- DROP TABLE public.book_language;

CREATE TABLE public.book_language (
	language_id serial4 NOT NULL,
	language_name varchar(255) NOT NULL,
	CONSTRAINT book_language_pkey PRIMARY KEY (language_id)
);


-- public.book_relations определение

-- Drop table

-- DROP TABLE public.book_relations;

CREATE TABLE public.book_relations (
	relation_id serial4 NOT NULL,
	relation_name varchar(255) NOT NULL,
	CONSTRAINT book_relations_pkey PRIMARY KEY (relation_id)
);


-- public.book_status определение

-- Drop table

-- DROP TABLE public.book_status;

CREATE TABLE public.book_status (
	book_status_id serial4 NOT NULL,
	status_name varchar(255) NOT NULL,
	CONSTRAINT book_status_pkey PRIMARY KEY (book_status_id)
);


-- public.borrow_status определение

-- Drop table

-- DROP TABLE public.borrow_status;

CREATE TABLE public.borrow_status (
	borrow_status_id serial4 NOT NULL,
	status_name varchar(255) NOT NULL,
	CONSTRAINT borrow_status_pkey PRIMARY KEY (borrow_status_id)
);


-- public.publishing_house определение

-- Drop table

-- DROP TABLE public.publishing_house;

CREATE TABLE public.publishing_house (
	publishing_house_id bigserial NOT NULL,
	publishing_house_name varchar(255) NOT NULL,
	CONSTRAINT publishing_house_pkey PRIMARY KEY (publishing_house_id)
);


-- public.user_role определение

-- Drop table

-- DROP TABLE public.user_role;

CREATE TABLE public.user_role (
	role_id serial4 NOT NULL,
	role_name varchar(25) NOT NULL,
	CONSTRAINT user_role_pkey PRIMARY KEY (role_id)
);


-- public.book определение

-- Drop table

-- DROP TABLE public.book;

CREATE TABLE public.book (
	book_id bigserial NOT NULL,
	book_name varchar(255) NOT NULL,
	release_date date NOT NULL,
	number_of_pages int8 NOT NULL,
	description varchar(100000) DEFAULT 'Нет описания'::character varying NOT NULL,
	identifier varchar(255) NOT NULL,
	book_status int4 NOT NULL,
	"language" int2 NOT NULL,
	CONSTRAINT book_pkey PRIMARY KEY (book_id),
	CONSTRAINT book_fk6 FOREIGN KEY (book_status) REFERENCES public.book_status(book_status_id) ON DELETE CASCADE,
	CONSTRAINT book_to_book_language FOREIGN KEY ("language") REFERENCES public.book_language(language_id) ON DELETE SET NULL
);


-- public.book_to_author определение

-- Drop table

-- DROP TABLE public.book_to_author;

CREATE TABLE public.book_to_author (
	book_id int8 NOT NULL,
	book_author_id int8 NOT NULL,
	CONSTRAINT book_to_author_pkey PRIMARY KEY (book_id, book_author_id),
	CONSTRAINT book_to_author_fk0 FOREIGN KEY (book_id) REFERENCES public.book(book_id) ON DELETE CASCADE,
	CONSTRAINT book_to_author_fk1 FOREIGN KEY (book_author_id) REFERENCES public.book_author(author_id) ON DELETE CASCADE
);


-- public.book_to_book_relation определение

-- Drop table

-- DROP TABLE public.book_to_book_relation;

CREATE TABLE public.book_to_book_relation (
	relation_id serial4 NOT NULL,
	relation_description varchar(255) NULL,
	main_book int8 NOT NULL,
	related_book int8 NOT NULL,
	relation int4 NOT NULL,
	CONSTRAINT book_to_book_relation_pkey PRIMARY KEY (relation_id),
	CONSTRAINT book_to_book_relation_fk2 FOREIGN KEY (main_book) REFERENCES public.book(book_id),
	CONSTRAINT book_to_book_relation_fk3 FOREIGN KEY (related_book) REFERENCES public.book(book_id),
	CONSTRAINT book_to_book_relation_fk4 FOREIGN KEY (relation) REFERENCES public.book_relations(relation_id)
);


-- public.book_to_genre определение

-- Drop table

-- DROP TABLE public.book_to_genre;

CREATE TABLE public.book_to_genre (
	book_id int8 NOT NULL,
	genre_id int4 NOT NULL,
	CONSTRAINT book_to_genre_pkey PRIMARY KEY (book_id, genre_id),
	CONSTRAINT fk5gwk6rv411uiqccmamicjkf1v FOREIGN KEY (book_id) REFERENCES public.book(book_id) ON DELETE CASCADE,
	CONSTRAINT fkb6h0o7n5p4u7m1yveananxqcn FOREIGN KEY (genre_id) REFERENCES public.book_genre(genre_id) ON DELETE CASCADE
);


-- public.book_to_publishing_house определение

-- Drop table

-- DROP TABLE public.book_to_publishing_house;

CREATE TABLE public.book_to_publishing_house (
	book_id int8 NOT NULL,
	publishing_house_id int8 NOT NULL,
	CONSTRAINT book_to_publishing_house_pkey PRIMARY KEY (book_id, publishing_house_id),
	CONSTRAINT book_to_publishing_house_fk0 FOREIGN KEY (book_id) REFERENCES public.book(book_id) ON DELETE CASCADE,
	CONSTRAINT book_to_publishing_house_fk1 FOREIGN KEY (publishing_house_id) REFERENCES public.publishing_house(publishing_house_id) ON DELETE CASCADE
);


-- public.bookshelf определение

-- Drop table

-- DROP TABLE public.bookshelf;

CREATE TABLE public.bookshelf (
	shelf_id serial4 NOT NULL,
	shelf_number int4 NOT NULL,
	books int8 NULL,
	CONSTRAINT bookshelf_pkey PRIMARY KEY (shelf_id),
	CONSTRAINT bookshelf_fk2 FOREIGN KEY (books) REFERENCES public.book(book_id) ON DELETE CASCADE
);


-- public.cabinet определение

-- Drop table

-- DROP TABLE public.cabinet;

CREATE TABLE public.cabinet (
	cabinet_id serial4 NOT NULL,
	cabinet_number int4 NOT NULL,
	shelves int8 NOT NULL,
	cabinet_name varchar(255) NOT NULL,
	CONSTRAINT cabinet_pkey PRIMARY KEY (cabinet_id),
	CONSTRAINT cabinet_fk2 FOREIGN KEY (shelves) REFERENCES public.bookshelf(shelf_id) ON DELETE CASCADE
);


-- public.cabinet_shelves определение

-- Drop table

-- DROP TABLE public.cabinet_shelves;

CREATE TABLE public.cabinet_shelves (
	cabinet_cabinet_id int4 NOT NULL,
	shelves_shelf_id int4 NOT NULL,
	CONSTRAINT uk8mkher4bk8vy6lsy3i9eoxwp8 UNIQUE (shelves_shelf_id),
	CONSTRAINT fkjcoof78lp3shpu2su2qmsenl9 FOREIGN KEY (cabinet_cabinet_id) REFERENCES public.cabinet(cabinet_id),
	CONSTRAINT fkq2khfncin6wq582w4gykd7bqo FOREIGN KEY (shelves_shelf_id) REFERENCES public.bookshelf(shelf_id)
);


-- public."user" определение

-- Drop table

-- DROP TABLE public."user";

CREATE TABLE public."user" (
	user_id bigserial NOT NULL,
	first_name varchar(255) NOT NULL,
	second_name varchar(255) NOT NULL,
	third_name varchar(255) NULL,
	date_of_birth date NULL,
	email varchar(255) NOT NULL,
	"password" varchar(255) NOT NULL,
	role_id int4 NULL,
	CONSTRAINT user_pkey PRIMARY KEY (user_id),
	CONSTRAINT fkcqrlykhob17d4pff3w78i1302 FOREIGN KEY (role_id) REFERENCES public.user_role(role_id)
);


-- public.borrow определение

-- Drop table

-- DROP TABLE public.borrow;

CREATE TABLE public.borrow (
	borrow_id bigserial NOT NULL,
	borrow_date timestamp(6) NOT NULL,
	expected_return_date timestamp(6) NOT NULL,
	borrowed_user int8 NOT NULL,
	borrowed_book int8 NOT NULL,
	redemption_price_if_loss int8 NOT NULL,
	status int4 NOT NULL,
	CONSTRAINT borrow_pkey PRIMARY KEY (borrow_id),
	CONSTRAINT borrow_fk3 FOREIGN KEY (borrowed_user) REFERENCES public."user"(user_id) ON DELETE CASCADE,
	CONSTRAINT borrow_fk4 FOREIGN KEY (borrowed_book) REFERENCES public.book(book_id) ON DELETE CASCADE,
	CONSTRAINT borrow_fk6 FOREIGN KEY (status) REFERENCES public.borrow_status(borrow_status_id) ON DELETE CASCADE
);


-- public.entry_code определение

-- Drop table

-- DROP TABLE public.entry_code;

CREATE TABLE public.entry_code (
	code_id bigserial NOT NULL,
	code varchar(255) NOT NULL,
	"user" int8 NOT NULL,
	expire_dttm timestamp(6) NOT NULL,
	CONSTRAINT entry_code_pkey PRIMARY KEY (code_id),
	CONSTRAINT entry_code_fk2 FOREIGN KEY ("user") REFERENCES public."user"(user_id)
);


-- public.notification определение

-- Drop table

-- DROP TABLE public.notification;

CREATE TABLE public.notification (
	notification_id bigserial NOT NULL,
	title varchar(255) NOT NULL,
	"text" varchar(255) NOT NULL,
	"user" int8 NOT NULL,
	is_checked bool NOT NULL,
	CONSTRAINT notification_pkey PRIMARY KEY (notification_id),
	CONSTRAINT notification_fk3 FOREIGN KEY ("user") REFERENCES public."user"(user_id)
);


-- public.ordering_books определение

-- Drop table

-- DROP TABLE public.ordering_books;

CREATE TABLE public.ordering_books (
	ordering_id bigserial NOT NULL,
	ordering_date date NOT NULL,
	expected_arrival_date date NOT NULL,
	administrator int8 NOT NULL,
	books varchar(255) NOT NULL,
	CONSTRAINT ordering_books_pkey PRIMARY KEY (ordering_id),
	CONSTRAINT ordering_books_fk3 FOREIGN KEY (administrator) REFERENCES public."user"(user_id)
);


-- public.recovery_token определение

-- Drop table

-- DROP TABLE public.recovery_token;

CREATE TABLE public.recovery_token (
	token_id bigserial NOT NULL,
	"token" varchar(255) NOT NULL,
	expire_dttm date NOT NULL,
	"user" int8 NOT NULL,
	CONSTRAINT recovery_token_pkey PRIMARY KEY (token_id),
	CONSTRAINT recovery_token_fk3 FOREIGN KEY ("user") REFERENCES public."user"(user_id)
);


-- public.books_arriving определение

-- Drop table

-- DROP TABLE public.books_arriving;

CREATE TABLE public.books_arriving (
	arriving_id bigserial NOT NULL,
	arriving_date date NOT NULL,
	arrived_books varchar(255) NOT NULL,
	"ordering" int8 NOT NULL,
	CONSTRAINT books_arriving_pkey PRIMARY KEY (arriving_id),
	CONSTRAINT books_arriving_fk3 FOREIGN KEY ("ordering") REFERENCES public.ordering_books(ordering_id)
);



-- DROP FUNCTION public.set_issue_date();

CREATE OR REPLACE FUNCTION public.set_issue_date()
 RETURNS trigger
 LANGUAGE plpgsql
AS $function$
BEGIN
    NEW.issue_date := NOW();
    RETURN NEW;
END;
$function$
;