package com.servlet.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dao.UserDao;
import com.dao.impl.UserDaoImpl;
import com.entity.User;
import com.service.UserService;
import com.service.impl.UserServiceImpl;
import com.utils.JedisUtil;
import com.utils.Md5Util;

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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(value = "/rst")
public class RegisterController extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");
        boolean isRegisted = false;
        UserDao userDao = new UserDaoImpl();
        String jsonstring = this.getParam(req);
        JSONObject jsonObject = JSONObject.parseObject(jsonstring);
        String password = jsonObject.getString("password");
        String password2 = jsonObject.getString("password2");
        String mail = jsonObject.getString("receiverEmail");
        String code = jsonObject.getString("code");
        String codes = JedisUtil.getJedis().get("codes");
        User user1 = userDao.select_by_mail(mail);
//        String name = req.getParameter("name");
//        String password = req.getParameter("password");
//        String tel = req.getParameter("tel");
        PrintWriter printWriter = resp.getWriter();
        UserService userService = new UserServiceImpl();
        String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(mail);
        boolean isMatched = matcher.matches();
        if (user1 == null && password.length() >= 6 && isMatched && password.equals(password2)&&code.equals(codes)) {
            userService.register(Md5Util.md5(password), mail);
        }
//        else if (!password.equals(password2)) {
//            printWriter.write("两次密码输入不一致");
//        } else if (user1 != null) {
//            printWriter.write("此邮箱已注册");
//        } else if (!code.equals(codes)) {
//            printWriter.write("验证码错误");
//        }else printWriter.write("邮箱格式错误");
        User user = userDao.select_by_mail(mail);
        if (user != null) {
            String str = JSON.toJSONString(user);
            printWriter.write(str);
            isRegisted = true;
        }
        printWriter.write(JSON.toJSONString(isRegisted));
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
            System.out.println(result);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
