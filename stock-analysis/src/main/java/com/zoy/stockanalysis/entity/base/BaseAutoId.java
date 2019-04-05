package com.zoy.stockanalysis.entity.base;

import com.zoy.utils.SnowFlakeIdGenerator;
import lombok.Data;
import lombok.ToString;

/**
 * @author : owen
 * @email zhaoouyang163@gmail.com
 * @date : 2019/4/5
 */
@Data
@ToString(callSuper = true)
public class BaseAutoId extends BaseId{

    public BaseAutoId() {
        this.id = SnowFlakeIdGenerator.getDefaultNextId();
    }

}
