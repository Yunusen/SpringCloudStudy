CREATE TABLE payment(
	id bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID' PRIMARY KEY,
	serial varchar(200) DEFAULT ''	
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8

SELECT * FROM payment
INSERT INTO payment(id, serial) values(1, 'yunusen')