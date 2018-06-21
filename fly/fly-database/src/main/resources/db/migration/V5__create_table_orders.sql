CREATE TABLE orders (
id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
user_id INTEGER NOT NULL,
delivery_address VARCHAR(255) NOT NULL,
process_status BOOLEAN NOT NULL DEFAULT '1',
packing_type VARCHAR(20) NOT NULL,
fragile BOOLEAN NOT NULL DEFAULT '0',
order_date TIMESTAMP DEFAULT now(),
employee_id INTEGER NOT NULL DEFAULT '0',

CONSTRAINT fk_users_id FOREIGN KEY(user_id) references users(id) 
	ON UPDATE CASCADE 
	ON DELETE CASCADE
) ENGINE=InnoDB;

INSERT INTO orders(user_id,delivery_address,packing_type) VALUES('1','Минск','letter');
INSERT INTO orders(user_id,delivery_address,packing_type,fragile) VALUES('2','Mogilev','package','1');
INSERT INTO orders(user_id,delivery_address,packing_type) VALUES('2','Grodno','letter');
INSERT INTO orders(user_id,delivery_address,packing_type) VALUES('3','Brest','box');
INSERT INTO orders(user_id,delivery_address,packing_type) VALUES('4','Gomel','box');