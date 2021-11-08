CREATE TABLE User
(
  UserID    int AUTO_INCREMENT,
  FirstName varchar(100),
  LastName  varchar(100),
  Address   varchar(255),
  City      varchar(50),
  EmailID   varchar(255),
  CellPhone varchar(15),
  PRIMARY KEY (UserID)
);