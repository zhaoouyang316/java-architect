package com.zoy.stockanalysis.entity;

import com.zoy.stockanalysis.entity.base.BaseAutoId;
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

/**
 * 股票策略记录表
 * @author : owen
 * @email zhaoouyang163@gmail.com
 * @date : 2019/4/5
 */
@Data
@ToString(callSuper = true)
@Entity
@Table(name="t_stock_analysis_record")
public class StockAnalysisRecord extends BaseAutoId implements Serializable {

    private static final long serialVersionUID = 7012824458698890667L;

    @Column(columnDefinition=STRING+"'策略名称'")
    private String stockName;
    @Column(columnDefinition=BIGINT+"'策略编号'")
    private Long stockAnalysisId;
    @Column(columnDefinition = DECIMAL+"'结算价'")
    private BigDecimal settlementPrice;
    @Column(columnDefinition = INT+"'状态 0 大盘跌 1 大盘涨'")
    private Integer broaderMarketStatus;
    @Column(columnDefinition=DECIMAL+"'上日结算'")
    private BigDecimal yesterdaySettlement;
    @Column(columnDefinition=DECIMAL+"'总结余'")
    private BigDecimal totalSettlement;
}
