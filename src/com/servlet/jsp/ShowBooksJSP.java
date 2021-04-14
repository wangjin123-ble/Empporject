package com.servlet.jsp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet(value ="/ShowBooks")
public class ShowBooksJSP extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name=String.valueOf(req.getParameter("name"));
        PrintWriter printWriter=resp.getWriter();

            printWriter.println("<html>");
            printWriter.println("<head>");
            printWriter.println("<meta charset='UTF-8'>");
            printWriter.println("<title>  查询详情</title>");
            printWriter.println("<head>");
            printWriter.println("<body>");
            printWriter.println("<table>");
            printWriter.println("      <tr>");
            printWriter.println("          <td>ID</td>");
            printWriter.println("          <td>name</td>");
            printWriter.println("          <td>author</td>");
            printWriter.println("          <td>price</td>");
            printWriter.println("          <td>number</td>");
            printWriter.println("          <td>introduce</td>");
            printWriter.println("      </tr>");
            printWriter.println("<table>");
            printWriter.println("<body>");
            printWriter.println("<html>");
        }

    }


