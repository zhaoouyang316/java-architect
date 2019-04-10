package com.zoy.stockanalysis.service.impl;

import com.zoy.stockanalysis.entity.BigMarket;
import com.zoy.stockanalysis.entity.StockAnalysis;
import com.zoy.stockanalysis.repostiory.BigMarketRepository;
import com.zoy.stockanalysis.repostiory.StockAnalysisRepository;
import com.zoy.stockanalysis.service.BigMarketService;
import com.zoy.stockanalysis.service.StockAnalysisService;
import com.zoy.utils.SnowFlakeIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
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
public class BigMarketServiceImpl implements BigMarketService {

    @Autowired
    private BigMarketRepository bigMarketRepository;

    @Override
    public List<BigMarket> findAll() {
        return bigMarketRepository.findAll();
    }

    @Override
    public BigMarket save(BigMarket bigMarket) {
        return bigMarketRepository.save(bigMarket);
    }

    @Override
    public List<BigMarket> findAll(BigMarket bigMarket) {
        return bigMarketRepository.findAll();
    }

    @Override
    public BigMarket saveByArray(String arr) {
        String[] bigMarketArr=arr.split("\"")[1].split(",");
        BigMarket bigMarket=new BigMarket();
        bigMarket.setId(SnowFlakeIdGenerator.getDefaultNextId());
        bigMarket.setShCompositeIndex(NumberUtils.parseNumber(bigMarketArr[1],BigDecimal.class));
        bigMarket.setVolatilityPrice(NumberUtils.parseNumber(bigMarketArr[2],BigDecimal.class));
        bigMarket.setVolatilityPercentage(NumberUtils.parseNumber(bigMarketArr[3],BigDecimal.class));
        bigMarket.setTradingNumber(NumberUtils.parseNumber(bigMarketArr[4],Long.class));
        bigMarket.setTradingPrice(NumberUtils.parseNumber(bigMarketArr[5],Long.class));
        bigMarket.setCreateTime(new Date());
        return bigMarketRepository.save(bigMarket);
    }
}
