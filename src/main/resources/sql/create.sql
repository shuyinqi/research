

CREATE TABLE `OBJECTA` (
  `aid` int(11) NOT NULL AUTO_INCREMENT,
  `age` int(11) DEFAULT NULL,
  `a_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;



create table ObjectB
(
  bid   NUMBER(11) not null,
  age      NUMBER(11) not null,
  b_name   VARCHAR2(200) not null
);

create sequence S_OBJECTB
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
cache 200;