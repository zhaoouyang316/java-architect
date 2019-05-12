package com.zoy.designpatterns.singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 线程安全类，Jdbc工厂
 *
 * 高并发下用枚举+静态内部类的方式实现单例
 * 1 编译器会创建一个final 类型的类继承 Enum，而且属性和方法都是 static类型的，Java类的加载和初始化过程都是线程安全的。
 * 2 枚举自己处理序列化
 *
 * @author : owen
 * @email zhaoouyang163@gmail.com
 * @date : 2019/4/4
 */
public class JdbcUtilsFactory {

    /**
     * jdbc工具类
     */
    private enum JdbcUtilsEnum{

        SINGLETON_JDBCUTILS_FACTORY;

        private JdbcUtils instance;

        // 枚举类的构造方法在类加载时就会被实例化
        private JdbcUtilsEnum(){
            instance=new JdbcUtils();
        }

        public JdbcUtils getInstance(){
            return instance;
        }
    }

    public static JdbcUtils getInstance(){
        return JdbcUtilsEnum.SINGLETON_JDBCUTILS_FACTORY.getInstance();
    }

}

/**
 * Jdbc工具类
 */
class JdbcUtils{

    public JdbcUtils(){}

    /**
     * 获取数据库连接
     * @return
     */
    public Connection getConn(){
        String driver= "com.mysql.cj.jdbc.Driver";
        String url= "jdbc:mysql://192.168.1.110:3306/java-architect?useUnicode=true&characterEncoding=UTF-8";
        String username= "root";
        String password= "123456";
        Connection conn= null;
        try {
            // 加载驱动
            Class.forName(driver);
            conn=(Connection) DriverManager.getConnection(url,username,password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 关闭连接
     * @param conn
     * @param pstmt
     */
    public void closeConn(Connection conn, PreparedStatement pstmt){
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(pstmt!=null){
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
