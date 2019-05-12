package com.zoy.concurrent.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author : owen
 * @email zhaoouyang163@gmail.com
 * @date : 2019/5/11
 */
@Controller
@Slf4j
public class TestController {

    int i = 0;

    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        System.out.println(i++);
        return "test";
    }

}
