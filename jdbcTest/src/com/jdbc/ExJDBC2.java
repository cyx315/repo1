package com.jdbc;

import com.entity.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExJDBC2 {
    public static void main(String[] args) {
       /* List<Student> stuList = findByName("三");
        stuList.forEach(System.out::println);*/

        Student stu = new Student("s011","洪七公",50,"男");
        //addStu(stu);
        //updateStu(stu);
        //deleteStu(stu);
        List<Student> allList = findAll();
        allList.forEach(System.out::println);


    }

    public static List<Student> findByName(String sname){
        List<Student> stuList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");//加载驱动
            //获取连接
            String url = "jdbc:mysql://localhost:3306/test2?serverTimezone=Asia/Shanghai";
            connection = DriverManager.getConnection(url, "root", "root");
            String sql = "select sno,sname,sage,ssex from student where sname like ?";
            pstat = connection.prepareStatement(sql);
            String name = "%"+sname+"%";
            pstat.setString(1,name);
            rs = pstat.executeQuery();

            while (rs.next()){
                Student stu = new Student();
                stu.setSno(rs.getString(1));
                stu.setSname(rs.getString(2));
                stu.setSage(rs.getInt(3));
                stu.setSsex(rs.getString(4));
                stuList.add(stu);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            if (null != rs){
                try {
                    rs.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (null != pstat){
                try {
                    pstat.close();
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

        return stuList;
    }


    public static void addStu(Student stu){
        Connection conn = null;
        PreparedStatement pstat = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/test2?serverTimezone=Asia/Shanghai";
            conn = DriverManager.getConnection(url, "root", "root");
            String sql = "insert into student(sno,sname,sage,ssex) values(?,?,?,?)";
            pstat = conn.prepareStatement(sql);

            pstat.setString(1,stu.getSno());
            pstat.setString(2,stu.getSname());
            pstat.setInt(3,stu.getSage());
            pstat.setString(4,stu.getSsex());

            int rowNum = pstat.executeUpdate();
            System.out.println("影响的行数；"+rowNum);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            if (null != pstat){
                try {
                    pstat.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

            if (null != conn){
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    public static void updateStu(Student stu){
        Connection conn = null;
        PreparedStatement pstat = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/test2?serverTimezone=Asia/Shanghai";
            conn = DriverManager.getConnection(url, "root", "root");
            String sql = "update student set sname = ?,sage = ?,ssex = ? where sno = ?";
            pstat = conn.prepareStatement(sql);

            pstat.setString(1,stu.getSname());
            pstat.setInt(2,stu.getSage());
            pstat.setString(3,stu.getSsex());
            pstat.setString(4,stu.getSno());

            int rowNum = pstat.executeUpdate();
            System.out.println("影响的行数："+rowNum);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            if (null != pstat){
                try {
                    pstat.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

            if (null != conn){
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

    }

    public static void deleteStu(Student stu){
        Connection conn = null;
        PreparedStatement pstat = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/test2?serverTimezone=Asia/Shanghai";

            conn = DriverManager.getConnection(url, "root", "root");
            String sql = "delete from student where sno = ?";
            pstat = conn.prepareStatement(sql);

            pstat.setString(1,stu.getSno());
            int rowNum = pstat.executeUpdate();
            System.out.println("影响的行数："+rowNum);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            if (null != pstat){
                try {
                    pstat.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

            if (null != conn){
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    public static List<Student> findAll(){
        List<Student> stuList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");//加载驱动
            //获取连接
            String url = "jdbc:mysql://localhost:3306/test2?serverTimezone=Asia/Shanghai";
            connection = DriverManager.getConnection(url, "root", "root");
            String sql = "select sno,sname,sage,ssex from student ";
            pstat = connection.prepareStatement(sql);


            rs = pstat.executeQuery();

            while (rs.next()){
                Student stu = new Student();
                stu.setSno(rs.getString(1));
                stu.setSname(rs.getString(2));
                stu.setSage(rs.getInt(3));
                stu.setSsex(rs.getString(4));
                stuList.add(stu);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            if (null != rs){
                try {
                    rs.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (null != pstat){
                try {
                    pstat.close();
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

        return stuList;
    }
}
