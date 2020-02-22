-- *new password for user postgres
-- ALTER USER postgres PASSWORD 'admin';

-- DROP SCHEMA IF EXISTS readerblog CASCADE;
-- CREATE SCHEMA readerblog;

-- SET search_path TO readerblog;

DROP TABLE IF EXISTS authors;

CREATE TABLE authors
(
    id              bigserial PRIMARY KEY,
    first_name      VARCHAR(50),
    last_name       VARCHAR(50),
    patronymic_name VARCHAR(50),
    short_biography TEXT,
    born_date       DATE,
    rating          real
);

INSERT INTO authors (first_name, last_name, patronymic_name, short_biography, born_date, rating)
VALUES ('Daniel', 'Defoe', '', 'He was a great writer.', '1660-01-08', '0.0');


DROP TABLE IF EXISTS categories;

CREATE TABLE categories
(
    id   serial,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO categories (name)
VALUES ('for adult'),
       ('for children');


DROP TABLE IF EXISTS genre;

CREATE TABLE genre
(
    id   serial,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO genre (name)
VALUES ('Detective'),
       ('Adventure');


DROP TABLE IF EXISTS roles CASCADE;

CREATE TABLE roles
(
    id   serial,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO roles (name)
VALUES ('ROLE_USER'),
       ('ROLE_MODERATOR'),
       ('ROLE_ADMIN'),
       ('ROLE_SUPERADMIN');


DROP TABLE IF EXISTS users CASCADE;

CREATE TABLE users
(
    id         bigserial,
    first_name VARCHAR(50),
    last_name  VARCHAR(50),
    nick_name  VARCHAR(50) UNIQUE,
    password   VARCHAR(80),
    email      VARCHAR(50) UNIQUE,
    role       int NOT NULL,
    FOREIGN KEY (role) REFERENCES roles (id),
    PRIMARY KEY (id)
);

INSERT INTO users (first_name, last_name, nick_name, password, email, role)
VALUES ('Admin', 'Admin', 'admin', '100', 'admin@gmail.com', 4);


DROP TABLE IF EXISTS books;

CREATE TABLE books
(
    id              bigserial PRIMARY KEY,
    title           varchar(255),
    author_id       bigint,
    FOREIGN KEY (author_id) REFERENCES authors (id),
    genre_id        int,
    FOREIGN KEY (genre_id) REFERENCES genre (id),
    category_id     int,
    FOREIGN KEY (category_id) REFERENCES categories (id),
    pages           int,
    format          varchar(255),
    year_of_writing int,
    origin_language varchar(255),
    publisher       varchar(255),
    rating          real
);

INSERT INTO books (title, author_id, genre_id, category_id, pages, format, year_of_writing,
                   origin_language, publisher, rating)
VALUES ('Robinson Crusoe', 1, 1, 1, 120, 'story', 1820, 'English', 'Neva', 10.0);


DROP TABLE IF EXISTS books_images;

CREATE TABLE books_images
(
    id      bigserial PRIMARY KEY,
    book_id bigint,
    FOREIGN KEY (book_id) REFERENCES books (id),
    path    varchar(255)
);

INSERT INTO books_images (book_id, path)
VALUES (1, '1png'),
       (1, '2png');

DROP TABLE IF EXISTS users_books CASCADE;

CREATE TABLE users_books
(
    user_id bigint NOT NULL,
    book_id bigint NOT NULL,
    PRIMARY KEY (user_id, book_id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (book_id) REFERENCES books (id)
);

INSERT INTO users_books(user_id, book_id)
VALUES (1, 1);


DROP TABLE IF EXISTS quotes;

CREATE TABLE quotes
(
    id     bigserial NOT NULL,
    text   text,
    rating real,
    PRIMARY KEY (id)
);

INSERT INTO quotes(text, rating)
values ('some quote', 6.5);


DROP TABLE IF EXISTS quotes_books;

CREATE TABLE quotes_books
(
    quotes_id bigint NOT NULL,
    book_id   bigint NOT NULL,
    PRIMARY KEY (quotes_id, book_id),
    FOREIGN KEY (quotes_id) REFERENCES quotes (id),
    FOREIGN KEY (book_id) REFERENCES books (id)
);

INSERT INTO quotes_books(quotes_id, book_id)
values (1, 1);


DROP TABLE IF EXISTS quotes_users;

CREATE TABLE quotes_users
(
    quotes_id bigint NOT NULL,
    user_id   bigint NOT NULL,
    PRIMARY KEY (quotes_id, user_id),
    FOREIGN KEY (quotes_id) REFERENCES quotes (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);

INSERT INTO quotes_users(quotes_id, user_id)
values (1, 1);


DROP TABLE IF EXISTS key_words;

CREATE TABLE key_words
(
    id   bigserial NOT NULL,
    word varchar(255),
    PRIMARY KEY (id)
);

INSERT INTO key_words(word)
values ('child'),
       ('science');


DROP TABLE IF EXISTS key_words_books;

CREATE TABLE key_words_books
(
    key_words_id bigint NOT NULL,
    book_id      bigint NOT NULL,
    PRIMARY KEY (key_words_id, book_id),
    FOREIGN KEY (key_words_id) REFERENCES key_words (id),
    FOREIGN KEY (book_id) REFERENCES books (id)
);

INSERT INTO key_words_books(key_words_id, book_id)
values (1, 1),
       (2, 1);


DROP TABLE IF EXISTS feedback;

CREATE TABLE feedback
(
    id                   bigserial NOT NULL,
    title                varchar(255),
    author_feedback      text,
    genre_own_opinion    text,
    category_own_opinion text,
    book_feedback        text,
    book_character       text,
    main_theme           text,
    detailed_feedback    text,
    canvas               text,
    overall_impression   text,
    overall_mark         text,
    feedback_visibility  varchar(255),
    date                 timestamp,
    feedback_rating      real,
    PRIMARY KEY (id)
);
INSERT INTO feedback (title, author_feedback, genre_own_opinion, category_own_opinion, book_feedback, book_character,
                      main_theme, detailed_feedback, canvas, overall_impression, overall_mark, feedback_visibility,
                      date, feedback_rating)
VALUES ('Robinson Crusoe', 'I respect this writer!', 'bromance', 'for teenagers',
        'He did it! I was on that island with the poor guy in my mind!', 'Robi, Friday',
        'alone on uninhabitated island', 'You must read it!', '!!!!!!', 'excellent', '10', 'public',
        '2018-01-30 10:06:22', 10.0);


DROP TABLE IF EXISTS feedback_books;

CREATE TABLE feedback_books
(
    feedback_id bigint NOT NULL,
    book_id     bigint NOT NULL,
    PRIMARY KEY (feedback_id, book_id),
    FOREIGN KEY (feedback_id) REFERENCES feedback (id),
    FOREIGN KEY (book_id) REFERENCES books (id)
);

INSERT INTO feedback_books(feedback_id, book_id)
values (1, 1);


DROP TABLE IF EXISTS feedback_users;

CREATE TABLE feedback_users
(
    feedback_id bigint NOT NULL,
    user_id     bigint NOT NULL,
    PRIMARY KEY (feedback_id, user_id),
    FOREIGN KEY (feedback_id) REFERENCES feedback (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);

INSERT INTO feedback_users(feedback_id, user_id)
values (1, 1);

DROP TABLE IF EXISTS authors_books;

CREATE TABLE authors_books
(
    author_id bigint NOT NULL,
    book_id   bigint NOT NULL,
    PRIMARY KEY (author_id, book_id),
    FOREIGN KEY (author_id) REFERENCES authors (id),
    FOREIGN KEY (book_id) REFERENCES books (id)
);

INSERT INTO authors_books(author_id, book_id)
VALUES (1, 1);
