package com.zoy.stockanalysis.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zoy.stockanalysis.StockAnalysisApplication;
import com.zoy.stockanalysis.entity.StockAnalysis;
import com.zoy.stockanalysis.service.StockAnalysisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

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
    public void findAll(){
        System.out.println("开始");
        StockAnalysis stockAnalysis=new StockAnalysis();
        stockAnalysis.setStockName("策略名称");
        stockAnalysis.setBuyTime(new Date());
        stockAnalysis.setSellTime(new Date());
        stockAnalysis.setCreateTime(new Date());

        List<StockAnalysis> list= stockAnalysisService.findAll();
        System.out.println(JSONObject.toJSONString(list));
    }

    /**
     * 新增策略
     */
    @Test
    public void addStockAnalysis(){
        StockAnalysis stockAnalysis=new StockAnalysis();
        stockAnalysis.setStockName("热门板块涨幅中偏上绩优股");
        stockAnalysis.setBuyTime(new Date());
        // 卖出时间 = 当前时间加一天的第二天 10:00
        stockAnalysis.setSellTime(new Date());
        stockAnalysis.setCreateTime(new Date());
        stockAnalysisService.save(stockAnalysis);
        System.out.println("新增策略成功！");
    }



}