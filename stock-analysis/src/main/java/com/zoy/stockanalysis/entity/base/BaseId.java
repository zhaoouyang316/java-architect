package com.zoy.stockanalysis.entity.base;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author : owen
 * @email zhaoouyang163@gmail.com
 * @date : 2019/4/5
 */
@Data
@ToString(callSuper = true)
@MappedSuperclass
public class BaseId extends BaseModel{

    @Id
    @Column(columnDefinition=BIGINT+"'序号'")
    public Long id;

}
