package com.servlet.controller;

import com.alibaba.fastjson.JSONObject;
import com.utils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(value = "/yuyueShowController")
//预约后向管理员展示
public class yuyueShowController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection conn = null;
        conn = DbUtils.getConnection();


        String result = this.getParam(req,resp);
        JSONObject jsonObject = JSONObject.parseObject(result);

        String ID = jsonObject.getString("ID");

        QueryRunner qr = new QueryRunner();
        String query = "select * from allbooks where ID = ' "+ ID +"'";

        try {
            List results = (List) qr.query(conn, query, new MapListHandler()); //使用QueryRunner类中的query方法把查询结果封装在List中，单条记录存在map中
        } catch (SQLException throwables) {
            throwables.printStackTrace();
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
