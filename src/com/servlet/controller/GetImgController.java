package com.servlet.controller;


import com.dao.UserDao;
import com.dao.impl.UserDaoImpl;
import com.entity.Img;
import com.entity.User;
import com.service.ImgService;
import com.service.impl.ImgServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serial;
import java.util.List;

@WebServlet(value = "/persons.do")
public class GetImgController extends HttpServlet {

    @Serial
    private static final long serialVersionUID = -800352785988546254L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 判断上传表单是否为multipart/form-data类型
//        Img tx=null;

        HttpSession session = request.getSession();
//        User user = (User) session.getAttribute("loginuser");
//        在登录时将 User 对象放入了 会
        UserDao userDao = new UserDaoImpl();
        User user = userDao.select_by_name("张华崇");
        if (user != null) {
            int myid = user.getID();
//            String SQL="SELECT id,image_path,old_name FROM img WHERE user_id=?";
//            ResultSet rs=JdbcHelper.query(SQL,myid);
//            String uSQL="SELECT username,password FROM t_user WHERE id=?";
//            ResultSet urs=JdbcHelper.query(uSQL,myid);
            System.out.println("我的个人id是: " + myid);
            ImgService imgService = new ImgServiceImpl();
            List<Img> touxiang = imgService.getImgs(myid);
            user.setImg(touxiang);
            int n = user.getImg().size();
            Img img1 = user.getImg().get(n - 1);
            System.out.println(img1.getOld_name());
//            try {
//                if(rs.next())
//                {
//                    tx=new Img();
//                    tx.setUser_id(rs.getInt(1));
//                    tx.setImage_path(rs.getString(2));
//                    tx.setOld_name(rs.getString(3));
//                    touxiang.add(tx);
//                }
//                if(urs.next()){
//                    user.setImg(touxiang);
//                }
//
//            } catch (SQLException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }

//            session.setAttribute("user", user);
//            response.sendRedirect( request.getContextPath() + "/person.html");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
