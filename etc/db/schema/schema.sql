CREATE DATABASE  IF NOT EXISTS `autentiadb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `autentiadb`;


CREATE TABLE Teacher (id bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY, name VARCHAR(100) NOT NULL,  email VARCHAR(20));

CREATE TABLE Course (id bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
 title VARCHAR(100) NOT NULL, hours SMALLINT NOT NULL,
  level VARCHAR(20),active BOOLEAN,teacherId bigint(20) ,
  CONSTRAINT `FK_teacher`  FOREIGN KEY (teacherId) REFERENCES Teacher(id));

INSERT INTO Teacher (id,name, email) VALUES (1,'Jorge Romero', 'jjj@ole.com');

INSERT INTO Teacher (id,name, email) VALUES (2,'Adolfo Dominguez', 'ddd@ole.com');

INSERT INTO Course (title, hours,level,active,teacherId) VALUES ('Learn PHP', 20, 'INTERMEDIATE',TRUE,1);

INSERT INTO Course (title, hours,level,active,teacherId)  VALUES ('Learn JAVA', 10, 'INTERMEDIATE',FALSE,2);





