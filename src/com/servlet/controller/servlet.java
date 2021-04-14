package com.servlet.controller;



import com.entity.AllUser;
import com.dao.impl.Text;
import com.json.ChangeJson;
import com.json.JsonChange1;
import com.utils.GetLoginUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Text;

@WebServlet(value = "/wts")
public class servlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 响应参数格式设置
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        //向外传值,并将数据转化为json格式保存在一个字符串对象里
        Text text =new Text();
        ChangeJson changeJson = new ChangeJson();
/****************************************************************************/
//将此字符串对象输出
        PrintWriter out=resp.getWriter();

        // 使用IOUtils的读取包方式
        // BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        // String body = IOUtils.read(reader);

        //使用InputStreamReader对象，获取前端传来的数据.其中
        // request.getInputStream()是读取前端传递来的数据字节流，
        // StandardCharsets.UTF_8是将前端传来的数据转化为UTF-8的编码方式
        InputStreamReader insr = new InputStreamReader(req.getInputStream(), StandardCharsets.UTF_8);
        //System.out.println(insr);
        StringBuilder body = new StringBuilder();
        int respInt = insr.read();
        while(respInt!=-1) { // 读取请求数据
            //将读取的字节流中的每一个字节转化为字符，然后添加到StringBuilder类型的对象中
            body.append((char) respInt);
            respInt = insr.read();
        }


        //out的print方法可以输出对象
      //  out.print(body);
        JsonChange1 jsonChange = new JsonChange1();
        //将StringBuilder类型的对象的对象通过toString方法转化为String类型，然后用fastjson的json包进行转化
        AllUser allUser = jsonChange.JsonChange(body.toString());


        //将int类型的变量转化为String类型
        //String ID = String.valueOf(user.getID());

        /*SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String apply_time= formatter.format(allUser.getApply_time());
        String deadline= formatter.format(allUser.getDeadline());*/


        if(allUser.getID()!=0 && allUser.getApply_book()!=null && allUser.getAuthor()!=null && allUser.getDeadline()!=null){
            text.record(allUser.getID(), allUser.getApply_book(), allUser.getAuthor(), allUser.getDeadline());//记录功能
        }else {
            System.out.println("记录信息有误");
        }

        if(allUser.getID()!=0 &&GetLoginUser.getLoginUser(req,resp).getName()!=null ){
            text.examine(allUser.getID(), allUser.getArticles(), GetLoginUser.getLoginUser(req,resp).getName(), allUser.getTel());//申请归还
        }else {
            System.out.println("申请信息有误");
        }

/******************************************************************************/


        String str2=changeJson.ChangeJson(text.examineselect(allUser.getID()));

        out.print(str2);


/******************************************************************************/


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
