CREATE TABLE User
(
  user_id    int AUTO_INCREMENT,
  first_name varchar(255),
  last_name  varchar(255),
  address   varchar(255),
  city      varchar(255),
  email_id   varchar(255),
  cell_phone varchar(255),
  PRIMARY KEY (user_id)
);