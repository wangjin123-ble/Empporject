package com.servlet.jsp;

import com.entity.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ShowAllBooksJSP", value = "/ShowAllBooksJSP")
public class ShowAllBooksJSP extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Book> books=(List<Book>) request.getAttribute("books");
        PrintWriter printWriter =response.getWriter();
        printWriter.println("<html>");
        printWriter.println("  <head>");
        printWriter.println("      <meta charset='UTF-8'>");
        printWriter.println("      <title>查询书籍页面<title>");
        printWriter.println("  <head>");
        printWriter.println("  <body>");
        printWriter.println("          <table border='1'>");
        printWriter.println("                <tr>");
        printWriter.println("                     <td> ID  </td>");
        printWriter.println("                     <td> name  </td>");
        printWriter.println("                     <td> author  </td>");
        printWriter.println("                     <td> price  </td>");
        printWriter.println("                     <td> number  </td>");
        printWriter.println("                     <td> introduce  </td>");
        printWriter.println("                     <td>colspan='2' 操作</td>");
        printWriter.println("                <tr>");
        for (Book book: books){
            printWriter.println("                <tr>");
            printWriter.println("                     <td> "+book.getID() +"</td>");
            printWriter.println("                     <td> "+book.getName()+"</td>");
            printWriter.println("                     <td> "+book.getAuthor()+"</td>");
            printWriter.println("                     <td> "+book.getPrice()+"</td>");
            printWriter.println("                     <td> "+book.getNumber()+"</td>");
            printWriter.println("                     <td> "+book.getIntroduce()+"</td>");
            printWriter.println("                     <td>删除</td>");
            printWriter.println("                     <td>修改</td>");
            printWriter.println("                <tr>");

        }
        printWriter.println("          <table>");
        printWriter.println("  <body>");
        printWriter.println("<html>");
    }
}
