-- *new password for user postgres
-- ALTER USER postgres PASSWORD 'admin';

-- DROP SCHEMA IF EXISTS readerblog CASCADE;
-- CREATE SCHEMA readerblog;

-- SET search_path TO readerblog;

DROP TABLE IF EXISTS authors;
CREATE TABLE authors
(
    id              BIGSERIAL PRIMARY KEY,
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
    id   SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);
INSERT INTO categories (name)
VALUES ('for adult'),
       ('for children');


DROP TABLE IF EXISTS genres;
CREATE TABLE genres
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);
INSERT INTO genres (name)
VALUES ('Detective'),
       ('Adventure');


DROP TABLE IF EXISTS roles CASCADE;
CREATE TABLE roles
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(32) NOT NULL
);
INSERT INTO roles (name)
VALUES ('ROLE_USER'),
       ('ROLE_MODERATOR'),
       ('ROLE_ADMIN'),
       ('ROLE_SUPERADMIN');


DROP TABLE IF EXISTS users CASCADE;
CREATE TABLE users
(
    id         BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(50),
    last_name  VARCHAR(50),
    nick_name  VARCHAR(50) UNIQUE,
    password   VARCHAR(80),
    email      VARCHAR(50) UNIQUE,
    email_verified BOOLEAN,
    status     VARCHAR(50)
);
INSERT INTO users (first_name, last_name, nick_name, password, email, email_verified, status)
VALUES ('Admin', 'Admin', 'admin', '100', 'admin@gmail.com', false, 'ACTIVE');


CREATE TABLE users_roles (
     user_id   BIGINT NOT NULL,
     role_id   INT NOT NULL,
    PRIMARY KEY (user_id,role_id),
    FOREIGN KEY (user_id) REFERENCES users (id)
    ON DELETE NO ACTION ON UPDATE NO ACTION,
    FOREIGN KEY (role_id)  REFERENCES roles (id)
    ON DELETE NO ACTION ON UPDATE NO ACTION
) ;

INSERT INTO users_roles (user_id, role_id)
VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4);



DROP TABLE IF EXISTS books;
CREATE TABLE books
(
    id              BIGSERIAL PRIMARY KEY,
    title           VARCHAR(255),
    genre_id        int,
    category_id     int,
    pages           int,
    format          VARCHAR(255),
    year_of_writing int,
    origin_language VARCHAR(255),
    publisher       VARCHAR(255),
    rating          real,
    FOREIGN KEY (genre_id) REFERENCES genres (id),
    FOREIGN KEY (category_id) REFERENCES categories (id)
);
INSERT INTO books (title, genre_id, category_id, pages, format, year_of_writing,
                   origin_language, publisher, rating)
VALUES ('Robinson Crusoe', 1, 1, 120, 'story', 1820, 'English', 'Neva', 10.0);

DROP TABLE IF EXISTS authors_books;
CREATE TABLE authors_books
(
    author_id       BIGINT NOT NULL,
    book_id         BIGINT NOT NULL,
    PRIMARY KEY (author_id, book_id),
    FOREIGN KEY (author_id) REFERENCES authors (id),
    FOREIGN KEY (book_id) REFERENCES books (id)
);
INSERT INTO authors_books(author_id, book_id)
VALUES (1, 1);

DROP TABLE IF EXISTS books_images;
CREATE TABLE books_images
(
    id      BIGSERIAL PRIMARY KEY,
    book_id BIGINT,
    path    VARCHAR(255),
    FOREIGN KEY (book_id) REFERENCES books (id)
);
INSERT INTO books_images (book_id, path)
VALUES (1, '1png'),
       (1, '2png');


DROP TABLE IF EXISTS users_books CASCADE;
CREATE TABLE users_books
(
    user_id BIGINT NOT NULL,
    book_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, book_id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (book_id) REFERENCES books (id)
);
INSERT INTO users_books(user_id, book_id)
VALUES (1, 1);


