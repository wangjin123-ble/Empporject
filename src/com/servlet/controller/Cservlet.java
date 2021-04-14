package com.servlet.controller;

import com.dao.impl.manageDaoImpl;
import com.entity.table;
import com.json.ChangeJson;
import com.json.JsonChange;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

//详情页的输出
@WebServlet(value = "/cs")
public class Cservlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int i =(int)req.getServletContext().getAttribute("ID");
        // 响应参数格式设置
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        manageDaoImpl manageDaoImpl = new manageDaoImpl();
        //将此字符串对象输出
        PrintWriter out=resp.getWriter();

        ChangeJson changeJson = new ChangeJson();
        String str6 = changeJson.ChangeJson(manageDaoImpl.getOneTable(i));
        out.write(str6);

    }
}
