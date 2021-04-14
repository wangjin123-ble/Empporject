package com.servlet.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dao.UserDao;
import com.dao.impl.UserDaoImpl;
import com.entity.User;
import com.service.UserService;
import com.service.impl.UserServiceImpl;
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

@WebServlet(value = "/fps")
public class ForgetPasswordController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");
        UserDao userDao = new UserDaoImpl();
        String param = this.getParam(req);
        JSONObject jsonObject = JSONObject.parseObject(param);
        String mail = jsonObject.getString("mail");
        User user = userDao.select_by_mail(mail);
        String new_password = jsonObject.getString("new_password");
        String new_password_again = jsonObject.getString("new_password_again");
//        String code = jsonObject.getString("code");
//        String codes = JedisUtil.getJedis().get("codes");
        UserService userService = new UserServiceImpl();
        PrintWriter printWriter = resp.getWriter();
        int i;
        if (user!=null&&new_password.equals(new_password_again)) {
            i = userService.change_password(new_password, user.getName());
            if (i == 1) {
                printWriter.write(JSON.toJSONString(true));
            }
        } else if (user == null) {
            printWriter.write(JSON.toJSONString(false));
            printWriter.write("邮箱验证错误");
        } else if (new_password.length() < 6) {
            printWriter.write(JSON.toJSONString(false));
            printWriter.println("密码必须为六位以上");
        } else {
            printWriter.write(JSON.toJSONString(false));
            printWriter.println("两次密码输入不一致");
        }

//        if (user!=null){
//            HttpSession session = req.getSession();
//            resp.getWriter().write(JSON.toJSONString(true));
//            session.setAttribute("forgetuser",user);
//        }else resp.getWriter().write("验证错误");
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
