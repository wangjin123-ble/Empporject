package com.servlet.controller;

import com.alibaba.fastjson.JSON;
import com.dao.BooksDao;
import com.dao.impl.BooksDaoImpl;
import com.entity.Allbooks;
import com.entity.Book;
import com.entity.Change;
import com.json.JsonChange2;
import com.service.BooksService;
import com.service.impl.BooksServiceImpl;
import com.utils.JedisUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet( value =("/zs"))

public class SearchAllbooksController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        BooksDao booksDao=new BooksDaoImpl();
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
//        out.print(body);
        JsonChange2 jsonChange = new JsonChange2();
        //将StringBuilder类型的对象的对象通过toString方法转化为String类型，然后用fastjson的json包进行转化
        Change change = jsonChange.JsonChange(body.toString());



        String str1 = String.valueOf(change.getName());
        BooksService booksService=new BooksServiceImpl();
        JedisUtil.getJedis().set("name2",str1);
        booksService.searchBooks(str1);
        List<Allbooks> books = booksService.searchBooks(str1);
        String string = JSON.toJSONString(books);
        resp.getWriter().write(string);
        req.setAttribute("books", books);





    }
}
