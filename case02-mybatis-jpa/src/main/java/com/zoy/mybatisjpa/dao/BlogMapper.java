package com.zoy.mybatisjpa.dao;

import com.zoy.mybatisjpa.entity.Blog;
import org.springframework.context.annotation.Lazy;

import javax.annotation.Resource;
import java.util.List;


public interface BlogMapper {

    List<Blog> selectBlog(Blog blog);

    void insertBlog(Blog blog);

}