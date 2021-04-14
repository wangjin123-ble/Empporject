package com.servlet.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dao.BooksDao;
import com.dao.impl.BooksDaoImpl;
import com.entity.Change;
import com.json.JsonChange2;
import com.utils.DbUtils;
import com.utils.GetLoginUser;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@WebServlet(value = "/jieyongController")
//预约按钮
public class jieyongController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        //将此字符串对象输出
        PrintWriter out=resp.getWriter();
         /*使用InputStreamReader对象，获取前端传来的数据.其中
           request.getInputStream()是读取前端传递来的数据字节流，
           StandardCharsets.UTF_8是将前端传来的数据转化为UTF-8的编码方式*/
        InputStreamReader insr = new InputStreamReader(req.getInputStream(), StandardCharsets.UTF_8);
        StringBuilder body = new StringBuilder();
        int respInt = insr.read();
        while(respInt!=-1) { // 读取请求数据
            //将读取的字节流中的每一个字节转化为字符，然后添加到StringBuilder类型的对象中
            body.append((char) respInt);
            respInt = insr.read();
        }

        System.out.println(body);
        //out的print方法可以输出对象
        out.print(body);
        JsonChange2 jsonChange = new JsonChange2();
        //将StringBuilder类型的对象的对象通过toString方法转化为String类型，然后用fastjson的json包进行转化
        Change change = jsonChange.JsonChange(body.toString());



        Integer str1 = Integer.valueOf(change.getID());

        try {
            Connection connection = DbUtils.getConnection();
            QueryRunner qr = new QueryRunner();

            String query = "Select * from allbooks where ID = '"+ str1+"'";

            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            String name = "";
            while (resultSet.next()) {

                name = resultSet.getString("name");
                System.out.println(name);
            }
            String sql ="INSERT INTO examine(articles,number,types,time,pass,name ) VALUE (?,?,?,current_date ,?,?) ";
            statement = connection.prepareStatement(sql);
            statement.setString(1,name);
            statement.setInt(2,str1);
            statement.setString(3,"借用");
            statement.setString(4,"0");
            statement.setString(5, GetLoginUser.getLoginUser(req,resp).getName());

            int i = statement.executeUpdate();

            DbUtils.closeAll(connection,statement,resultSet);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    public String getParam(HttpServletRequest req, HttpServletResponse resp) {
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
