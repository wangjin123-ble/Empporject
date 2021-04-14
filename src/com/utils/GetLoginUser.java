package com.utils;


import com.alibaba.fastjson.JSONObject;
import com.dao.UserDao;
import com.dao.impl.UserDaoImpl;
import com.entity.Token;
import com.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class GetLoginUser {
    public static User getLoginUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        String token = null;
//        Enumeration<String> token = req.getHeaderNames();
//        Map<String, String> map = new HashMap<>();
//        Enumeration headerNames = req.getHeaderNames();
//        while (headerNames.hasMoreElements()) {
//            String key = (String) headerNames.nextElement();
//            System.out.println(key);
//            String value = req.getHeader(key);
//            map.put(key, value);
//        }
//        String token = map.get("token");
//        String json = JedisUtil.getJedis().get(token);
//        while(token.hasMoreElements()){
//            //遍历枚举中存储的每一个元素
//            String value = (String)token.nextElement();
//            System.out.println(value);//将值输出
//        }
        String param = getParam(req);
        JSONObject jsonObject1 = JSONObject.parseObject(param);
//        Token token1 = (Token) req.getAttribute("token");
//        String token = token1.getToken();
//        String token = jsonObject1.getString("token");
        String token = JedisUtil.getJedis().get("token");
        System.out.println(token);
        String json = JedisUtil.getJedis().get(token);
        JSONObject jsonObject = JSONObject.parseObject(json);
        String tel = jsonObject.getString("tel");
        UserDao userDao = new UserDaoImpl();
        User loginUser = userDao.select_by_tel(tel);
        return loginUser;
    }

    public static String getParam(HttpServletRequest req) {
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
