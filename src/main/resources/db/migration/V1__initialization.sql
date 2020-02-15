-- *new password for user postgres
-- ALTER USER postgres PASSWORD 'admin';

-- DROP SCHEMA IF EXISTS readerblog_test CASCADE;
-- CREATE SCHEMA readerblog_test;

-- SET search_path TO readerblog_test;


DROP TABLE IF EXISTS test;
CREATE TABLE test
(
    id          BIGSERIAL PRIMARY KEY,
    information VARCHAR(50)
);
INSERT INTO test (information)
VALUES ('Мы рады приветствовать Вас на нашем портале!');