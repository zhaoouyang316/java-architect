package com.zoy.common.enums;

import lombok.Getter;

/**
 * 大盘类型
 * @author : owen
 * @email zhaoouyang163@gmail.com
 * @date : 2019/4/10
 */
@Getter
public enum BigMarketTypeEnum {
    /**
     * 上证
     */
    SH(0),
    /**
     * 深证
     */
    SZ(1);

    private Integer value;

    BigMarketTypeEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

}
