package com.zoy.common.enums;

import lombok.Getter;

/**
 * 持仓状态
 * @author : owen
 * @email zhaoouyang163@gmail.com
 * @date : 2019/4/10
 */
@Getter
public enum StockStatusEnum {
    /**
     * 平仓
     */
    UNWIND(0),
    /**
     * 持仓
     */
    POSITION(1);

    private Integer value;

    StockStatusEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }


}
