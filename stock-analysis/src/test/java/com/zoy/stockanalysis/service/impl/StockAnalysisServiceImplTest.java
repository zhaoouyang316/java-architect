package com.zoy.stockanalysis.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zoy.common.enums.StatusEnum;
import com.zoy.stockanalysis.StockAnalysisApplication;
import com.zoy.stockanalysis.entity.StockAnalysis;
import com.zoy.stockanalysis.service.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author : owen
 * @email zhaoouyang163@gmail.com
 * @date : 2019/4/5
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StockAnalysisApplication.class)
public class StockAnalysisServiceImplTest {

    @Autowired
    private StockAnalysisService stockAnalysisService;

    @Autowired
    private ItemStockService itemStockService;



    @Value("${bigmarket.url.sh}")
    private String bigmarketUrlSh;

    @Value("${bigmarket.url.sz}")
    private String bigmarketUrlSz;

    @Value("${stock.url.sh}")
    private String stockUrlSh;

    @Value("${stock.url.sz}")
    private String stockUrlSz;




    @Test
    public void findAll(){
        System.out.println("开始");
        StockAnalysis stockAnalysis=new StockAnalysis();
        stockAnalysis.setStockName("策略名称");
        stockAnalysis.setBuyTime(BUY_TIME);
        stockAnalysis.setSellTime(SELL_TIME);
        stockAnalysis.setCreateTime(new Date());

        List<StockAnalysis> list= stockAnalysisService.findAll();
        System.out.println(JSONObject.toJSONString(list));
    }


    // 买入时间
    public static final String BUY_TIME="10:30";
    // 卖出时间
    public static final String SELL_TIME="9:31";

    /**
     * 新增策略
     */
    @Test
    public void addStockAnalysis(){
        StockAnalysis stockAnalysis=new StockAnalysis();
        stockAnalysis.setStockName("热门板块换手率高涨幅低的");
        stockAnalysis.setBuyTime("01:00");
        stockAnalysis.setStatus(StatusEnum.ACTIVE.getValue());
        stockAnalysis.setSellTime("9:24");
        stockAnalysis.setCreateTime(new Date());
        stockAnalysisService.save(stockAnalysis);
        System.out.println("新增策略成功！");
    }

    /**
     * 买入股票
     */
    @Test
    @Transactional(rollbackFor = Exception.class)
    public void buyStockPrice() throws Exception {
        // 股票代码
        String strCode="002639";
        // 策略编号
        Long stockAnalysisId=168707356354412544L;
        // 购买金额
        Long positionNumber=10000L;

        String[] strCodeArr=strCode.split(",");
        Integer flagIndex=0;
        String errorCode="";
        for(String str:strCodeArr){
            Thread.sleep(200);
            boolean flag=itemStockService.buyStock(str,stockAnalysisId,positionNumber);
            if(flag){
                flagIndex+=1;
            }else{
                errorCode+=str+",";
            }
        }
        System.out.println("股票买入成功！数量："+flagIndex);
        System.out.println("股票买入失败股票代码："+errorCode);
    }

    /**
     * 卖出股票
     */
    @Test
    public void sellStockPrice() throws Exception {
        itemStockService.sellStock();
    }

}