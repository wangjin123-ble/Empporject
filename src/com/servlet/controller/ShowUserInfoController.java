package com.servlet.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dao.UserDao;
import com.dao.impl.UserDaoImpl;
import com.entity.User;
import com.utils.GetLoginUser;
import com.utils.JedisUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@WebServlet(value = "/showinf")
public class ShowUserInfoController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");
        UserDao userDao = new UserDaoImpl();
//        User user = GetLoginUser.getLoginUser(req,resp);
        String param = getParam(req);
        JSONObject jsonObject = JSONObject.parseObject(param);
        String token = jsonObject.getString("token");
        String loginUserJson = JedisUtil.getJedis().get(token);
        JSONObject jsonObject1 = JSONObject.parseObject(loginUserJson);
        String mail = jsonObject1.getString("mail");
        User user = userDao.select_by_mail(mail);
        String s = JSON.toJSONString(user);
        PrintWriter printWriter = resp.getWriter();
        printWriter.println(s);
    }

    public static String getParam(HttpServletRequest req) {
        try {
            InputStream input = req.getInputStream();
            String result = "";
            byte[] b = new byte[1024];
            while (input.read(b) != -1) {
                result = new String(b, StandardCharsets.UTF_8);
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
