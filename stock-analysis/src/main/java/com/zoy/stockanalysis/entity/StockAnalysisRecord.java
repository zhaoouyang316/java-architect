package com.zoy.stockanalysis.entity;

import com.zoy.stockanalysis.entity.base.BaseAutoId;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 股票策略记录表
 * @author : owen
 * @email zhaoouyang163@gmail.com
 * @date : 2019/4/5
 */
@Data
@ToString(callSuper = true)
public class StockAnalysisRecord extends BaseAutoId implements Serializable{

    private static final long serialVersionUID = 7012824458698890667L;

    private String stockName;
    private Long stockAnalysisId;
    private BigDecimal settlementPrice;
    private Integer broaderMarketStatus;

}
