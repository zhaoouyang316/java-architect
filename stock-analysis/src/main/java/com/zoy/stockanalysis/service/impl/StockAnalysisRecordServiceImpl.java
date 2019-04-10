package com.zoy.stockanalysis.service.impl;

import com.zoy.common.enums.StockStatusEnum;
import com.zoy.stockanalysis.entity.StockAnalysisRecord;
import com.zoy.stockanalysis.entity.StockPriceRecord;
import com.zoy.stockanalysis.repostiory.StockAnalysisRecordRepository;
import com.zoy.stockanalysis.service.StockAnalysisRecordService;
import com.zoy.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public List<StockAnalysisRecord> findAll(StockAnalysisRecord stockAnalysisRecord) {
        stockAnalysisRecordRepository.findAll();
        return null;
    }


}
