package com.zoy.stockanalysis.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zoy.common.enums.BigMarketTypeEnum;
import com.zoy.common.enums.StatusEnum;
import com.zoy.common.enums.StockStatusEnum;
import com.zoy.stockanalysis.entity.BigMarket;
import com.zoy.stockanalysis.entity.StockAnalysis;
import com.zoy.stockanalysis.entity.StockAnalysisRecord;
import com.zoy.stockanalysis.entity.StockPriceRecord;
import com.zoy.stockanalysis.service.*;
import com.zoy.utils.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.NumberUtils;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author : owen
 * @email zhaoouyang163@gmail.com
 * @date : 2019/4/5
 */
@Service
@Slf4j
public class ItemStockServiceImpl implements ItemStockService {

    @Autowired
    private StockPriceRecordService tockPriceRecordService;
    @Autowired
    private BigMarketService bigMarketService;
    @Autowired
    private StockAnalysisRecordService stockAnalysisRecordService;
    @Autowired
    private StockAnalysisService stockAnalysisService;
    @Autowired
    private StockPriceRecordService stockPriceRecordService;

    @Value("${bigmarket.url.sh}")
    private String bigmarketUrlSh;

    @Value("${bigmarket.url.sz}")
    private String bigmarketUrlSz;

    @Value("${stock.url.sh}")
    private String stockUrlSh;

    @Value("${stock.url.sz}")
    private String stockUrlSz;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean buyStock(String stockCode,Long stockAnalysisId,Long positionNumber) throws Exception {
        // 标记是否请求成功
        boolean flag=false;
        // 默认上证
        BigMarketTypeEnum bigMarketTypeEnum=BigMarketTypeEnum.SH;

        //获取网络请求工具类实例
        HttpUtils httpUtils = HttpUtils.getInstance();
        // 大盘指数
        Response resBigMarket=httpUtils.getDataSynFromNet(bigmarketUrlSh);
        // 大盘数据持久化
        if(resBigMarket.isSuccessful()){

                Response resStock=httpUtils.getDataSynFromNet(stockUrlSh+stockCode);
                if(resStock.isSuccessful()){
                    String resStockBody=resStock.body().string();
                    // 如果不是上证就查深证
                    String ckNull=resStockBody.split("\"")[1];

                    // 判断是否是深证
                    if(ckNull==null|| "".equals(ckNull)){
                        resStock=httpUtils.getDataSynFromNet(stockUrlSz+stockCode);
                        resStockBody=resStock.body().string();
                        bigMarketTypeEnum=BigMarketTypeEnum.SZ;
                        resBigMarket=httpUtils.getDataSynFromNet(bigmarketUrlSz);
                    }else if(Double.valueOf(ckNull.split(",")[3])==0){
                        // 如果上证查了价格是空的也有可能是 深证
                        resStock=httpUtils.getDataSynFromNet(stockUrlSz+stockCode);
                        resStockBody=resStock.body().string();
                        bigMarketTypeEnum=BigMarketTypeEnum.SZ;
                        resBigMarket=httpUtils.getDataSynFromNet(bigmarketUrlSz);
                    }

                    String body1=resBigMarket.body().string();
                    log.info("bigMarket callback {}",body1);
                    BigMarket bigMarket=bigMarketService.saveByArray(body1,bigMarketTypeEnum);
                    if(bigMarket!=null){
                        // 拼接大盘编号
                        log.info("stockPriceRecord callback {}",resStockBody);
                        // 将价格入库
                        Integer broaderMarketStatus=0;
                        if(bigMarket.getVolatilityPrice().compareTo(new BigDecimal(0L))==1){
                            broaderMarketStatus=1;
                        }
                        StockPriceRecord stockPriceRecord=tockPriceRecordService.saveByArray(resStockBody,bigMarket.getId()
                                ,broaderMarketStatus,stockAnalysisId,stockCode
                                ,bigMarketTypeEnum, StockStatusEnum.POSITION,positionNumber,BigDecimal.valueOf(0),null);
                        if(stockPriceRecord!=null){
                            flag=true;
                        }else{
                            log.error("股票行情入库异常！");
                        }
                    }
                }else{
                    log.error("股票行情接口异常！");
                }

            }else{
                log.error("大盘入库异常！");
            }

        if(!flag){
            throw new RuntimeException("新增股票失败！");
        }

        return flag;
    }

