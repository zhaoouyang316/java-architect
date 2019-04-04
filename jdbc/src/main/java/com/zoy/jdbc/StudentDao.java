package com.zoy.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author : owen
 * @email zhaoouyang163@gmail.com
 * @date : 2019/4/4
 */
public class StudentDao {

    /**
     * 新增
     * @param student
     * @return
     */
    public static int insert(Student student){
        Connection conn=JdbcUtilsFactory.getInstance().getConn();
        int i=0;
        String sql="insert into jdbc_student (name,sex,age) values (?,?,?)";
        PreparedStatement pstmt=null;
        try {
            pstmt=(PreparedStatement)conn.prepareStatement(sql);
            pstmt.setString(1,student.getName());
            pstmt.setInt(2,student.getSex());
            pstmt.setInt(3,student.getAge());
            i=pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            JdbcUtilsFactory.getInstance().closeConn(conn,pstmt);
        }
        return i;
    }

    /**
     * 修改
     * @param student
     * @return
     */
    public static int update(Student student){
        Connection conn=JdbcUtilsFactory.getInstance().getConn();
        int i=0;
        String sql="update jdbc_student set age='" + student.getAge() + "' where name='" + student.getName() + "'";
        PreparedStatement pstmt=null;
        try {
            pstmt=(PreparedStatement)conn.prepareStatement(sql);
            i=pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            JdbcUtilsFactory.getInstance().closeConn(conn,pstmt);
        }
        return i;
    }


    /**
     * 删除
     * @param student
     * @return
     */
    public static int delete(Student student){
        Connection conn=JdbcUtilsFactory.getInstance().getConn();
        int i=0;
        String sql="delete from jdbc_student where name='"+student.getName()+"'";
        PreparedStatement pstmt=null;
        try {
            pstmt=(PreparedStatement)conn.prepareStatement(sql);
            i=pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            JdbcUtilsFactory.getInstance().closeConn(conn,pstmt);
        }
        return i;
    }

    /**
     * 查询
     * @return
     */
    public static Integer getCount(){
        Connection conn = JdbcUtilsFactory.getInstance().getConn();
        String sql = "select * from jdbc_student";
        int col=0;
        PreparedStatement pstmt=null;
        try {
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            col = rs.getMetaData().getColumnCount();
            System.out.println("============================");
            while (rs.next()) {
                for (int i = 1; i <= col; i++) {
                    System.out.print(rs.getString(i) + "\t");
                    if ((i == 2) && (rs.getString(i).length() < 8)) {
                        System.out.print("\t");
                    }
                }
                System.out.println("");
            }
            System.out.println("============================");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
          JdbcUtilsFactory.getInstance().closeConn(conn,pstmt);
        }
        return col;
    }

}
