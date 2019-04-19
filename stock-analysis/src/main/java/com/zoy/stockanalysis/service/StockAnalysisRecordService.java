package com.zoy.stockanalysis.service;

import com.zoy.stockanalysis.entity.StockAnalysisRecord;

import java.util.List;

/**
 * @author : owen
 * @email zhaoouyang163@gmail.com
 * @date : 2019/4/5
 */
public interface StockAnalysisRecordService {

    List<StockAnalysisRecord> findAll();
    StockAnalysisRecord save(StockAnalysisRecord stockAnalysisRecord);
    StockAnalysisRecord saveAndFlush(StockAnalysisRecord stockAnalysisRecord);
    List<StockAnalysisRecord> findAll(StockAnalysisRecord stockAnalysisRecord);
    StockAnalysisRecord getByStockAnalysisId(StockAnalysisRecord stockAnalysisRecord);
    void deleteByStockAnalysisId(Long stockAnalysisId);
}
