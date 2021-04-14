package com.dao.impl;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.entity.*;
import com.utils.DbUtils;

public class manageDaoImpl {
    public ArrayList<User> getSuperUser() {
        DbUtils obj = new DbUtils();
        Connection connection = DbUtils.getConnection();
        ArrayList<User> superUsers = new ArrayList<>();
        if (connection == null) {
            System.out.println("数据库连接出错");
            return null;
        }
        String sql = "SELECT * from master";
        try {
            //预编译处理sql语句
            PreparedStatement ptst = connection.prepareStatement(sql);
            ResultSet rs = ptst.executeQuery();
            // rs.last();
            //rs.beforeFirst();
            while (rs.next()) {
                User superUser = new User();
                superUser.setName(rs.getString("name"));
                superUser.setSex(rs.getString("sex"));
                superUser.setPeriod_number(rs.getInt("period_number"));
                superUser.setDirection(rs.getString("direction"));
                superUser.setStudent_number(rs.getString("student_number"));
                superUser.setTel(rs.getString("tel"));
                superUser.setMail(rs.getString("mail"));
                superUser.setPassword(rs.getString("password"));
                superUsers.add(superUser);
            }

            // obj.closeConn(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL语句有误，数据库报错");
            return null;
        }
        return superUsers;
    }

    public ArrayList<table> getTable() {
        DbUtils obj = new DbUtils();
        Connection connection = DbUtils.getConnection();
        ArrayList<table> tables = new ArrayList<>();
        if (connection == null) {
            System.out.println("数据库连接出错");
            return null;
        }
        String sql = "SELECT * from examine where pass=0";
        try {
            //预编译处理sql语句
            PreparedStatement ptst = connection.prepareStatement(sql);
            ResultSet rs = ptst.executeQuery();
            // rs.last();
            //   rs.beforeFirst();
            while (rs.next()) {
                table table = new table();
                table.setID(rs.getInt("ID"));
                table.setArticles(rs.getString("articles"));
                table.setName(rs.getString("name"));
                table.setTypes(rs.getString("types"));
                table.setTime(rs.getDate("time"));
                table.setTel(rs.getLong("tel"));
                table.setNumber(rs.getLong("number"));
                table.setAllfun(rs.getString("allfun"));
                table.setPass(rs.getString("pass"));
                table.setExamine_time(rs.getDate("examine_time"));
                tables.add(table);
            }
          //  System.out.println(tables);
            //  obj.closeConn(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL语句有误，数据库报错");
            return null;
        }
        return tables;
    }

    public ArrayList<table> getSlovedTable() {
        DbUtils obj = new DbUtils();
        Connection connection = DbUtils.getConnection();
        ArrayList<table> tables = new ArrayList<>();
        if (connection == null) {
            System.out.println("数据库连接出错");
            return null;
        }
        String sql = "SELECT * from examine where pass=1 or pass=2";
        try {
            //预编译处理sql语句
            PreparedStatement ptst = connection.prepareStatement(sql);
            ResultSet rs = ptst.executeQuery();
            // rs.last();
            //   rs.beforeFirst();
            while (rs.next()) {
                table table = new table();
                table.setID(rs.getInt("ID"));
                table.setArticles(rs.getString("articles"));
                table.setName(rs.getString("name"));
                table.setTypes(rs.getString("types"));
                table.setTime(rs.getDate("time"));
                table.setTel(rs.getLong("tel"));
                table.setNumber(rs.getLong("number"));
                table.setAllfun(rs.getString("allfun"));
                table.setPass(rs.getString("pass"));
                table.setExamine_time(rs.getDate("examine_time"));
                tables.add(table);
            }
            //  System.out.println(tables);
            //  obj.closeConn(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL语句有误，数据库报错");
            return null;
        }
        return tables;
    }

    public ArrayList<table> getAllTable() {
        DbUtils obj = new DbUtils();
        Connection connection = DbUtils.getConnection();
        ArrayList<table> tables = new ArrayList<>();
        if (connection == null) {
            System.out.println("数据库连接出错");
            return null;
        }
        String sql = "SELECT * from examine";
        try {
            //预编译处理sql语句
            PreparedStatement ptst = connection.prepareStatement(sql);
            ResultSet rs = ptst.executeQuery();
            // rs.last();
            //   rs.beforeFirst();
            while (rs.next()) {
                table table = new table();
                table.setID(rs.getInt("ID"));
                table.setArticles(rs.getString("articles"));
                table.setName(rs.getString("name"));
                table.setTypes(rs.getString("types"));
                table.setTime(rs.getDate("time"));
                table.setTel(rs.getLong("tel"));
                table.setNumber(rs.getLong("number"));
                table.setAllfun(rs.getString("allfun"));
                table.setPass(rs.getString("pass"));
                table.setExamine_time(rs.getDate("examine_time"));
                tables.add(table);
            }
            //  System.out.println(tables);
            //  obj.closeConn(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL语句有误，数据库报错");
            return null;
        }
        return tables;
    }

