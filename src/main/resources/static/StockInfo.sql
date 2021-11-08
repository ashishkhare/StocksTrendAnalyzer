create TABLE StockInfo
(
  stockRowId    INT AUTO_INCREMENT,
  symbol        varchar(10),
  isin          varchar(25),
  openPrice     decimal(12, 7),
  highPrice     decimal(12, 7),
  lowPrice      decimal(12, 7),
  closePrice    decimal(12, 7),
  adjClosePrice decimal(12, 7),
  volume        bigint,
  lastTradeDate DATE,
  PRIMARY KEY (stockRowId)
);