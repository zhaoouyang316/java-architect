package com.zoy.stockanalysis.entity;

import com.zoy.stockanalysis.entity.base.BaseAutoId;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
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
@Entity
@Table(name="t_stock_price_record")
@DynamicUpdate(true)
public class StockPriceRecord extends BaseAutoId implements Serializable{

    private static final long serialVersionUID = -2256839563977327100L;

    @Column(columnDefinition=STRING+"'策略名称'")
    private String stockName;
    @Column(columnDefinition=BIGINT+"'策略编号'")
    private Long stockAnalysisId;
    @Column(columnDefinition=DECIMAL+"'今日开盘价'")
    private BigDecimal todayPrice;
    @Column(columnDefinition=DECIMAL+"'昨日收盘价'")
    private BigDecimal yesterdayPrice;
    @Column(columnDefinition=DECIMAL+"'当前价格'")
    private BigDecimal spotPrice;
    @Column(columnDefinition=DECIMAL+"'今日最高价'")
    private BigDecimal highPrice;
    @Column(columnDefinition=DECIMAL+"'今日最低价'")
    private BigDecimal lowerPrice;
    @Column(columnDefinition=DECIMAL+"'买一价'")
    private BigDecimal buyOnePriceFirst;
    @Column(columnDefinition=DECIMAL+"'卖一价'")
    private BigDecimal sellOnePriceFirst;
    @Column(columnDefinition=BIGINT+"'成交量'")
    private Long volume;
    @Column(columnDefinition=DECIMAL+"'成交额'")
    private BigDecimal turnover;
    @Column(columnDefinition=BIGINT+"'买一量'")
    private Long buyOneNumber;
    @Column(columnDefinition=DECIMAL+"'买一价'")
    private BigDecimal buyOnePrice;
    @Column(columnDefinition=BIGINT+"'买二量'")
    private Long buyTwoNumber;
    @Column(columnDefinition=DECIMAL+"'买二价'")
    private BigDecimal buyTwoPrice;
    @Column(columnDefinition=BIGINT+"'买三量'")
    private Long buyThreeNumber;
    @Column(columnDefinition=DECIMAL+"'买三价'")
    private BigDecimal buyThreePrice;
    @Column(columnDefinition=BIGINT+"'买四量'")
    private Long buyFourNumber;
    @Column(columnDefinition=DECIMAL+"'买四价'")
    private BigDecimal buyFourPrice;
    @Column(columnDefinition=BIGINT+"'买五量'")
    private Long buyFiveNumber;
    @Column(columnDefinition=DECIMAL+"'买五价'")
    private BigDecimal buyFivePrice;
    @Column(columnDefinition=BIGINT+"'卖一量'")
    private Long sellOneNumber;
    @Column(columnDefinition=DECIMAL+"'卖一价'")
    private BigDecimal sellOnePrice;
    @Column(columnDefinition=BIGINT+"'卖二量'")
    private Long sellTwoNumber;
    @Column(columnDefinition=DECIMAL+"'卖二价'")
    private BigDecimal sellTwoPrice;
    @Column(columnDefinition=BIGINT+"'卖三量'")
    private Long sellThreeNumber;
    @Column(columnDefinition=DECIMAL+"'卖三价'")
    private BigDecimal sellThreePrice;
    @Column(columnDefinition=BIGINT+"'卖四量'")
    private Long sellFourNumber;
    @Column(columnDefinition=DECIMAL+"'卖四价'")
    private BigDecimal sellFourPrice;
    @Column(columnDefinition=BIGINT+"'卖五量'")
    private Long sellFiveNumber;
    @Column(columnDefinition=DECIMAL+"'卖五价'")
    private BigDecimal sellFivePrice;
    @Column(columnDefinition=DATETIME+"'日期'")
    private Date time;
    @Column(columnDefinition=TINYINT+"'状态 0 平仓 1 持仓'")
    private Integer positionsStatus;
    @Column(columnDefinition=TINYINT+"'状态 0 大盘跌 1 大盘涨'")
    private Integer broaderMarketStatus;
    @Column(columnDefinition=BIGINT+"'大盘编号'")
    private Long bigMarketId;
    @Column(columnDefinition=DECIMAL+"'上日结算'")
    private BigDecimal yesterdaySettlement;
    @Column(columnDefinition=STRING+"'股票代码'")
    private String stockCode;
    @Column(columnDefinition=TINYINT+"'大盘类型 0 上证，1 深证'")
    private Integer bigMarketTypeEnum;
    @Column(columnDefinition=BIGINT+"'持仓数量'")
    private Long positionNumber;
    @Column(columnDefinition=DECIMAL+"'波动百分比'")
    private BigDecimal volatilityPercentage;
}
