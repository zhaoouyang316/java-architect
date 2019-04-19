package com.zoy.stockanalysis.service.impl;

import com.zoy.stockanalysis.StockAnalysisApplication;
import com.zoy.stockanalysis.service.ItemStockService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author : owen
 * @email zhaoouyang163@gmail.com
 * @date : 2019/4/19
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StockAnalysisApplication.class)
public class StockAnalysisRecordServiceImplTest {

    @Autowired
    private ItemStockService itemStockService;

    /**
     * 删除
     */
    @Test
    public void deleteByStockAnalysisId() throws Exception {
        itemStockService.deleteByStockAnalysisId(168674780231237632L);
    }

}