    /**
     * 卖出股票
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void sellStock() throws Exception {

        // TODO: 2019/4/11 待优化，改为map形式
        StockAnalysis sas=new StockAnalysis();
        sas.setStatus(StatusEnum.ACTIVE.getValue());
        sas.setUpdateTime(null);
        sas.setId(null);

        // 查询所有有效的策略
        List<StockAnalysis> listStockAnalysis=stockAnalysisService.findAll(sas);
        // 总结算价
        BigDecimal totalSettlementRecord=null;

        int count=0;
        for (StockAnalysis stockAnalysis : listStockAnalysis) {
            // 大盘涨跌（上证）
            Integer shBtStatus = null;
            // 大盘涨跌（深证）
            Integer szBtStatus = null;

            // TODO: 2019/4/11 待优化
            StockPriceRecord sprd = new StockPriceRecord();
            sprd.setPositionsStatus(StockStatusEnum.POSITION.getValue());
            sprd.setStockAnalysisId(stockAnalysis.getId());
            sprd.setId(null);
            sprd.setUpdateTime(null);
            // 根据策略编号 查询所有持仓中的股票
            List<StockPriceRecord> listStockPriceRecord = stockPriceRecordService.findAll(sprd);

            // 当日策略的结算价
            BigDecimal spotSettlementRecord = BigDecimal.valueOf(0);

            // 是否操作成功
            boolean flag = false;

            // 遍历股票
            for (StockPriceRecord stockPriceRecord : listStockPriceRecord) {
                Thread.sleep(200);
                // 获取网络请求工具类实例
                HttpUtils httpUtils = HttpUtils.getInstance();
                Response resSh = null;
                Response resSz = null;
                // 大盘指数
                String bigMarketBody = null;
                // 股票行情价格
                String stockPriceBody = null;

                BigMarketTypeEnum bigMarketTypeEnum = null;

                // 判断是否是上证
                if (ObjectUtils.nullSafeEquals(BigMarketTypeEnum.SH.getValue(),
                        stockPriceRecord.getBigMarketTypeEnum())) {
                    resSh = httpUtils.getDataSynFromNet(bigmarketUrlSh);
                    bigMarketBody = resSh.body().string();
                    // 查询上证股票
                    resSh = httpUtils.getDataSynFromNet(stockUrlSh + stockPriceRecord.getStockCode());
                    stockPriceBody = resSh.body().string();
                    bigMarketTypeEnum = BigMarketTypeEnum.SH;
                } else {
                    resSz = httpUtils.getDataSynFromNet(bigmarketUrlSz);
                    bigMarketBody = resSz.body().string();
                    // 查询深证股票
                    resSz = httpUtils.getDataSynFromNet(stockUrlSz + stockPriceRecord.getStockCode());
                    stockPriceBody = resSz.body().string();
                    bigMarketTypeEnum = BigMarketTypeEnum.SZ;
                }

                log.info("bigMarket callback {}", bigMarketBody);
                BigMarket bigMarket = bigMarketService.saveByArray(bigMarketBody, bigMarketTypeEnum);
                if (bigMarket != null) {
                    // 拼接大盘编号
                    log.info("stockPriceRecord callback {}", stockPriceBody);
                    // 将价格入库
                    Integer broaderMarketStatus = 0;
                    // 判断大盘跌还是涨
                    if (bigMarket.getVolatilityPrice().compareTo(new BigDecimal(0L)) == 1) {
                        broaderMarketStatus = 1;
                    }

                    // 得到最新价格
                    String[] stockPriceArr = stockPriceBody.split("\"")[1].split(",");
                    BigDecimal nPrice = NumberUtils.parseNumber(stockPriceArr[3], BigDecimal.class);

                    // 盈亏(上日结算) = 最新股价*持仓数量-（买入价格*持仓数量）
                    // 昨日价格
                    BigDecimal yesterdayPrice = stockPriceRecord.getSpotPrice().multiply(BigDecimal.valueOf(stockPriceRecord.getPositionNumber()));
                    // 最新价格
                    BigDecimal newPrice = nPrice.multiply(BigDecimal.valueOf(stockPriceRecord.getPositionNumber()));
                    // 上日结算
                    BigDecimal yesterdaySettlement = newPrice.subtract(yesterdayPrice);

                    spotSettlementRecord = spotSettlementRecord.add(yesterdaySettlement);

                    // 波动比率 = (新价格-旧价格)/旧价格
                    Double volatilityPercentage=(newPrice.doubleValue()-yesterdayPrice.doubleValue())/yesterdayPrice.doubleValue();
                    // 平仓
                    StockPriceRecord spd = stockPriceRecordService.saveByArray(stockPriceBody, bigMarket.getId(), broaderMarketStatus,
                            stockPriceRecord.getStockAnalysisId()
                            , stockPriceRecord.getStockCode(), bigMarketTypeEnum,
                            StockStatusEnum.UNWIND, stockPriceRecord.getPositionNumber()
                            , yesterdaySettlement,BigDecimal.valueOf(volatilityPercentage));


                    // 修改旧记录状态为平仓
                    StockPriceRecord oldSpd=new StockPriceRecord();
                    BeanUtils.copyProperties(stockPriceRecord,oldSpd);
                    oldSpd.setId(stockPriceRecord.getId());
                    oldSpd.setPositionsStatus(StockStatusEnum.UNWIND.getValue());
                    oldSpd.setStatus(StatusEnum.DISABLE.getValue());

                    stockPriceRecordService.saveAndFlush(oldSpd);

                    // 判断是否是上证
                    if (ObjectUtils.nullSafeEquals(BigMarketTypeEnum.SH.getValue(),
                            spd.getBigMarketTypeEnum())) {
                        shBtStatus = spd.getBroaderMarketStatus();
                    } else {
                        szBtStatus = spd.getBroaderMarketStatus();
                    }

                    if (spd != null) {
                        flag = true;
                    } else {
                        log.error("股票行情入库异常！");
                    }
                }
            }

            if (flag) {
                // 股票策略记录表
                StockAnalysisRecord sards=new StockAnalysisRecord();
                sards.setStockAnalysisId(stockAnalysis.getId());
                sards.setId(null);
                sards.setUpdateTime(null);
                StockAnalysisRecord sard = stockAnalysisRecordService.getByStockAnalysisId(sards);

                // 第一次平仓
                if (ObjectUtils.isEmpty(sard)) {
                    // 新增股票策略记录
                    StockAnalysisRecord stockAnalysisRecord = new StockAnalysisRecord();
                    stockAnalysisRecord.setSettlementPrice(spotSettlementRecord);
                    stockAnalysisRecord.setStockAnalysisId(stockAnalysis.getId());
                    stockAnalysisRecord.setStockName(stockAnalysis.getStockName());
                    stockAnalysisRecord.setTotalSettlement(spotSettlementRecord);
                    // 优先插入上证，如果策略中没有上证的就插入深证的大盘涨跌
                    if (shBtStatus != null) {
                        stockAnalysisRecord.setBroaderMarketStatus(shBtStatus);
                    } else {
                        stockAnalysisRecord.setBroaderMarketStatus(szBtStatus);
                    }
                    stockAnalysisRecord.setStatus(StockStatusEnum.POSITION.getValue());
                    stockAnalysisRecordService.save(stockAnalysisRecord);
                } else {
                    StockAnalysisRecord stockAnalysisRecord = new StockAnalysisRecord();
                    // 结算价=最新价
                    stockAnalysisRecord.setSettlementPrice(spotSettlementRecord);
                    // 上日结算=结算价
                    stockAnalysisRecord.setTotalSettlement(sard.getSettlementPrice());
                    // 总结余=总结余+新总结余
                    stockAnalysisRecord.setTotalSettlement(spotSettlementRecord.add(sard.getTotalSettlement()));
                    stockAnalysisRecord.setStockAnalysisId(stockAnalysis.getId());
                    stockAnalysisRecord.setStockName(stockAnalysis.getStockName());
                    // 优先插入上证，如果策略中没有上证的就插入深证的大盘涨跌
                    if (shBtStatus != null) {
                        stockAnalysisRecord.setBroaderMarketStatus(shBtStatus);
                    } else {
                        stockAnalysisRecord.setBroaderMarketStatus(szBtStatus);
                    }
                    stockAnalysisRecordService.save(stockAnalysisRecord);

                    StockAnalysisRecord sardNew=new StockAnalysisRecord();
                    BeanUtils.copyProperties(sard,sardNew);
                    sardNew.setId(sard.getId());
                    sardNew.setStatus(StockStatusEnum.UNWIND.getValue());
                    // 修改状态为平仓
                    stockAnalysisRecordService.saveAndFlush(sardNew);
                }
            }


        }



    }

    @Override
    public String searchStock(Long stockAnalysisId) throws Exception {

        // TODO: 2019/4/11 待优化，改为map形式
        StockAnalysis sas=new StockAnalysis();
        sas.setStatus(StatusEnum.ACTIVE.getValue());
        sas.setId(stockAnalysisId);
        sas.setUpdateTime(null);

        // 查询所有有效的策略
        List<StockAnalysis> listStockAnalysis=stockAnalysisService.findAll(sas);

        JSONObject json=new JSONObject();

        JSONArray jsonArray=new JSONArray();

        int count=0;
        for (StockAnalysis stockAnalysis : listStockAnalysis) {
            // 大盘涨跌（上证）
            Integer shBtStatus = null;
            // 大盘涨跌（深证）
            Integer szBtStatus = null;

            // TODO: 2019/4/11 待优化
            StockPriceRecord sprd = new StockPriceRecord();
            sprd.setPositionsStatus(StockStatusEnum.POSITION.getValue());
            sprd.setStockAnalysisId(stockAnalysis.getId());
            sprd.setId(null);
            sprd.setUpdateTime(null);
            // 根据策略编号 查询所有持仓中的股票
            List<StockPriceRecord> listStockPriceRecord = stockPriceRecordService.findAll(sprd);

            // 当日策略的结算价
            BigDecimal spotSettlementRecord = BigDecimal.valueOf(0);

            // 是否操作成功
            boolean flag = false;

            // 遍历股票
            for (StockPriceRecord stockPriceRecord : listStockPriceRecord) {
                Thread.sleep(200);
                // 获取网络请求工具类实例
                HttpUtils httpUtils = HttpUtils.getInstance();
                Response resSh = null;
                Response resSz = null;
                // 大盘指数
                String bigMarketBody = null;
                // 股票行情价格
                String stockPriceBody = null;

                BigMarketTypeEnum bigMarketTypeEnum = null;

                // 判断是否是上证
                if (ObjectUtils.nullSafeEquals(BigMarketTypeEnum.SH.getValue(),
                        stockPriceRecord.getBigMarketTypeEnum())) {
                    resSh = httpUtils.getDataSynFromNet(bigmarketUrlSh);
                    bigMarketBody = resSh.body().string();
                    // 查询上证股票
                    resSh = httpUtils.getDataSynFromNet(stockUrlSh + stockPriceRecord.getStockCode());
                    stockPriceBody = resSh.body().string();
                    bigMarketTypeEnum = BigMarketTypeEnum.SH;
                } else {
                    resSz = httpUtils.getDataSynFromNet(bigmarketUrlSz);
                    bigMarketBody = resSz.body().string();
                    // 查询深证股票
                    resSz = httpUtils.getDataSynFromNet(stockUrlSz + stockPriceRecord.getStockCode());
                    stockPriceBody = resSz.body().string();
                    bigMarketTypeEnum = BigMarketTypeEnum.SZ;
                }

                log.info("bigMarket callback {}", bigMarketBody);
                BigMarket bigMarket = bigMarketService.saveByArray(bigMarketBody, bigMarketTypeEnum);
                if (bigMarket != null) {
                    // 拼接大盘编号
                    log.info("stockPriceRecord callback {}", stockPriceBody);
                    // 将价格入库
                    Integer broaderMarketStatus = 0;
                    // 判断大盘跌还是涨
                    if (bigMarket.getVolatilityPrice().compareTo(new BigDecimal(0L)) == 1) {
                        broaderMarketStatus = 1;
                    }

                    // 得到最新价格
                    String[] stockPriceArr = stockPriceBody.split("\"")[1].split(",");
                    BigDecimal nPrice = NumberUtils.parseNumber(stockPriceArr[3], BigDecimal.class);

                    // 盈亏(上日结算) = 最新股价*持仓数量-（买入价格*持仓数量）
                    // 昨日价格
                    BigDecimal yesterdayPrice = stockPriceRecord.getSpotPrice().multiply(BigDecimal.valueOf(stockPriceRecord.getPositionNumber()));
                    // 最新价格
                    BigDecimal newPrice = nPrice.multiply(BigDecimal.valueOf(stockPriceRecord.getPositionNumber()));
                    // 上日结算
                    BigDecimal yesterdaySettlement = newPrice.subtract(yesterdayPrice);

                    spotSettlementRecord = spotSettlementRecord.add(yesterdaySettlement);

                    // 波动比率 = (新价格-旧价格)/旧价格
                    Double volatilityPercentage=(newPrice.doubleValue()-yesterdayPrice.doubleValue())/yesterdayPrice.doubleValue();

                    System.out.println("股票买入单价："+ stockPriceRecord.getSpotPrice());
                    System.out.println("股票最新单价："+ nPrice);
                    System.out.println("股票买入数量："+ stockPriceRecord.getPositionNumber());

                    System.out.println("股票名称："+stockPriceRecord.getStockName());
                    System.out.println("股票代码："+stockPriceRecord.getStockCode());
                    System.out.println("波动比率："+volatilityPercentage);
                    System.out.println("波动价格："+yesterdaySettlement);
                    System.out.println("===================分割线=======================");

                    JSONObject jsonStr=new JSONObject();
                    jsonStr.put("spotPrice",stockPriceRecord.getSpotPrice());
                    jsonStr.put("newPrice",nPrice);
                    jsonStr.put("positionNumber",stockPriceRecord.getPositionNumber());
                    jsonStr.put("stockName",stockPriceRecord.getStockName());
                    jsonStr.put("stockCode",stockPriceRecord.getStockCode());
                    jsonStr.put("volatilityPercentage",stockPriceRecord.getVolatilityPercentage());
                    jsonStr.put("yesterdaySettlement",stockPriceRecord.getYesterdaySettlement());
                    jsonArray.add(jsonStr);
                }
            }

                // 股票策略记录表
                StockAnalysisRecord sards=new StockAnalysisRecord();
                sards.setStockAnalysisId(stockAnalysis.getId());
                sards.setId(null);
                sards.setUpdateTime(null);
                StockAnalysisRecord sard = stockAnalysisRecordService.getByStockAnalysisId(sards);

                // 第一次平仓
                if (ObjectUtils.isEmpty(sard)) {
                    // 新增股票策略记录
                    StockAnalysisRecord stockAnalysisRecord = new StockAnalysisRecord();
                    stockAnalysisRecord.setSettlementPrice(spotSettlementRecord);
                    stockAnalysisRecord.setStockAnalysisId(stockAnalysis.getId());
                    stockAnalysisRecord.setStockName(stockAnalysis.getStockName());
                    stockAnalysisRecord.setTotalSettlement(spotSettlementRecord);
                    // 优先插入上证，如果策略中没有上证的就插入深证的大盘涨跌
                    if (shBtStatus != null) {
                        stockAnalysisRecord.setBroaderMarketStatus(shBtStatus);
                    } else {
                        stockAnalysisRecord.setBroaderMarketStatus(szBtStatus);
                    }
                    stockAnalysisRecord.setStatus(StockStatusEnum.POSITION.getValue());
                    // stockAnalysisRecordService.save(stockAnalysisRecord);
                } else {
                    StockAnalysisRecord stockAnalysisRecord = new StockAnalysisRecord();
                    // 结算价=最新价
                    stockAnalysisRecord.setSettlementPrice(spotSettlementRecord);
                    // 上日结算=结算价
                    stockAnalysisRecord.setTotalSettlement(sard.getSettlementPrice());
                    // 总结余=总结余+新总结余
                    stockAnalysisRecord.setTotalSettlement(spotSettlementRecord.add(sard.getTotalSettlement()));
                    stockAnalysisRecord.setStockAnalysisId(stockAnalysis.getId());
                    stockAnalysisRecord.setStockName(stockAnalysis.getStockName());
                    // 优先插入上证，如果策略中没有上证的就插入深证的大盘涨跌
                    if (shBtStatus != null) {
                        stockAnalysisRecord.setBroaderMarketStatus(shBtStatus);
                    } else {
                        stockAnalysisRecord.setBroaderMarketStatus(szBtStatus);
                    }
                    // stockAnalysisRecordService.save(stockAnalysisRecord);

                    StockAnalysisRecord sardNew=new StockAnalysisRecord();
                    BeanUtils.copyProperties(sard,sardNew);
                    sardNew.setId(sard.getId());
                    sardNew.setStatus(StockStatusEnum.UNWIND.getValue());
                    // 修改状态为平仓
                    // stockAnalysisRecordService.saveAndFlush(sardNew);

                    // System.out.println("总结余："+stockAnalysisRecord.getTotalSettlement());
                }
                json.put("list",jsonArray);
                json.put("spotSettlement",spotSettlementRecord);
            }
            return json.toJSONString();
    }
}
