package com.zoy.case02mybatisjpa.dao;

import com.zoy.mybatisjpa.Case02MybatisJpaApplication;
import com.zoy.mybatisjpa.dao.BlogMapper;
import com.zoy.mybatisjpa.entity.Blog;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 * @author : owen
 * @email zhaoouyang163@gmail.com
 * @date : 2019/4/18
 */
@Slf4j
@ConditionalOnClass
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Case02MybatisJpaApplication.class)
public class BlogMapperTest {

    @Autowired
    private BlogMapper blogMapper;

    @Test
    public void test() throws IOException {
        System.out.println(1);
    }

    @Test
    public void testXmlLoadSpringBoot() throws IOException {
        Blog blog=new Blog();
        System.out.println(blogMapper.selectBlog(blog));
        System.out.println(1);
    }

}