package com.zoy.stockanalysis.entity.base;

import lombok.Data;
import lombok.ToString;

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
public class BaseModel implements Serializable{

    protected static final long serialVersionUID = 7515843252853453439L;

    protected Date createTime;

}
