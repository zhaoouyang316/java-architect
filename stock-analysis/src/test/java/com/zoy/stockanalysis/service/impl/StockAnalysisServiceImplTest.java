package com.zoy.stockanalysis.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zoy.stockanalysis.StockAnalysisApplication;
import com.zoy.stockanalysis.dao.StockAnalysisRepository;
import com.zoy.stockanalysis.entity.StockAnalysis;
import com.zoy.stockanalysis.service.StockAnalysisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * @author : owen
 * @email zhaoouyang163@gmail.com
 * @date : 2019/4/5
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StockAnalysisApplication.class)
public class StockAnalysisServiceImplTest {

    @Autowired
    private StockAnalysisService stockAnalysisService;

    @Test
    public void getStockAnalysis() throws Exception {
        System.out.println("开始");
        StockAnalysis stockAnalysis=stockAnalysisService.getStockAnalysis(1L);
        System.out.println("查询结果："+ JSONObject.toJSONString(stockAnalysis));
    }

    @Test
    public void testAdd(){
        System.out.println("开始");
        StockAnalysis stockAnalysis=new StockAnalysis();
        stockAnalysis.setStockName("策略名称");
        stockAnalysis.setBuyTime(new Date());
        stockAnalysis.setSellTime(new Date());
        stockAnalysis.setCreateTime(new Date());

        stockAnalysisService.save(stockAnalysis);
    }

    @Test
    public void testUpdate(){
        System.out.println("开始");
        StockAnalysis stockAnalysis=new StockAnalysis();
        stockAnalysis.setStockName("策略名2");
        stockAnalysis.setBuyTime(new Date());
        stockAnalysis.setSellTime(new Date());
        stockAnalysis.setCreateTime(new Date());
        stockAnalysis.setId(1L);

        stockAnalysisService.updateStockNameById(stockAnalysis.getStockName(),stockAnalysis.getId());
    }

}