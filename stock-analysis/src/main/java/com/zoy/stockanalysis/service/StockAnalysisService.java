package com.zoy.stockanalysis.service;

import com.zoy.stockanalysis.entity.StockAnalysis;

/**
 * @author : owen
 * @email zhaoouyang163@gmail.com
 * @date : 2019/4/5
 */
public interface StockAnalysisService {

    StockAnalysis getStockAnalysis(Long id);
    StockAnalysis save(StockAnalysis stockAnalysis);
    int updateStockNameById(String stockName,Long id);
}
