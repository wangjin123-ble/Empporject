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
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
@WebServlet(name = "controller",value = "/controller")
public class controller extends HttpServlet {
//报修原因接受
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection connection = null;

        ResultSet resultSet = null;


        String result = this.getParam(req, resp);

        JSONObject jsonObject = JSONObject.parseObject(result);
        String baoxiuren = jsonObject.getString("name");
        int wupinbianhao = jsonObject.getInteger("number");
        String wupinmingcheng = jsonObject.getString("book");
        String baoxiuyuanyin = jsonObject.getString("reason");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        PreparedStatement statement=null;
        try {
            connection = DbUtils.getConnection();
            String sql ="INSERT INTO examine(articles,ID,name,types,time,pass,allfun) VALUE (?,?,?,?,current_date ,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,wupinmingcheng);
            preparedStatement.setInt(2,wupinbianhao);
            preparedStatement.setString(3,baoxiuren);
            preparedStatement.setString(4,"维修" );

            preparedStatement.setString(5,"0");
            preparedStatement.setString(6,baoxiuyuanyin);
           // preparedStatement.setString(7,"587");
            int i = preparedStatement.executeUpdate();

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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
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
