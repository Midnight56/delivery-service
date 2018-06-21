CREATE TABLE roles (
role_id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
role_name VARCHAR(20) NOT NULL
) ENGINE=InnoDB;

INSERT INTO roles(role_name) VALUE('admin');
INSERT INTO roles(role_name) VALUE('customer');
INSERT INTO roles(role_name) VALUE('courier');

CREATE TABLE users_to_roles (
	fk_user_id INTEGER NOT NULL,
	fk_role_id INTEGER NOT NULL,

	PRIMARY KEY(fk_user_id, fk_role_id),

	CONSTRAINT fk_to_user FOREIGN KEY (fk_user_id) REFERENCES users(id)
		ON DELETE CASCADE
		ON UPDATE CASCADE,

	CONSTRAINT fk_to_role FOREIGN KEY (fk_role_id) REFERENCES roles(role_id)
		ON DELETE CASCADE
		ON UPDATE CASCADE
) ENGINE=InnoDB;

INSERT INTO users_to_roles(fk_user_id,fk_role_id) VALUES('1','1');
INSERT INTO users_to_roles(fk_user_id,fk_role_id) VALUES('2','2');
INSERT INTO users_to_roles(fk_user_id,fk_role_id) VALUES('3','3');
INSERT INTO users_to_roles(fk_user_id,fk_role_id) VALUES('4','3');
INSERT INTO users_to_roles(fk_user_id,fk_role_id) VALUES('4','1');