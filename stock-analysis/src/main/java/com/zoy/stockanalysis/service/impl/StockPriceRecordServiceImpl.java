package com.zoy.stockanalysis.service.impl;

import com.zoy.common.enums.BigMarketTypeEnum;
import com.zoy.common.enums.StatusEnum;
import com.zoy.common.enums.StockStatusEnum;
import com.zoy.stockanalysis.entity.StockPriceRecord;
import com.zoy.stockanalysis.repostiory.StockPriceRecordRepository;
import com.zoy.stockanalysis.service.StockPriceRecordService;
import com.zoy.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.util.NumberUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author : owen
 * @email zhaoouyang163@gmail.com
 * @date : 2019/4/5
 */
@Service
public class StockPriceRecordServiceImpl implements StockPriceRecordService {

    @Autowired
    private StockPriceRecordRepository stockPriceRecordRepository;

    @Override
    public List<StockPriceRecord> findAll() {
        return stockPriceRecordRepository.findAll();
    }

    @Override
    public StockPriceRecord save(StockPriceRecord stockPriceRecord) {
        return stockPriceRecordRepository.save(stockPriceRecord);
    }

    @Override
    public StockPriceRecord saveAndFlush(StockPriceRecord stockPriceRecord) {
        return stockPriceRecordRepository.saveAndFlush(stockPriceRecord);
    }


