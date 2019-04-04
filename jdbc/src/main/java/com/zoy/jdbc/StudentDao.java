package com.zoy.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author : owen
 * @email zhaoouyang163@gmail.com
 * @date : 2019/4/4
 */
public class StudentDao {

    public static int insert(Student student){
        Connection conn=JdbcUtilsFactory.getInstance().getConn();
        int i=0;
        String sql="insert into jdbc_student (name,sex,age) values (?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,student.getName());
            pstmt.setInt(2,student.getSex());
            pstmt.setInt(3,student.getAge());
            i=pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

}
