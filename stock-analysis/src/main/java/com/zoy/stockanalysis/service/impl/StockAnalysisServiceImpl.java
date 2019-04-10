package com.zoy.stockanalysis.service.impl;

import com.zoy.stockanalysis.entity.StockPriceRecord;
import com.zoy.stockanalysis.repostiory.StockAnalysisRepository;
import com.zoy.stockanalysis.entity.StockAnalysis;
import com.zoy.stockanalysis.service.StockAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<StockAnalysis> findAll() {
        return stockAnalysisRepository.findAll();
    }

    @Override
    public StockAnalysis save(StockAnalysis stockAnalysis) {
        return stockAnalysisRepository.save(stockAnalysis);
    }

    @Override
    public List<StockAnalysis> findAll(StockAnalysis stockAnalysis) {
        //构建对象
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("status", ExampleMatcher.GenericPropertyMatchers.contains());
        Example<StockAnalysis> ex = Example.of(stockAnalysis, matcher);
       return stockAnalysisRepository.findAll(ex);
    }


}
