package com.servlet.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dao.BooksDao;
import com.dao.impl.BooksDaoImpl;
import com.entity.Allbooks;
import com.service.BooksService;
import com.service.impl.BooksServiceImpl;
import com.utils.JedisUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet(value="/is")
public class SelectBooksController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }





    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");
        String jsonstring=this.getParam(req);
        JSONObject jsonObject = JSONObject.parseObject(jsonstring);
        String bookname = jsonObject.getString("bookname");
//        String name2= JedisUtil.getJedis().get("name2");
        BooksService booksService = new BooksServiceImpl();

        List<Allbooks> allbooks = booksService.showBooks(bookname);
//        List<Allbooks> allbooks1 = booksService.searchBooks(name2);
        String string = JSON.toJSONString(allbooks);
//        String string1 = JSON.toJSONString(allbooks1);
        resp.getWriter().write(string);
//        resp.getWriter().write(string1);

        req.setAttribute("allbooks",allbooks);
//        req.setAttribute("allbooks1",allbooks1);




    }

    private String getParam(HttpServletRequest req) {
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
