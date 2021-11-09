create TABLE StockInfo
(
  stock_row_id    INT AUTO_INCREMENT,
  symbol        varchar(10),
  isin          varchar(25),
  open_price     decimal(12, 7),
  high_price     decimal(12, 7),
  low_price      decimal(12, 7),
  close_price    decimal(12, 7),
  adj_close_price decimal(12, 7),
  volume        bigint,
  last_trade_date DATE,
  PRIMARY KEY (stock_row_id)
);