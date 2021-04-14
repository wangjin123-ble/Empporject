package com.servlet.controller;

import com.alibaba.fastjson.JSON;
import com.service.impl.searchServiceImpl;
import com.service.searchService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet(value = "/ShowAllSearchController")
public class ShowAllSearchController extends HttpServlet {
    // 管理员处理申请表 --> 存入数据库 --> 前端展示
    //维修页面展示
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        searchService searchService = new searchServiceImpl();


        List searches = null;

        try {
            searches = searchService.showAllSearch();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        PrintWriter printWriter = resp.getWriter();


        String s = JSON.toJSONString(searches);
        printWriter.write(s);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        doPost(req,resp);
    }





}
