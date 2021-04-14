package com.servlet.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.entity.Token;
import com.entity.User;
import com.service.UserService;
import com.service.impl.UserServiceImpl;
import com.utils.GetLoginUser;
import com.utils.JedisUtil;
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

@WebServlet(value = "/mu")
public class ModifyUserController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");
        String param = this.getParam(req);
        User user = GetLoginUser.getLoginUser(req,resp);
        JSONObject jsonObject = JSONObject.parseObject(param);
        if (user!=null) {
            Integer ID = user.getID();
            String direction = jsonObject.getString("direction");
            String sex = jsonObject.getString("sex");
            String student_number = jsonObject.getString("student_number");
            String tel = jsonObject.getString("tel");
            String mail = jsonObject.getString("mail");
            int period_number = Integer.parseInt(jsonObject.getString("period_number"));
            User user1 = new User(ID,direction,sex,period_number,student_number,tel,mail);
            PrintWriter printWriter = resp.getWriter();
//        printWriter.write(JSON.toJSONString(user1));
            String MuToken = UUID.randomUUID() + "";
            Token token = new Token(MuToken, true);
            Jedis MuJedis = JedisUtil.getJedis();
            MuJedis.set(MuToken, JSON.toJSONString(user1));
            printWriter.write(JSON.toJSONString(token));
            MuJedis.expire(MuToken, 3000);
            UserService userService = new UserServiceImpl();
            int i = userService.modify(user1);
            if (i != 0) {
                resp.getWriter().println();
            } else resp.getWriter().println();
        }else resp.getWriter().println();
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
