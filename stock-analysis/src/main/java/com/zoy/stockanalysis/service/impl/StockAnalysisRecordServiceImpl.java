package com.zoy.stockanalysis.service.impl;

import com.zoy.common.enums.StockStatusEnum;
import com.zoy.stockanalysis.entity.StockAnalysis;
import com.zoy.stockanalysis.entity.StockAnalysisRecord;
import com.zoy.stockanalysis.entity.StockPriceRecord;
import com.zoy.stockanalysis.repostiory.StockAnalysisRecordRepository;
import com.zoy.stockanalysis.service.StockAnalysisRecordService;
import com.zoy.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.NumberUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author : owen
 * @email zhaoouyang163@gmail.com
 * @date : 2019/4/5
 */
@Service
public class StockAnalysisRecordServiceImpl implements StockAnalysisRecordService {

    @Autowired
    private StockAnalysisRecordRepository stockAnalysisRecordRepository;

    @Override
    public List<StockAnalysisRecord> findAll() {
        return stockAnalysisRecordRepository.findAll();
    }

    @Override
    public StockAnalysisRecord save(StockAnalysisRecord stockAnalysisRecord) {
        return stockAnalysisRecordRepository.save(stockAnalysisRecord);
    }

    @Override
    public StockAnalysisRecord saveAndFlush(StockAnalysisRecord stockAnalysisRecord) {
        return stockAnalysisRecordRepository.saveAndFlush(stockAnalysisRecord);
    }

    @Override
    public List<StockAnalysisRecord> findAll(StockAnalysisRecord stockAnalysisRecord) {
        stockAnalysisRecordRepository.findAll();
        return null;
    }

    @Override
    public StockAnalysisRecord getByStockAnalysisId(StockAnalysisRecord stockAnalysisRecord) {
        //构建对象
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("stockAnalysisId", ExampleMatcher.GenericPropertyMatchers.contains());
        Example<StockAnalysisRecord> ex = Example.of(stockAnalysisRecord, matcher);
        List<StockAnalysisRecord> list=stockAnalysisRecordRepository.findAll(ex);
        return CollectionUtils.isEmpty(list)?null:list.get(0);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteByStockAnalysisId(Long stockAnalysisId) {
        stockAnalysisRecordRepository.deleteAllByStockAnalysisId(stockAnalysisId);
    }


}
