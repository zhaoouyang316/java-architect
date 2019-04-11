package com.zoy.stockanalysis.service;

import com.zoy.common.enums.BigMarketTypeEnum;
import com.zoy.common.enums.StockStatusEnum;
import com.zoy.stockanalysis.entity.StockPriceRecord;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author : owen
 * @email zhaoouyang163@gmail.com
 * @date : 2019/4/5
 */
public interface StockPriceRecordService {

    List<StockPriceRecord> findAll();
    StockPriceRecord save(StockPriceRecord stockPriceRecord);
    StockPriceRecord saveAndFlush(StockPriceRecord stockPriceRecord);
    List<StockPriceRecord> findAll(StockPriceRecord stockPriceRecord);
    StockPriceRecord findById(StockPriceRecord stockPriceRecord);

    /**
     * 保存股票行情
     * @param arr 新浪接口行情 字符串数组
     * @param bigMarketId 大盘数据
     * @param broaderMarketStatus 大盘涨跌
     * @param stockAnalysisId 股票策略编号
     * @param stockCode 股票代码
     * @param bigMarketTypeEnum 大盘类型
     * @param positionNumber 持仓数量
     * @param yesterdaySettlement 上日结算
     * @return
     */
    StockPriceRecord saveByArray(String arr, Long bigMarketId, Integer broaderMarketStatus, Long stockAnalysisId, String stockCode, BigMarketTypeEnum bigMarketTypeEnum, StockStatusEnum stockStatusEnum, Long positionNumber, BigDecimal yesterdaySettlement);
}
