CREATE TABLE USER
(
  user_id    int AUTO_INCREMENT,
  first_name varchar(100),
  last_name  varchar(100),
  address   varchar(255),
  city      varchar(100),
  email_id   varchar(255),
  cell_phone varchar(13),
  PRIMARY KEY (user_id)
);