package com.zoy.stockanalysis.service;

import com.zoy.common.enums.BigMarketTypeEnum;
import com.zoy.stockanalysis.entity.BigMarket;

import java.util.List;

/**
 * @author : owen
 * @email zhaoouyang163@gmail.com
 * @date : 2019/4/5
 */
public interface BigMarketService {

    List<BigMarket> findAll();
    BigMarket save(BigMarket bigMarket);
    List<BigMarket> findAll(BigMarket bigMarket);

    /**
     * 根据数组持久化
     * @param arr
     */
    BigMarket saveByArray(String arr,BigMarketTypeEnum bigMarketTypeEnum);

    /**
     * 保存最新大盘价格
     * @param bigMarket
     * @return
     */
    BigMarket updateById(BigMarket bigMarket);
}
