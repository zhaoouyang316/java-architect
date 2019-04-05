package com.zoy.stockanalysis.service.impl;

import com.zoy.stockanalysis.dao.StockAnalysisRepository;
import com.zoy.stockanalysis.entity.StockAnalysis;
import com.zoy.stockanalysis.service.StockAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : owen
 * @email zhaoouyang163@gmail.com
 * @date : 2019/4/5
 */
@Service
public class StockAnalysisServiceImpl implements StockAnalysisService{

    @Autowired
    private StockAnalysisRepository stockAnalysisRepository;

    @Override
    public StockAnalysis getStockAnalysis(Long id) {
        return stockAnalysisRepository.getStockAnalysis(id);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public StockAnalysis save(StockAnalysis stockAnalysis) {
        return stockAnalysisRepository.save(stockAnalysis);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateStockNameById(String stockName, Long id) {
        return stockAnalysisRepository.updateStockNameById(stockName,id);
    }
}
