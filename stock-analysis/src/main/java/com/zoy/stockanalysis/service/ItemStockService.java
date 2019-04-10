package com.zoy.stockanalysis.service;

/**
 * 股票功能模块
 * @author : owen
 * @email zhaoouyang163@gmail.com
 * @date : 2019/4/10
 */
public interface ItemStockService {

    /**
     * 买入股票
     * @param stockCode
     * @return
     */
    boolean buyStock(String stockCode,Long stockAnalysisId,Long positionNumber) throws Exception;

    /**
     * 卖出股票
      */
    void sellStock();

    // 根据时间统计排行榜
    // 统计涨幅低于3%的盈利比率
    // 统计涨幅高于5%的盈利比率
    // 统计涨幅3%-5%的盈利比率
}
