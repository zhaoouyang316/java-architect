package com.zoy.stockanalysis.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zoy.stockanalysis.StockAnalysisApplication;
import com.zoy.stockanalysis.entity.StockAnalysis;
import com.zoy.stockanalysis.entity.StockPriceRecord;
import com.zoy.stockanalysis.service.BigMarketService;
import com.zoy.stockanalysis.service.ItemStockService;
import com.zoy.stockanalysis.service.StockAnalysisService;
import com.zoy.stockanalysis.service.StockPriceRecordService;
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

    @Autowired
    private BigMarketService bigMarketService;

    @Autowired
    private StockPriceRecordService stockPriceRecordService;

    @Autowired
    private ItemStockService itemStockService;

    // 买入时间
    public static final String BUY_TIME="10:30";
    // 卖出时间
    public static final String SELL_TIME="9:31";

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

    /**
     * 新增策略
     */
    @Test
    public void addStockAnalysis(){
        StockAnalysis stockAnalysis=new StockAnalysis();
        stockAnalysis.setStockName("热门板块涨幅中偏上绩优股");
        stockAnalysis.setBuyTime(BUY_TIME);
        // 卖出时间 = 当前时间加一天的第二天 10:00
        /*Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,10);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        Date sellTime=DateUtil.getAfterDayDate(calendar);*/

        stockAnalysis.setSellTime(SELL_TIME);
        stockAnalysis.setCreateTime(new Date());
        stockAnalysisService.save(stockAnalysis);
        System.out.println("新增策略成功！");
    }

    /**
     * 买入股票
     */
    @Test
    public void buyStockPrice() throws Exception {
        // 股票代码
        String strCode="002349,002118,600557,000610";
        // 策略编号
        Long stockAnalysisId=1L;

        String[] strCodeArr=strCode.split(",");
        Integer flagIndex=0;
        String errorCode="";
        for(String str:strCodeArr){
            Thread.sleep(200);
            boolean flag=itemStockService.buyStock(str,stockAnalysisId);
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
    public void sellStockPrice() throws InterruptedException {
        // 查询所有持仓中的股票
        List<StockPriceRecord> listStockPriceRecord=stockPriceRecordService.findAll(new StockPriceRecord(){{
            this.setStatus(1);
        }});
        // 循环便利这些股票，修改状态为平仓，新增股票策略记录，计算盈亏入库
        for(StockPriceRecord stockPriceRecord:listStockPriceRecord){
            Thread.sleep(200);
            // 查询当前当前股票价格

        }
    }

    /**
     * 策略清空
     */

}