package com.zoy.stockanalysis.controller;

import com.alibaba.fastjson.JSON;
import com.zoy.stockanalysis.entity.StockAnalysis;
import com.zoy.stockanalysis.service.StockAnalysisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : owen
 * @email zhaoouyang163@gmail.com
 * @date : 2019/4/5
 */
@RestController
@Slf4j
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private StockAnalysisService stockAnalysisService;

    @GetMapping(value = "/search")
    public String search(){
        System.out.println(1);
        return "1";
    }

}
