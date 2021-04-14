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
import java.util.UUID;

//未登录时忘记密码或登陆后直接修改密码
@WebServlet(value = "/cps")
public class ChangePasswordController extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");
        UserService userService = new UserServiceImpl();
        String param = this.getParam(req);
        System.out.println(param);
        JSONObject jsonObject = JSONObject.parseObject(param);
        String mail = jsonObject.getString("mail");
        String token = jsonObject.getString("token");
        String json = JedisUtil.getJedis().get(token);
        JSONObject jsonObject1 = JSONObject.parseObject(json);
        String tel = jsonObject1.getString("tel");
        UserDao userDao = new UserDaoImpl();
        User user = userDao.select_by_tel(tel);
        User user1 = userDao.select_by_mail(mail);
        String new_password = jsonObject.getString("new_password");
        String new_password_again = jsonObject.getString("new_password_again");
        String password1 = jsonObject1.getString("password");
        String password = user.getPassword();
        PrintWriter printWriter = resp.getWriter();
        int i = 0;
        if (user1!=null&&new_password.equals(new_password_again)&&user.getMail().equals(user1.getMail())&&password.equals(password1)) {
            i = userService.change_password(new_password, user.getName());
            if (i!=0){
                printWriter.write(JSON.toJSONString(true));
                String newToken = UUID.randomUUID() + "";
                JedisUtil.getJedis().set(newToken,JSON.toJSONString(user));
                JedisUtil.getJedis().expire(newToken, 3000);
                printWriter.write(newToken);
            }
        } else if (new_password.length() < 6) {
//            printWriter.println("密码必须为六位以上");
            printWriter.write(JSON.toJSONString(false));
        } else if (!user.getMail().equals(user1.getMail())) {
            printWriter.write(JSON.toJSONString(false));
        } else if (!password.equals(password1)) {
//            printWriter.println("原密码输入错误");
            printWriter.write(JSON.toJSONString(false));
        }else if (user1==null){
            printWriter.write(JSON.toJSONString(false));
        }
        else {
//            printWriter.println("两次密码输入不一致");
            printWriter.write(JSON.toJSONString(false));
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
            System.out.println(result);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
