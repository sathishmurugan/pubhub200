CREATE TABLE users
(
 id INT PRIMARY KEY AUTO_INCREMENT,
 NAME VARCHAR(200) UNIQUE NOT NULL,
 email VARCHAR(200) NOT NULL,
 PASSWORD VARCHAR(200) NOT NULL
);

desc users;
insert into users(NAME,email,PASSWORD)values('sathish','ms.sathish.26','hai');
select * from users;
update users set email='ms.sathish.26@gmail.com' where id=1;