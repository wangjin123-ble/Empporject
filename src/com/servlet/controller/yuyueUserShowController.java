package com.servlet.controller;

import com.alibaba.fastjson.JSON;
import com.utils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@WebServlet(value = "/yuyueUserShowController")
//预约界面图书展示
public class yuyueUserShowController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");
        Connection connection =null;
        connection = DbUtils.getConnection();
        QueryRunner qr = new QueryRunner();

        String query = "Select * from allbooks ";
        List results = null;
        try {
            results = (List) qr.query(connection, query, new MapListHandler());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        PrintWriter printWriter = resp.getWriter();


        String s = JSON.toJSONString(results);
        printWriter.write(s);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
