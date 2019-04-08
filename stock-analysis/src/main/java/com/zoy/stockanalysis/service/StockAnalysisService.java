package com.zoy.stockanalysis.service;

import com.zoy.stockanalysis.entity.StockAnalysis;

import java.util.List;

/**
 * @author : owen
 * @email zhaoouyang163@gmail.com
 * @date : 2019/4/5
 */
public interface StockAnalysisService {

    List<StockAnalysis> findAll();
    StockAnalysis save(StockAnalysis stockAnalysis);
    List<StockAnalysis> findAll(StockAnalysis stockAnalysis);
}
