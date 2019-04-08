package com.zoy.stockanalysis.entity.base;

import com.zoy.common.constant.ColumnType;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * @author : owen
 * @email zhaoouyang163@gmail.com
 * @date : 2019/4/5
 */
@Data
@ToString(callSuper = true)
@MappedSuperclass
public class BaseModel extends ColumnType implements Serializable{

    protected static final long serialVersionUID = 7515843252853453439L;

    @Column(columnDefinition=DATETIME+"'创建时间'")
    protected Date createTime;
    @Column(columnDefinition=DATETIME+"'更新时间'")
    protected Date updateTime;

}
