package com.servlet.controller;

import com.dao.impl.manageDaoImpl;
import com.json.ChangeJson;
import com.json.JsonChange;
import com.entity.table;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;

//通过驳回的实现
@WebServlet(value = "/rs1")

public class Rservlet extends HttpServlet{


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 响应参数格式设置
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        manageDaoImpl manageDaoImpl = new manageDaoImpl();
        //将此字符串对象输出
        PrintWriter out=resp.getWriter();
         /*使用InputStreamReader对象，获取前端传来的数据.其中
           request.getInputStream()是读取前端传递来的数据字节流，
           StandardCharsets.UTF_8是将前端传来的数据转化为UTF-8的编码方式*/
        InputStreamReader insr = new InputStreamReader(req.getInputStream(), StandardCharsets.UTF_8);
        StringBuilder body = new StringBuilder();
        int respInt = insr.read();
        while(respInt!=-1) { // 读取请求数据
            //将读取的字节流中的每一个字节转化为字符，然后添加到StringBuilder类型的对象中
            body.append((char) respInt);
            respInt = insr.read();
        }

        System.out.println(body);
        //out的print方法可以输出对象
        out.print(body);
        JsonChange jsonChange = new JsonChange();
        //将StringBuilder类型的对象的对象通过toString方法转化为String类型，然后用fastjson的json包进行转化
        table table=new table();
        table = jsonChange.JosnChangeJavaObject(body.toString());

//        out.write(body);

        String str1 = String.valueOf(table.getID());
        String str2 = table.getPass();
        String str3 = table.getNopass();



        if( str1 != null && str2.equals("通过") ){
            manageDaoImpl.pass(table.getID());
        }else if(str1 != null && str3.equals("驳回")){
            manageDaoImpl.noPass(table.getID());
        }

        System.out.println("提交的数据："+"编号："+str1+" "+"状态：" +str2+str3);


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
