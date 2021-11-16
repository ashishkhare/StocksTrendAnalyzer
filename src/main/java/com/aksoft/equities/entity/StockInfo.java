package com.aksoft.equities.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @TableGenerator(name = "addition_type_id_seq", allocationSize = 1, table = "ADDITION_TYPE_SEQUENCES",
            pkColumnName = "SEQ_NAME",
            valueColumnName = "SEQ_NUMBER",
            pkColumnValue = "SEQUENCE")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "addition_type_id_seq")
    @Column(name="stock_row_id")
    @JsonIgnore
    private int stockRowId;
    @Column(name="last_trade_date")
    private Date date;
    @Column(name="open_price")
    private BigDecimal open;
    @Column(name="high_price")
    private BigDecimal high;
    @Column(name="low_price")
    private BigDecimal low;
    @Column(name="close_price")
    private BigDecimal close;
    @Column(name="adj_close_price")
    private BigDecimal adjClose;
    @Column(name="volume")
    private BigInteger volume;
    @Column(name="symbol")
    private String name;
    @Column(name="isin")
    private String isin;
}
