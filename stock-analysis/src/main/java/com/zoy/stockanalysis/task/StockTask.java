package com.zoy.stockanalysis.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author : owen
 * @email zhaoouyang163@gmail.com
 * @date : 2019/4/7
 */
@Component
public class StockTask {

    @Scheduled(cron = "0 0 10 10 * ? ")
    public void stockTask(){
        System.out.println("开始卖出股票...");
        // 查询所有需要卖出的股票

        // 查询当前股票价格

        // 计算盈亏

        // 写入股票策略记录

        // 修改所有需要卖出的股票状态为已卖出
    }

}