    public ArrayList<table> getOneTable(int a) {
        DbUtils obj = new DbUtils();
        Connection connection = DbUtils.getConnection();
        ArrayList<table> tables = new ArrayList<>();
        if (connection == null) {
            System.out.println("数据库连接出错");
            return null;
        }
        String sql = "SELECT * from examine where ";
        try {
            //预编译处理sql语句
            PreparedStatement ptst = connection.prepareStatement("SELECT * from examine where ID=?");
            ptst.setInt(1, a);
            ResultSet rs = ptst.executeQuery();
            // rs.last();
            //   rs.beforeFirst();
            while (rs.next()) {
                table table = new table();
                table.setID(rs.getInt("ID"));
                table.setArticles(rs.getString("articles"));
                table.setName(rs.getString("name"));
                table.setTypes(rs.getString("types"));
                table.setTime(rs.getDate("time"));
                table.setTel(rs.getLong("tel"));
                table.setNumber(rs.getLong("number"));
                table.setAllfun(rs.getString("allfun"));
                table.setPass(rs.getString("pass"));
                table.setExamine_time(rs.getDate("examine_time"));
                tables.add(table);
            }
            //  System.out.println(tables);
            //  obj.closeConn(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL语句有误，数据库报错");
            return null;
        }
        return tables;
    }

    public void pass(int a) {
        DbUtils obj = new DbUtils();
        Connection connection = DbUtils.getConnection();
        if (connection == null) {
            System.out.println("数据库连接出错");

        }
        try {
            //预编译处理sql语句
            PreparedStatement ptst = connection.prepareStatement("UPDATE examine SET pass='1',examine_time=current_date WHERE  ID=?");
            ptst.setInt(1, a);
            ptst.execute();

            // obj.closeConn(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL语句有误，数据库报错");
        }
    }

    public void noPass(int a) {
        DbUtils obj = new DbUtils();
        Connection connection = DbUtils.getConnection();
        if (connection == null) {
            System.out.println("数据库连接出错");

        }
        try {
            //预编译处理sql语句
            PreparedStatement ptst = connection.prepareStatement("UPDATE examine SET pass='2',examine_time=current_date WHERE  ID=?");
            ptst.setInt(1, a);
            ptst.execute();
            //  obj.closeConn(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL语句有误，数据库报错");
        }
    }

    public int getPassNum() {
        Connection connection = DbUtils.getConnection();
        if (connection == null) {
            System.out.println("数据库连接出错");

        }
        int count = 0;
        try {
            //预编译处理sql语句
            PreparedStatement ptst = connection.prepareStatement("select count(*) from (SELECT * FROM examine where pass=1) as number1;");
            ResultSet rs = ptst.executeQuery();
            while (rs.next()){
                count = rs.getInt(1);
            }
            return count;
            // obj.closeConn(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL语句有误，数据库报错");
        }

        return count;
    }

    public int getNoPassNum() {
        Connection connection = DbUtils.getConnection();
        if (connection == null) {
            System.out.println("数据库连接出错");

        }
        int count = 0;
        try {
            //预编译处理sql语句
            PreparedStatement ptst = connection.prepareStatement("select count(*) from (SELECT * FROM examine where pass=2) as number2");
            ResultSet rs = ptst.executeQuery();

            while (rs.next()){
                count = rs.getInt(1);
            }
            return count;
            // obj.closeConn(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL语句有误，数据库报错");
        }

        return count;
    }

//获取已处理未处理数
    public  ArrayList<num> getNum(){
        Connection connection = DbUtils.getConnection();
        ArrayList<num> nums = new ArrayList<>();
        num num =new num();
        int a = this.getPassNum();

        int b = this.getNoPassNum();

        int c= a + b;

        num.setSolvedNum(c);

        if (connection == null) {
            System.out.println("数据库连接出错");

        }
        int count = 0;
        try {
            //预编译处理sql语句
            PreparedStatement ptst = connection.prepareStatement("select count(*) from (SELECT * FROM examine where pass=0) as number3");
            ResultSet rs = ptst.executeQuery();

            while (rs.next()){
                count = rs.getInt(1);
            }
            num.setPendingNum(count);
            // obj.closeConn(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL语句有误，数据库报错");
        }
        nums.add(num);
        return nums;
    }



}