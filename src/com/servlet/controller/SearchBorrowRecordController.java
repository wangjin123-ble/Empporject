package com.servlet.controller;

import com.alibaba.fastjson.JSON;
import com.entity.BorrowRecord;
import com.entity.User;
import com.service.BorrowRecordService;
import com.service.impl.BorrowRecordServiceImpl;
import com.utils.GetLoginUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/sbr")
public class SearchBorrowRecordController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");
        User loginUser = GetLoginUser.getLoginUser(req, resp);
        BorrowRecordService borrowRecordService = new BorrowRecordServiceImpl();
        List<BorrowRecord> borrowRecordList = borrowRecordService.showAllRecord(loginUser.getMail());
        String jsonString = JSON.toJSONString(borrowRecordList);
        System.out.println(jsonString);
        resp.getWriter().write(jsonString);

    }

//    public String getParam(HttpServletRequest req) {
//        try {
//            InputStream input = req.getInputStream();
//            String result = "";
//            byte[] b = new byte[1024];
//            while (input.read(b) != -1) {
//                result = new String(b, StandardCharsets.UTF_8);
//            }
//            System.out.println(result);
//            return result;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}
