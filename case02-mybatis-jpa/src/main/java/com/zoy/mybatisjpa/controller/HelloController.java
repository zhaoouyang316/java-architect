package com.zoy.mybatisjpa.controller;

import com.alibaba.fastjson.JSONObject;
import com.zoy.mybatisjpa.dao.BlogMapper;
import com.zoy.mybatisjpa.entity.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : owen
 * @email zhaoouyang163@gmail.com
 * @date : 2019/4/18
 */
@RestController
public class HelloController {

    @Autowired
    private BlogMapper blogMapper;

    @GetMapping("/hello")
    public String hello(){
        List<Blog> list=blogMapper.selectBlog(new Blog());
        return JSONObject.toJSONString(list);
    }

}
