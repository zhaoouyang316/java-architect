package com.zoy.stockanalysis.controller;

import com.alibaba.fastjson.JSONObject;
import com.zoy.common.enums.StatusEnum;
import com.zoy.stockanalysis.entity.StockAnalysis;
import com.zoy.stockanalysis.service.ItemStockService;
import com.zoy.stockanalysis.service.StockAnalysisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author : owen
 * @email zhaoouyang163@gmail.com
 * @date : 2019/4/5
 */
@RestController
@Slf4j
@RequestMapping("/stock")
public class IndexController {

    @Autowired
    private ItemStockService itemStockService;
    @Autowired
    private StockAnalysisService stockAnalysisService;

    /**
     * 买入股票
     * @param stockCode
     * @param stockAnalysisId
     * @param positionNumber
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/buy")
    public String buyStock(String stockCode,Long stockAnalysisId,Long positionNumber) throws Exception {
        JSONObject json=new JSONObject();

        String[] strCodeArr=stockCode.split(",");
        Integer flagIndex=0;
        String errorCode="";
        for(String str:strCodeArr){
            Thread.sleep(200);
            if(itemStockService.buyStock(str,stockAnalysisId,positionNumber)){
                flagIndex+=1;
            }else{
                errorCode+=str+",";
            }
        }
        json.put("okNum",flagIndex);
        return json.toJSONString();
    }

    /**
     * 卖出全部股票
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/sellAll")
    public String sellAllStock() throws Exception {
        itemStockService.sellStock();
        return "ok";
    }


    /**
     * 新增策略
     * @param stockName
     * @param sellTime
     * @param buyTime
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/addAnalysis")
    public String addAnalysis(String stockName,String sellTime,String buyTime) throws Exception {
        StockAnalysis stockAnalysis=new StockAnalysis();
        stockAnalysis.setStockName(stockName);
        stockAnalysis.setBuyTime(buyTime);
        stockAnalysis.setStatus(StatusEnum.ACTIVE.getValue());
        stockAnalysis.setSellTime(sellTime);
        stockAnalysis.setCreateTime(new Date());
        StockAnalysis ss=stockAnalysisService.save(stockAnalysis);
        return JSONObject.toJSONString(ss);
    }

    /**
     * 查看持仓
     * @param stockAnalysisId
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/searchStock")
    public String searchStock(Long stockAnalysisId) throws Exception {
        //169039296274104320L
        return itemStockService.searchStock(stockAnalysisId);
    }


    /**
     * 查看策略
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/searchAnalysis")
    public String searchAnalysis() throws Exception {
        //169039296274104320L
        return JSONObject.toJSONString(stockAnalysisService.findAll());
    }

}
