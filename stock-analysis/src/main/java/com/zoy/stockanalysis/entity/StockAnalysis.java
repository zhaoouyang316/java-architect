package com.zoy.stockanalysis.entity;
import com.zoy.stockanalysis.entity.base.BaseAutoId;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 股票策略表
 * @author : owen
 * @email zhaoouyang163@gmail.com
 * @date : 2019/4/5
 */
@Data
@ToString(callSuper = true)
@Entity
@Table(name="stock_analysis")
public class StockAnalysis extends BaseAutoId implements Serializable{

    private static final long serialVersionUID = 6189793642569280182L;

    @Column(columnDefinition= STRING+"'策略名称'")
    private String stockName;
    @Column(columnDefinition= DATETIME+"'买入时间'")
    private Date buyTime;
    @Column(columnDefinition= DATETIME+"'卖出时间'")
    private Date sellTime;

}
