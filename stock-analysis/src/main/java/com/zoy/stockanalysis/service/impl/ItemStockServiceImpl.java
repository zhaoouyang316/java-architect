package com.zoy.stockanalysis.service.impl;

import com.zoy.stockanalysis.entity.BigMarket;
import com.zoy.stockanalysis.entity.StockPriceRecord;
import com.zoy.stockanalysis.service.BigMarketService;
import com.zoy.stockanalysis.service.ItemStockService;
import com.zoy.stockanalysis.service.StockPriceRecordService;
import com.zoy.utils.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean buyStock(String stockCode,Long stockAnalysisId) throws Exception {
        // 标记是否请求成功
        boolean flag=false;
        //获取网络请求工具类实例
        HttpUtils httpUtils = HttpUtils.getInstance();
        Response res1=httpUtils.getDataSynFromNet("http://hq.sinajs.cn/list=s_sh000001");
        // 大盘数据持久化
        if(res1.isSuccessful()){
                String body1=res1.body().string();
                log.info("bigMarket callback {}",body1);
                Response res2=httpUtils.getDataSynFromNet("http://hq.sinajs.cn/list=sh"+stockCode);
                if(res2.isSuccessful()){
                    String body2=res2.body().string();
                    // 如果不是上证就查深证
                    String ckNull=body2.split("\"")[1];
                    if(ckNull==null||ckNull.equals("")){
                        res2=httpUtils.getDataSynFromNet("http://hq.sinajs.cn/list=sz"+stockCode);
                        body2=res2.body().string();
                    }
                    BigMarket bigMarket=bigMarketService.saveByArray(body1);
                    if(bigMarket!=null){
                        // 拼接大盘编号
                        log.info("stockPriceRecord callback {}",body2);
                        // 将价格入库
                        Integer broaderMarketStatus=0;
                        if(bigMarket.getVolatilityPrice().compareTo(new BigDecimal(0L))==1){
                            broaderMarketStatus=1;
                        }
                        StockPriceRecord stockPriceRecord=tockPriceRecordService.saveByArray(body2,bigMarket.getId(),broaderMarketStatus,stockAnalysisId);
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

    @Override
    public void sellStock() {

    }
}
