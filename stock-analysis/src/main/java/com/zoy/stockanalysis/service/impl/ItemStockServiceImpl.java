package com.zoy.stockanalysis.service.impl;

import com.zoy.common.enums.BigMarketTypeEnum;
import com.zoy.common.enums.StockStatusEnum;
import com.zoy.stockanalysis.entity.BigMarket;
import com.zoy.stockanalysis.entity.StockPriceRecord;
import com.zoy.stockanalysis.service.BigMarketService;
import com.zoy.stockanalysis.service.ItemStockService;
import com.zoy.stockanalysis.service.StockPriceRecordService;
import com.zoy.utils.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

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
                                ,bigMarketTypeEnum, StockStatusEnum.POSITION,positionNumber,BigDecimal.valueOf(0));
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
    public void sellStock() {

    }
}