DROP TABLE IF EXISTS quotes;
CREATE TABLE quotes
(
    id     BIGSERIAL PRIMARY KEY,
    TEXT   TEXT,
    status varchar(50),
    rating real
);
INSERT INTO quotes(TEXT, status, rating)
values ('some quote', 'ACTIVE', 6.5);


DROP TABLE IF EXISTS quotes_books;
CREATE TABLE quotes_books
(
    quote_id BIGINT NOT NULL,
    book_id   BIGINT NOT NULL,
    PRIMARY KEY (quote_id, book_id),
    FOREIGN KEY (quote_id) REFERENCES quotes (id),
    FOREIGN KEY (book_id) REFERENCES books (id)
);
INSERT INTO quotes_books(quote_id, book_id)
values (1, 1);


DROP TABLE IF EXISTS quotes_users;
CREATE TABLE quotes_users
(
    quotes_id BIGINT NOT NULL,
    user_id   BIGINT NOT NULL,
    PRIMARY KEY (quotes_id, user_id),
    FOREIGN KEY (quotes_id) REFERENCES quotes (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);
INSERT INTO quotes_users(quotes_id, user_id)
values (1, 1);


DROP TABLE IF EXISTS key_words;
CREATE TABLE key_words
(
    id   BIGSERIAL PRIMARY KEY,
    word VARCHAR(255)
);
INSERT INTO key_words(word)
values ('child'),
       ('science');


DROP TABLE IF EXISTS key_words_books;
CREATE TABLE key_words_books
(
    key_words_id BIGINT NOT NULL,
    book_id      BIGINT NOT NULL,
    PRIMARY KEY (key_words_id, book_id),
    FOREIGN KEY (key_words_id) REFERENCES key_words (id),
    FOREIGN KEY (book_id) REFERENCES books (id)
);
INSERT INTO key_words_books(key_words_id, book_id)
values (1, 1),
       (2, 1);


DROP TABLE IF EXISTS feedbacks;
CREATE TABLE feedbacks
(
    id                   BIGSERIAL  PRIMARY KEY,
    title                VARCHAR(255),
    author_feedback      TEXT,
    genre_own_opinion    TEXT,
    category_own_opinion TEXT,
    book_feedback        TEXT,
    book_character       TEXT,
    main_theme           TEXT,
    detailed_feedback    TEXT,
    canvas               TEXT,
    overall_impression   TEXT,
    overall_mark         TEXT,
    feedback_visibility  VARCHAR(255),
    date                 timestamp,
    feedback_rating      real
);
INSERT INTO feedbacks (title, author_feedback, genre_own_opinion, category_own_opinion, book_feedback, book_character,
                       main_theme, detailed_feedback, canvas, overall_impression, overall_mark, feedback_visibility,
                       date, feedback_rating)
VALUES ('Robinson Crusoe', 'I respect this writer!', 'bromance', 'for teenagers',
        'He did it! I was on that island with the poor guy in my mind!', 'Robi, Friday',
        'alone on uninhabitated island', 'You must read it!', '!!!!!!', 'excellent', '10', 'public',
        '2018-01-30 10:06:22', 10.0);


DROP TABLE IF EXISTS feedbacks_books;
CREATE TABLE feedbacks_books
(
    feedback_id BIGINT NOT NULL,
    book_id     BIGINT NOT NULL,
    PRIMARY KEY (feedback_id, book_id),
    FOREIGN KEY (feedback_id) REFERENCES feedbacks (id),
    FOREIGN KEY (book_id) REFERENCES books (id)
);
INSERT INTO feedbacks_books(feedback_id, book_id)
values (1, 1);


DROP TABLE IF EXISTS feedbacks_users;
CREATE TABLE feedbacks_users
(
    feedback_id BIGINT NOT NULL,
    user_id     BIGINT NOT NULL,
    PRIMARY KEY (feedback_id, user_id),
    FOREIGN KEY (feedback_id) REFERENCES feedbacks (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);
INSERT INTO feedbacks_users(feedback_id, user_id)
values (1, 1);