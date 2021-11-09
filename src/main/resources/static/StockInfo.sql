create TABLE STOCK_INFO
(
  stock_row_id    INT AUTO_INCREMENT,
  symbol        varchar(10),
  isin          varchar(25),
  open_price     decimal(15, 9),
  high_price     decimal(15, 9),
  low_price      decimal(15, 9),
  close_price    decimal(15, 9),
  adj_close_price decimal(15, 9),
  volume        bigint,
  last_trade_date DATE,
  PRIMARY KEY (stock_row_id)
);