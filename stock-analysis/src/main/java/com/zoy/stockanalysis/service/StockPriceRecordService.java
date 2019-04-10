package com.zoy.stockanalysis.service;

import com.zoy.stockanalysis.entity.StockPriceRecord;

import java.util.List;

/**
 * @author : owen
 * @email zhaoouyang163@gmail.com
 * @date : 2019/4/5
 */
public interface StockPriceRecordService {

    List<StockPriceRecord> findAll();
    StockPriceRecord save(StockPriceRecord stockPriceRecord);
    List<StockPriceRecord> findAll(StockPriceRecord stockPriceRecord);
    /**
     * 根据字符串持久化
     * @param arr
     */
    StockPriceRecord saveByArray(String arr,Long bigMarketId,Integer broaderMarketStatus,Long stockAnalysisId);
}
