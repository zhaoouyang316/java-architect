package com.zoy.mybatisjpa.dao;

import com.zoy.mybatisjpa.entity.Blog;

import java.util.List;

public interface BlogMapper {

    List<Blog> selectBlog(Blog blog);

}