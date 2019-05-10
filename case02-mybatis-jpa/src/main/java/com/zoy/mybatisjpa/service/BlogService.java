package com.zoy.mybatisjpa.service;

import com.zoy.mybatisjpa.entity.Blog;

import java.util.List;

/**
 * @author : owen
 * @email zhaoouyang163@gmail.com
 * @date : 2019/4/19
 */
public interface BlogService {

    List<Blog> selectBlog(Blog blog);

    void insertBlog(Blog blog);
}
