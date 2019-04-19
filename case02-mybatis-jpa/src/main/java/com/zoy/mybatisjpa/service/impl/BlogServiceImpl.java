package com.zoy.mybatisjpa.service.impl;

import com.zoy.mybatisjpa.dao.BlogMapper;
import com.zoy.mybatisjpa.entity.Blog;
import com.zoy.mybatisjpa.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author : owen
 * @email zhaoouyang163@gmail.com
 * @date : 2019/4/19
 */
public class BlogServiceImpl implements BlogService{

    @Autowired
    private BlogMapper blogMapper;

    @Override
    public List<Blog> selectBlog(Blog blog) {
        return blogMapper.selectBlog(blog);
    }
}
