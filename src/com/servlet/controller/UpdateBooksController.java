package com.servlet.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dao.BooksDao;
import com.dao.impl.BooksDaoImpl;
import com.entity.Change;
import com.json.JsonChange2;
import com.service.BooksService;
import com.service.impl.BooksServiceImpl;
import com.utils.DbUtils;

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
import java.text.SimpleDateFormat;

@WebServlet(value = "/ns")
public class UpdateBooksController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;


        String result = this.getParam(request, response);

        JSONObject jsonObject = JSONObject.parseObject(result);

        String mingcheng = jsonObject.getString("name");
        String author = jsonObject.getString("author");
        String shujileibie = jsonObject.getString("leibie");
        String chubanriqi = jsonObject.getString("chubanriqi");
        String jiage = jsonObject.getString("jiage");
        String goururiqi = jsonObject.getString("goururiqi");
        String gourushuliang= jsonObject.getString("gourushuliang");
        String cunfangdidain= jsonObject.getString("cunfangdidian");
        String fuzeren= jsonObject.getString("fuzeren");
        String suoshubumen = jsonObject.getString("suoshubumen");
        String jieshao = jsonObject.getString("introduce");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        try {
            connection = DbUtils.getConnection();

            String sql ="UPDATE allbooks SET article='图书',name=?,author=?,variety=?,time=?,price=?,buy_time=?,number=?,place=?,manager=?,department=?,introducescore=? WHERE ID=?";
            statement = connection.prepareStatement(sql);


            statement.setString(1,mingcheng);
            statement.setString(2,author);
            statement.setString(3,shujileibie);
            statement.setString(4,chubanriqi);
            statement.setString(5,jiage);
            statement.setString(6,goururiqi);
            statement.setString(7,gourushuliang);
            statement.setString(8,cunfangdidain);
            statement.setString(9,fuzeren);
            statement.setString(10,suoshubumen);
            statement.setString(11,jieshao);



            int i = statement.executeUpdate();
            PrintWriter printWriter = response.getWriter();

            if(i>0){
                System.out.println("提交成功");
                String s = JSON.toJSONString(true);
                printWriter.write(s);
            }
            else {
                System.out.println("提交失败");
                String s = JSON.toJSONString(false);
                printWriter.write(s);

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DbUtils.closeAll(connection,statement,resultSet);
        }
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
