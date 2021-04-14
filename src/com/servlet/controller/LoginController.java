package com.servlet.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dao.UserDao;
import com.dao.impl.UserDaoImpl;
import com.entity.Token;
import com.entity.User;
import com.service.UserService;
import com.service.impl.UserServiceImpl;

import com.utils.JedisUtil;
import com.utils.Md5Util;

import redis.clients.jedis.Jedis;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(value = "/login")
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");
        UserService userService = new UserServiceImpl();
        PrintWriter printWriter = resp.getWriter();
        String json = this.getParam(req);
        JSONObject jsonObject = JSON.parseObject(json);
        String tel = jsonObject.getString("name");
        String password = jsonObject.getString("password");
        UserDao userDao = new UserDaoImpl();
//        String inputVcode = jsonObject.getString("inputVcode");
        String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(tel);
        boolean isMatched = matcher.matches();

        User loginuser = null;
        if (!isMatched) {
            loginuser = userService.login_by_tel(tel, Md5Util.md5(password));
        }else loginuser = userDao.select_by_mail(tel);
        System.out.println(loginuser);
//        String codes = (String)session.getAttribute("codes");
//        if ((codes!=null&&codes.equalsIgnoreCase(inputVcode))){
        if (loginuser != null) {
            //生成令牌
            String loginToken = UUID.randomUUID() + "";
            Token token = new Token(loginToken, true);
            req.setAttribute("token",token);
            Jedis loginJedis = JedisUtil.getJedis();
            loginJedis.set("token",loginToken);
            loginJedis.set(loginToken, JSON.toJSONString(loginuser));
            loginJedis.expire(loginToken, 6000);
            printWriter.write(JSON.toJSONString(token));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public String getParam(HttpServletRequest req) {
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
