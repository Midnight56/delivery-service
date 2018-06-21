CREATE TABLE user_options (
option_id INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
rating INT(1) DEFAULT '0',
block INT(1) DEFAULT '0',

CONSTRAINT fk_option_id FOREIGN KEY(option_id) REFERENCES users(id)
	ON DELETE CASCADE
	ON UPDATE CASCADE
) ENGINE=InnoDB;

INSERT INTO user_options(option_id,rating,block) VALUES('1','5','0');
INSERT INTO user_options(option_id,rating,block) VALUES('2','5','0');
INSERT INTO user_options(option_id,rating,block) VALUES('3','5','0');
INSERT INTO user_options(option_id,rating,block) VALUES('4','5','0');