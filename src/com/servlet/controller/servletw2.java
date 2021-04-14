package com.servlet.controller;


import com.json.ChangeJson;
import com.entity.AllUser;
import com.dao.impl.Text;
import com.json.JsonChange1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

@WebServlet(value = "/wrs")
public class servletw2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        Text text =new Text();
        ChangeJson changeJson = new ChangeJson();

        PrintWriter out=resp.getWriter();


        InputStreamReader insr = new InputStreamReader(req.getInputStream(), StandardCharsets.UTF_8);
        //System.out.println(insr);
        StringBuilder body = new StringBuilder();
        int respInt = insr.read();
        while(respInt!=-1) { // 读取请求数据
            //将读取的字节流中的每一个字节转化为字符，然后添加到StringBuilder类型的对象中
            body.append((char) respInt);
            respInt = insr.read();
        }


        //out的print方法可以输出对象
        //  out.print(body);
        JsonChange1 jsonChange = new JsonChange1();
        //将StringBuilder类型的对象的对象通过toString方法转化为String类型，然后用fastjson的json包进行转化
        AllUser allUser = jsonChange.JsonChange(body.toString());


        String str1 = null;
        try {
            str1 = changeJson.ChangeJson(text.recordselect());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        out.write(str1);

    }
}
