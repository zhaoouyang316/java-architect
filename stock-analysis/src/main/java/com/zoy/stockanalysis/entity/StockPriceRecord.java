package com.zoy.stockanalysis.entity;

import com.zoy.stockanalysis.entity.base.BaseAutoId;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 股票价格记录表
 * @author : owen
 * @email zhaoouyang163@gmail.com
 * @date : 2019/4/5
 */
@Data
@ToString(callSuper = true)
public class StockPriceRecord extends BaseAutoId implements Serializable{

    private static final long serialVersionUID = -2256839563977327100L;

    private String stockName;
    private Long stockAnalysisId;
    private BigDecimal todayPrice;
    private BigDecimal yesterdayPrice;
    private BigDecimal spotPrice;
    private BigDecimal highPrice;
    private BigDecimal lowerPrice;
    private BigDecimal buyOnePriceFirst;
    private BigDecimal sellOnePriceFirst;
    private Long volume;
    private Long turnover;
    private Long buyOneNumber;
    private BigDecimal buyOnePrice;
    private Long buyTwoNumber;
    private BigDecimal buyTwoPrice;
    private Long buyThreeNumber;
    private BigDecimal buyThreePrice;
    private Long buyFourNumber;
    private BigDecimal buyFourPrice;
    private Long buyFiveNumber;
    private BigDecimal buyFivePrice;
    private Long sellOneNumber;
    private BigDecimal sellOnePrice;
    private Long sellTwoNumber;
    private BigDecimal sellTwoPrice;
    private Long sellThreeNumber;
    private BigDecimal sellThreePrice;
    private Long sellFourNumber;
    private BigDecimal sellFourPrice;
    private Long sellFiveNumber;
    private BigDecimal sellFivePrice;
    private Date time;
    private Integer status;
    private Integer broaderMarketStatus;

}
