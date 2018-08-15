CREATE TABLE users (
id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(50) NOT NULL UNIQUE,
email VARCHAR(100) NOT NULL UNIQUE,
password VARCHAR(255)NOT NULL
) ENGINE=InnoDB;

INSERT INTO users(name,email,password) VALUES('admin','admin@mail.ru','$2a$10$/0qlWagvcyGhgT7BHkLCH.phnfxijOsqyArRDn4B8WcDuTXujt5ku');
INSERT INTO users(name,email,password) VALUES('vasya','vasya@mail.ru','$2a$10$Ps8x9NBtoJik/CrAzvuaQuPcyfJnJcfTe5h7J9WRsHIrdOSWLZQOi');
INSERT INTO users(name,email,password) VALUES('dima','asdsf@mail.ru','$2a$10$zOvZeUif2fg5VL8M1LRHoOKfJEs.qGQPSWvus.ZysoxTxbzdAaJmO');
INSERT INTO users(name,email,password) VALUES('pavel','xcdfv222@mail.ru','$2a$10$6RuQmZPmE3Gj/sme3FG1V.jQ49olc3nyuaVHTmSjT6wNC/9Skq0HO');