    @Override
    public List<StockPriceRecord> findAll(StockPriceRecord stockPriceRecord) {
        //构建对象
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("status", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("stockAnalysisId", ExampleMatcher.GenericPropertyMatchers.contains());
        Example<StockPriceRecord> ex = Example.of(stockPriceRecord, matcher);
        return stockPriceRecordRepository.findAll(ex);
    }

    @Override
    public StockPriceRecord findById(StockPriceRecord stockPriceRecord) {
        return stockPriceRecordRepository.findById(stockPriceRecord.getId()).orElse(null);
    }

    @Override
    public StockPriceRecord saveByArray(String arr,Long bigMarketId,
                                        Integer broaderMarketStatus,Long stockAnalysisId,
                                        String stockCode,BigMarketTypeEnum bigMarketTypeEnum,
                                        StockStatusEnum stockStatusEnum,Long positionNumber,
                                        BigDecimal yesterdaySettlement,BigDecimal volatilityPercentage) {

        String[] stockPriceArr=arr.split("\"")[1].split(",");
        StockPriceRecord stockPriceRecord=new StockPriceRecord();
        // 策略编号
        stockPriceRecord.setStockAnalysisId(stockAnalysisId);
        // 股票相关
        stockPriceRecord.setStockName(stockPriceArr[0]);
        stockPriceRecord.setTodayPrice(NumberUtils.parseNumber(stockPriceArr[1],BigDecimal.class));
        stockPriceRecord.setYesterdayPrice(NumberUtils.parseNumber(stockPriceArr[2],BigDecimal.class));
        stockPriceRecord.setSpotPrice(NumberUtils.parseNumber(stockPriceArr[3],BigDecimal.class));
        stockPriceRecord.setHighPrice(NumberUtils.parseNumber(stockPriceArr[4],BigDecimal.class));
        stockPriceRecord.setLowerPrice(NumberUtils.parseNumber(stockPriceArr[5],BigDecimal.class));
        stockPriceRecord.setBuyOnePriceFirst(NumberUtils.parseNumber(stockPriceArr[6],BigDecimal.class));
        stockPriceRecord.setSellOnePriceFirst(NumberUtils.parseNumber(stockPriceArr[7],BigDecimal.class));
        stockPriceRecord.setVolume(NumberUtils.parseNumber(stockPriceArr[8],Long.class));
        stockPriceRecord.setTurnover(NumberUtils.parseNumber(stockPriceArr[9],BigDecimal.class));

        stockPriceRecord.setBuyOneNumber(NumberUtils.parseNumber(stockPriceArr[10],Long.class));
        stockPriceRecord.setBuyOnePrice(NumberUtils.parseNumber(stockPriceArr[11],BigDecimal.class));
        stockPriceRecord.setBuyTwoNumber(NumberUtils.parseNumber(stockPriceArr[12],Long.class));
        stockPriceRecord.setBuyTwoPrice(NumberUtils.parseNumber(stockPriceArr[13],BigDecimal.class));
        stockPriceRecord.setBuyThreeNumber(NumberUtils.parseNumber(stockPriceArr[14],Long.class));
        stockPriceRecord.setBuyThreePrice(NumberUtils.parseNumber(stockPriceArr[15],BigDecimal.class));
        stockPriceRecord.setBuyFourNumber(NumberUtils.parseNumber(stockPriceArr[16],Long.class));
        stockPriceRecord.setBuyFourPrice(NumberUtils.parseNumber(stockPriceArr[17],BigDecimal.class));
        stockPriceRecord.setBuyFiveNumber(NumberUtils.parseNumber(stockPriceArr[18],Long.class));
        stockPriceRecord.setBuyFivePrice(NumberUtils.parseNumber(stockPriceArr[19],BigDecimal.class));

        stockPriceRecord.setSellOneNumber(NumberUtils.parseNumber(stockPriceArr[20],Long.class));
        stockPriceRecord.setSellOnePrice(NumberUtils.parseNumber(stockPriceArr[21],BigDecimal.class));
        stockPriceRecord.setSellTwoNumber(NumberUtils.parseNumber(stockPriceArr[22],Long.class));
        stockPriceRecord.setSellTwoPrice(NumberUtils.parseNumber(stockPriceArr[23],BigDecimal.class));
        stockPriceRecord.setSellThreeNumber(NumberUtils.parseNumber(stockPriceArr[24],Long.class));
        stockPriceRecord.setSellThreePrice(NumberUtils.parseNumber(stockPriceArr[25],BigDecimal.class));
        stockPriceRecord.setSellFourNumber(NumberUtils.parseNumber(stockPriceArr[26],Long.class));
        stockPriceRecord.setSellFourPrice(NumberUtils.parseNumber(stockPriceArr[27],BigDecimal.class));
        stockPriceRecord.setSellFiveNumber(NumberUtils.parseNumber(stockPriceArr[28],Long.class));
        stockPriceRecord.setSellFivePrice(NumberUtils.parseNumber(stockPriceArr[29],BigDecimal.class));
        String time=stockPriceArr[30]+" "+stockPriceArr[31];

        stockPriceRecord.setTime(DateUtil.strToDateSql(time));
        stockPriceRecord.setPositionsStatus(stockStatusEnum.getValue());
        stockPriceRecord.setBigMarketId(bigMarketId);
        stockPriceRecord.setCreateTime(new Date());
        stockPriceRecord.setBroaderMarketStatus(broaderMarketStatus);
        stockPriceRecord.setStockCode(stockCode);
        stockPriceRecord.setBigMarketTypeEnum(bigMarketTypeEnum.getValue());
        stockPriceRecord.setYesterdaySettlement(yesterdaySettlement);
        stockPriceRecord.setStatus(StatusEnum.ACTIVE.getValue());

        // 根据设定购买金额计算购买股票数量
        // 平仓
        if(StockStatusEnum.UNWIND.getValue().equals(stockStatusEnum.getValue())){
            stockPriceRecord.setPositionNumber(positionNumber);
            stockPriceRecord.setVolatilityPercentage(volatilityPercentage);
        }else{
        // 持仓
            String str=(BigDecimal.valueOf(positionNumber).divide(stockPriceRecord.getSpotPrice(),BigDecimal.ROUND_HALF_UP)).toString();
            str=str.substring(0,str.length()-2)+"00";
            stockPriceRecord.setPositionNumber(Long.valueOf(str));
        }

        return stockPriceRecordRepository.save(stockPriceRecord);
    }
}
