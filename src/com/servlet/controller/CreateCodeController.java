package com.servlet.controller;

import cn.dsna.util.images.ValidateCode;
import com.alibaba.fastjson.JSON;
import com.utils.JedisUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(value = "/createCode")
public class CreateCodeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Jedis jedis = new Jedis("192.168.57.61", 6379);
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");
        ValidateCode code = new ValidateCode(200, 30, 4, 20);
        System.out.println(code.getCode());
        resp.getWriter().write(JSON.toJSONString(code.getCode()));
//        session.setAttribute("codes", code.getCode());
        JedisUtil.getJedis().set("codes",code.getCode());
//        code.write(resp.getOutputStream());

    }
}
