package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ExJDBC {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        try {
            //1.加载驱动程序
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2.准备连接条件
            String url = "jdbc:mysql://localhost:3306/test2?serverTimezone=Asia/Shanghai";
            String user = "root";
            String password = "root";
            //3.获取连接
            connection = DriverManager.getConnection(url, user, password);
            //4.获取执行SQL语句的执行器
            statement = connection.createStatement();
            String sno = "s002";
            String sname = "黄蓉";
            int sage = 20;
            String ssex = "女";
            //String sql= "INSERT INTO student values('"+sno+"','"+sname+"',"+sage+",'"+ssex+"')";
            //String sql = "update student set ssex='"+ssex+"' where sno='"+sno+"'";
            String sql = "insert into student values('s010','赵六',23,'男')";
            //5.执行SQL语句
            int rowNum = statement.executeUpdate(sql);
            System.out.println("影响的行数："+rowNum);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            //6.关闭资源
            if (null != statement){
                try {
                    statement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (null != connection){
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
}
