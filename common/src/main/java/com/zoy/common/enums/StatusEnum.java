package com.zoy.common.enums;

import lombok.Getter;

/**
 * 持仓状态
 * @author : owen
 * @email zhaoouyang163@gmail.com
 * @date : 2019/4/10
 */
@Getter
public enum StatusEnum {
    /**
     * 禁用
     */
    DISABLE(0),
    /**
     * 激活
     */
    ACTIVE(1);

    private Integer value;

    StatusEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }


}
