package com.servlet.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.entity.Book;
import com.service.BookService;
import com.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet(value = "/sb")
public class SearchBookController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");
        String jsonstring = this.getParam(req);
        JSONObject jsonObject = JSONObject.parseObject(jsonstring);
        String bookname = jsonObject.getString("bookname");
        BookService bookService = new BookServiceImpl();
        Book books = bookService.search_by_name(bookname);
        String string = JSON.toJSONString(books);
        resp.getWriter().write(string);
    }

    public String getParam(HttpServletRequest req) {
        try {
            InputStream input = req.getInputStream();
            String result = "";
            byte[] b = new byte[1024];
            while (input.read(b) != -1) {
                result = new String(b, StandardCharsets.UTF_8);
            }
            System.out.println(result);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
