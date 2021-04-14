package com.servlet.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.utils.DbUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

@WebServlet( value = "/us")
public class IncreaseBooksController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);


    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;


        String result = this.getParam(req, resp);

        JSONObject jsonObject = JSONObject.parseObject(result);

        Integer bianhao = jsonObject.getInteger("ID");
        String mingcheng = jsonObject.getString("name");
        String author = jsonObject.getString("author");
        String shujileibie = jsonObject.getString("leibie");
        String chubanriqi = jsonObject.getString("chubanriqi");
        String jiage = jsonObject.getString("jiage");
        String goururiqi = jsonObject.getString("goururiqi");
        String gourushuliang= jsonObject.getString("gourushuliang");
        String cunfangdidain= jsonObject.getString("cunfangdidian");
        String fuzeren= jsonObject.getString("fuzeren");
        String suoshubumen = jsonObject.getString("suoshubumen");
        String jieshao = jsonObject.getString("introduce");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        try {
            connection = DbUtils.getConnection();
            String sql ="INSERT INTO allbooks(article,ID,name,author,variety,time,price,buy_time,number,place,manager,department,introduce) VALUE ('图书',?,?,?,?,?,?,?,?,?,?,?,?)";
            statement = connection.prepareStatement(sql);

            statement.setInt(1,bianhao);
            statement.setString(2,mingcheng);
            statement.setString(3,author);
            statement.setString(4,shujileibie);
            statement.setString(5,chubanriqi);
            statement.setString(6,jiage);
            statement.setString(7,goururiqi);
            statement.setString(8,gourushuliang);
            statement.setString(9,cunfangdidain);
            statement.setString(10,fuzeren);
            statement.setString(11,suoshubumen);
            statement.setString(12,jieshao);


            int i = statement.executeUpdate();
            PrintWriter printWriter = resp.getWriter();

            if(i>0){
                System.out.println("提交成功");
                String s = JSON.toJSONString(true);
                printWriter.write(s);
            }
            else {
                System.out.println("提交失败");
                String s = JSON.toJSONString(false);
                printWriter.write(s);

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DbUtils.closeAll(connection,statement,resultSet);
        }
    }



    public String getParam(HttpServletRequest req, HttpServletResponse resp) {
        String result = "";
        try {
            InputStream input = req.getInputStream();

            byte [] b = new byte[1024];
            int len = 0;
            while((len = input.read(b)) != -1) {
                result = new String(b, "utf-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
