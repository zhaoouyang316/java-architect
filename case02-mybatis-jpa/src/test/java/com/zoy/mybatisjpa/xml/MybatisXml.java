package com.zoy.mybatisjpa.xml;

import com.alibaba.fastjson.JSONObject;
import com.zoy.mybatisjpa.entity.Blog;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * @author : owen
 * @email zhaoouyang163@gmail.com
 * @date : 2019/4/19
 */
public class MybatisXml {

    /**
     * 通过xml配置mybatis,适用非web项目
     * @throws IOException
     */
    @Test
    public void test() throws IOException {

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        Properties properties=new Properties();
        properties.load(Resources.getResourceAsStream("jdbc.properties"));
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream,null,properties);

        SqlSession session = sqlSessionFactory.openSession();
        try {
            // 查询
            // Blog blog = (Blog) session.selectOne("com.zoy.mybatisjpa.dao.BlogMapper.selectBlog", 101);
            // System.out.println(JSONObject.toJSONString(blog));
            Blog blog=new Blog();
            blog.setName("小甜甜2");
            blog.setCreateTime(new Date());
            blog.setUpdateTime(new Date());
            session.insert("insertBlogByLastId",blog);
            session.commit();
        } finally {
            session.close();
        }

    }


    /**
     * 模糊查询
     * @throws IOException
     */
    @Test
    public void sarch() throws IOException {

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        Properties properties=new Properties();
        properties.load(Resources.getResourceAsStream("jdbc.properties"));
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream,null,properties);

        SqlSession session = sqlSessionFactory.openSession();
        try {
            // 查询
            // Blog blog = (Blog) session.selectOne("com.zoy.mybatisjpa.dao.BlogMapper.selectBlog", 101);
            // System.out.println(JSONObject.toJSONString(blog));
            Blog blog=new Blog();
            blog.setName("甜2");
            blog.setCreateTime(new Date());
            blog.setUpdateTime(new Date());
            List<Blog> list=session.selectList("selectBlog",blog);
            String str=JSONObject.toJSONString(list);
            System.out.println(str);
            session.commit();
        } finally {
            session.close();
        }

    }

}
