CREATE TABLE person (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL,
	birth_date DATE NOT NULL
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE contact (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	type VARCHAR(50) NOT NULL,
	value VARCHAR(50) NOT NULL,
	person_id BIGINT(20) NOT NULL,
	FOREIGN KEY (person_id) REFERENCES person(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO person (name, birth_date) values ('Tony Stark', '1972-05-10');
INSERT INTO person (name, birth_date) values ('John Snow', '1988-07-15');
INSERT INTO person (name, birth_date) values ('Bruce Wayne', '1974-05-10');
INSERT INTO person (name, birth_date) values ('Peter Parker', '1994-01-21');

INSERT INTO contact (type, value, person_id) values ('email', 'tony@stark.com', 1);
INSERT INTO contact (type, value, person_id) values ('whatsapp', '(11) 99999-9999', 1);
INSERT INTO contact (type, value, person_id) values ('whatsapp', '(11) 99999-9999', 2);
INSERT INTO contact (type, value, person_id) values ('whatsapp', '(11) 99999-9999', 3);
INSERT INTO contact (type, value, person_id) values ('whatsapp', '(11) 99999-9999', 4);
INSERT INTO contact (type, value, person_id) values ('github', '@Wolfsonfire', 2);