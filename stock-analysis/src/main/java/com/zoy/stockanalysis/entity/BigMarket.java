package com.zoy.stockanalysis.entity;

import com.zoy.stockanalysis.entity.base.BaseAutoId;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author : owen
 * @email zhaoouyang163@gmail.com
 * @date : 2019/4/10
 */
@Data
@ToString(callSuper = true)
@Entity
@Table(name="t_big_market")
public class BigMarket extends BaseAutoId implements Serializable{

    @Column(columnDefinition= DECIMAL+"'大盘指数'")
    private BigDecimal shCompositeIndex;
    @Column(columnDefinition= DECIMAL+"'波动额'")
    private BigDecimal volatilityPrice;
    @Column(columnDefinition= DECIMAL+"'波动比例'")
    private BigDecimal volatilityPercentage;
    @Column(columnDefinition= BIGINT+"'交易量'")
    private Long tradingNumber;
    @Column(columnDefinition= BIGINT+"'交易额'")
    private Long tradingPrice;
}
