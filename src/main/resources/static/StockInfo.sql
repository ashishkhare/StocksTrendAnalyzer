create TABLE STOCK_INFO
(
  stock_row_id    INT AUTO_INCREMENT,
  last_trade_date DATE,
  open_price     decimal(15, 9),
  high_price     decimal(15, 9),
  low_price      decimal(15, 9),
  close_price    decimal(15, 9),
  adj_close_price decimal(15, 9),
  volume        bigint,
  symbol        varchar(10),
  isin          varchar(25),
  PRIMARY KEY (stock_row_id)
);