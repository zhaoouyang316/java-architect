package com.zoy.stockanalysis.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zoy.common.enums.BigMarketTypeEnum;
import com.zoy.common.enums.StatusEnum;
import com.zoy.common.enums.StockStatusEnum;
import com.zoy.stockanalysis.StockAnalysisApplication;
import com.zoy.stockanalysis.entity.BigMarket;
import com.zoy.stockanalysis.entity.StockAnalysis;
import com.zoy.stockanalysis.entity.StockAnalysisRecord;
import com.zoy.stockanalysis.entity.StockPriceRecord;
import com.zoy.stockanalysis.service.*;
import com.zoy.utils.BigDecimalUtils;
import com.zoy.utils.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.NumberUtils;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
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
    private BigMarketService bigMarketService;

    @Autowired
    private StockPriceRecordService stockPriceRecordService;

    @Autowired
    private ItemStockService itemStockService;

    @Autowired
    private StockAnalysisRecordService stockAnalysisRecordService;



    @Value("${bigmarket.url.sh}")
    private String bigmarketUrlSh;

    @Value("${bigmarket.url.sz}")
    private String bigmarketUrlSz;

    @Value("${stock.url.sh}")
    private String stockUrlSh;

    @Value("${stock.url.sz}")
    private String stockUrlSz;


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
        stockAnalysis.setStatus(StatusEnum.ACTIVE.getValue());
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
        // 持仓数量
        Long positionNumber=1000L;

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
    @Transactional(rollbackFor = Exception.class)
    public void sellStockPrice() throws Exception {


        // 查询所有有效的策略
        List<StockAnalysis> listStockAnalysis=stockAnalysisService.findAll(new StockAnalysis(){{
            this.setStatus(StatusEnum.ACTIVE.getValue());
        }});

        // 总结算价
        BigDecimal totalSettlementRecord=null;


        for(StockAnalysis stockAnalysis:listStockAnalysis){

            // 大盘涨跌（上证）
            Integer shBtStatus=null;
            // 大盘涨跌（深证）
            Integer szBtStatus=null;

            // 根据策略编号 查询所有持仓中的股票
            List<StockPriceRecord> listStockPriceRecord=stockPriceRecordService.findAll(new StockPriceRecord(){{
                this.setPositionsStatus(StockStatusEnum.POSITION.getValue());
                this.setStockAnalysisId(stockAnalysis.getId());
            }});

            // 当日策略的结算价
            BigDecimal spotSettlementRecord=null;

            // 是否操作成功
            boolean flag=false;

            // 遍历股票
            for(StockPriceRecord stockPriceRecord:listStockPriceRecord){
                Thread.sleep(200);
                // 获取网络请求工具类实例
                HttpUtils httpUtils = HttpUtils.getInstance();
                Response resSh=null;
                Response resSz=null;

                // 大盘指数
                String bigMarketBody=null;
                // 股票行情价格
                String stockPriceBody=null;

                BigMarketTypeEnum bigMarketTypeEnum=null;

                // 判断是否是上证
                if(ObjectUtils.nullSafeEquals(BigMarketTypeEnum.SH.getValue(),
                        stockPriceRecord.getBigMarketTypeEnum())){
                    resSh=httpUtils.getDataSynFromNet(bigmarketUrlSh);
                    bigMarketBody=resSh.body().string();
                    // 查询上证股票
                    resSh=httpUtils.getDataSynFromNet(stockUrlSh+stockPriceRecord.getStockCode());
                    stockPriceBody=resSh.body().string();
                    bigMarketTypeEnum=BigMarketTypeEnum.SH;
                }else{
                    resSz=httpUtils.getDataSynFromNet(bigmarketUrlSz);
                    bigMarketBody=resSz.body().string();
                    // 查询深证股票
                    resSz=httpUtils.getDataSynFromNet(stockUrlSz+stockPriceRecord.getStockCode());
                    stockPriceBody=resSz.body().string();
                    bigMarketTypeEnum=BigMarketTypeEnum.SZ;
                }

                log.info("bigMarket callback {}",bigMarketBody);
                BigMarket bigMarket=bigMarketService.saveByArray(bigMarketBody,bigMarketTypeEnum);
                if(bigMarket!=null){
                    // 拼接大盘编号
                    log.info("stockPriceRecord callback {}",stockPriceBody);
                    // 将价格入库
                    Integer broaderMarketStatus=0;
                    // 判断大盘跌还是涨
                    if(bigMarket.getVolatilityPrice().compareTo(new BigDecimal(0L))==1){
                        broaderMarketStatus=1;
                    }

                    // 得到最新价格
                    String[] stockPriceArr=stockPriceBody.split("\"")[1].split(",");
                    BigDecimal nPrice= NumberUtils.parseNumber(stockPriceArr[3],BigDecimal.class);

                    // 盈亏(上日结算) = 最新股价*持仓数量-（买入价格*持仓数量）
                    // 昨日价格
                    BigDecimal yesterdayPrice=stockPriceRecord.getSpotPrice().multiply(BigDecimal.valueOf(stockPriceRecord.getPositionNumber()));
                    // 最新价格
                    BigDecimal newPrice=nPrice.multiply(BigDecimal.valueOf(stockPriceRecord.getPositionNumber()));
                    // 上日结算
                    BigDecimal yesterdaySettlement=newPrice.subtract(yesterdayPrice);

                    spotSettlementRecord=spotSettlementRecord.add(yesterdaySettlement);

                    // 平仓
                    StockPriceRecord spd=stockPriceRecordService.saveByArray(stockPriceBody,bigMarket.getId(),broaderMarketStatus,
                            stockPriceRecord.getStockAnalysisId()
                            ,stockPriceRecord.getStockCode(),bigMarketTypeEnum,
                            StockStatusEnum.UNWIND,stockPriceRecord.getPositionNumber()
                            ,yesterdaySettlement);

                    // 判断是否是上证
                    if(ObjectUtils.nullSafeEquals(BigMarketTypeEnum.SH.getValue(),
                            spd.getBigMarketTypeEnum())){
                        shBtStatus=spd.getBroaderMarketStatus();
                    }else{
                        szBtStatus=spd.getBroaderMarketStatus();
                    }


                    if(spd!=null){
                        flag=true;
                    }else{
                        log.error("股票行情入库异常！");
                    }
                }


            }

            if(flag){

                StockAnalysisRecord sard=stockAnalysisRecordService.getByStockAnalysisId(new StockAnalysisRecord(){{
                    this.setStockAnalysisId(stockAnalysis.getId());
                }});
                // 第一次平仓
                if(ObjectUtils.isEmpty(sard)){
                    // 新增股票策略记录
                    StockAnalysisRecord stockAnalysisRecord=new StockAnalysisRecord();
                    stockAnalysisRecord.setSettlementPrice(spotSettlementRecord);
                    stockAnalysisRecord.setStockAnalysisId(stockAnalysis.getId());
                    stockAnalysisRecord.setStockName(stockAnalysis.getStockName());

                    // 优先插入上证，如果策略中没有上证的就插入深证的大盘涨跌
                    if(shBtStatus!=null){
                        stockAnalysisRecord.setBroaderMarketStatus(shBtStatus);
                    }else{
                        stockAnalysisRecord.setBroaderMarketStatus(szBtStatus);
                    }
                    stockAnalysisRecordService.save(stockAnalysisRecord);
                }else{
                    //

                    //
                }

            }


        }
    }

    /**
     * 策略清空
     */

}