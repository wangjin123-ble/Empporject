package com.servlet.controller;

import com.dao.impl.manageDaoImpl;
import com.json.ChangeJson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//输出已处理数未处理数
@WebServlet(value = "/ds")
public class Bservlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 响应参数格式设置
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        //向外传值,并将数据转化为json格式保存在一个字符串对象里
        manageDaoImpl manageDaoImpl = new manageDaoImpl();
        ChangeJson changeJson = new ChangeJson();
        String str6 = changeJson.ChangeJson(manageDaoImpl.getNum());
        PrintWriter out=resp.getWriter();
        out.write(str6);
    }
}
