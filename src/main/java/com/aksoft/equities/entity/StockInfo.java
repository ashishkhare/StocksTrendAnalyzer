package com.aksoft.equities.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "STOCK_INFO")
public class StockInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="stock_row_id")
    private int stockRowId;
    @Column(name="symbol")
    private String symbol;
    @Column(name="isin")
    private String isin;
    @Column(name="open_price")
    private BigDecimal openPrice;
    @Column(name="high_price")
    private BigDecimal highPrice;
    @Column(name="low_price")
    private BigDecimal lowPrice;
    @Column(name="close_price")
    private BigDecimal closePrice;
    @Column(name="adj_close_price")
    private BigDecimal adjClosePrice;
    @Column(name="volume")
    private BigInteger volume;
    @Column(name="last_trade_date")
    private Date lastTradeDate;


}
