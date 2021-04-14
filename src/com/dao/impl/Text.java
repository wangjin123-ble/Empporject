package com.dao.impl;


import com.entity.AllUser;
import com.utils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Text {
    AllUser allUser = new AllUser();

    public void returnrecord(int a,String b){
        System.out.println("确定是否归还");
         //0为取消，1为确定归还

        if(a!=0){
            Connection connection = DbUtils.getConnection();
            try{
                String sql = "UPDATE borrowrecord SET status=1,return_time=current_date WHERE apply_book=?";  //SQL语句
                PreparedStatement statement = connection.prepareStatement(sql);
                //PreparedStatement statement = connection.prepareStatement("UPDATE borrowrecord SET status=1,return_time=current_date WHERE apply_book=?");
                System.out.println("请输入需要归还的书名");


                statement.setObject(1, b);

                int i = statement.executeUpdate();  //执行次数
                if (i > 0) {
                    System.out.println("更新成功！！！！");
                }
                else{
                    System.out.println("归还失败，请查看是否已经归还");

                }

            }catch (Exception throwables) {
                throwables.printStackTrace();
            }
        }
        else {
            Connection connection = DbUtils.getConnection();
            try {
                String sql = "UPDATE borrowrecord SET status=0,return_time=current_date WHERE apply_book=?";  //SQL语句
                PreparedStatement statement = connection.prepareStatement(sql);
                //PreparedStatement statement = connection.prepareStatement("UPDATE borrowrecord SET status=1,return_time=current_date WHERE apply_book=?");
                System.out.println("请输入需要归还的书名");

                int i = statement.executeUpdate();  //执行次数
                if (i > 0) {
                    System.out.println("更新成功！！！！");
                }
                else{
                    System.out.println("归还失败，请查看是否已经归还");

                }
                statement.setObject(1, b);
            }catch (Exception throwables) {
                throwables.printStackTrace();
            }

            System.out.println("成功取消！");
        }

    }

    public void  record1() {
        Connection connection = DbUtils.getConnection();
        try {
            String sql = "SELECT * FROM borrowrecord  ";  //SQL语句，返回结果集
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                System.out.println("ID" + " " + rs.getObject("ID"));
                System.out.println("apply_name" + " " + rs.getObject("apply_name"));
                System.out.println("apply_time" + " " + rs.getObject("apply_time"));
                System.out.println("apply_book" + " " + rs.getObject("apply_book"));
                System.out.println("return_time" + " " + rs.getObject("return_time"));
                System.out.println("deadline" + " " + rs.getObject("deadline"));
                System.out.println("status" + " " + rs.getObject("status"));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void examine(int a,String b,String c,long d){

        Connection connection = DbUtils.getConnection();
        try{

            String sql = "INSERT INTO examine (ID,`articles`,`name`,`types`,`tel`,`time`,`pass`) VALUES (?,'gaoshu',?,'归还','131111',current_date,'0')";  //SQL语句
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setObject(1, a);
            statement.setObject(2, b);


            //statement.execute();

            int i = statement.executeUpdate();  //执行次数
            if (i > 0) {
                System.out.println("更新成功！！！！");
            }
            else{
                System.out.println("申请失败");
            }

        }catch (Exception throwables) {
            throwables.printStackTrace();
        }

    }

    public void record (int a, String b, String c, Date d){
        Connection connection = DbUtils.getConnection();
        try{
            String sql = "INSERT INTO recordw (ID,apply_book,author,apply_time,deadline) VALUES (?,?,?,current_date,?)";  //SQL语句
            PreparedStatement statement = connection.prepareStatement(sql);
            //PreparedStatement statement = connection.prepareStatement("UPDATE borrowrecord SET status=1,return_time=current_date WHERE apply_book=?");

            statement.setObject(1,a);
            statement.setObject(2,b);
            statement.setObject(3,c);
            statement.setObject(4,d);

            int i = statement.executeUpdate();  //执行次数
            if (i > 0) {
                System.out.println("更新成功！！！！");
            }
            else{
                System.out.println("归还失败，请查看是否已经归还");

            }

        }catch (Exception throwables) {
            throwables.printStackTrace();
        }

    }

    public ArrayList recordselect ()throws SQLException{
        ArrayList users1 =new ArrayList();
        Connection connection = DbUtils.getConnection();

        String sql = "SELECT * from recordw";  //SQL语句
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        QueryRunner queryRuner =new QueryRunner();
        users1=(ArrayList)queryRuner.query(connection,sql,new MapListHandler());
        return users1;
    }

    public ArrayList examineselect (int a){
        ArrayList users =new ArrayList();
        Connection connection = DbUtils.getConnection();
        try{
            String sql = "SELECT * from examine where ID=?";    //SQL语句
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setObject(1, a);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                allUser.setID(rs.getInt("ID"));
                allUser.setArticles(rs.getString("articles"));
                allUser.setName(rs.getString("name"));
                allUser.setTypes(rs.getString("types"));
                allUser.setTel(rs.getLong("tel"));
                allUser.setTime(rs.getDate("time"));
                allUser.setPass(rs.getString("pass"));
                users.add(allUser);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("SQL语句有误，数据库报错");
            return null;
        }
        return users;
    }
}
