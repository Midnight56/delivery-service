CREATE TABLE users (
id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(50) NOT NULL UNIQUE,
email VARCHAR(100) NOT NULL UNIQUE,
password VARCHAR(255)NOT NULL
) ENGINE=InnoDB;

INSERT INTO users(name,email,password) VALUES('admin','admin@mail.ru','1111');
INSERT INTO users(name,email,password) VALUES('vasya','vasya@mail.ru','222');
INSERT INTO users(name,email,password) VALUES('dima','asdsf@mail.ru','1234');
INSERT INTO users(name,email,password) VALUES('pavel','xcdfv222@mail.ru','213');
