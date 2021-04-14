package com.servlet.controller;


import com.dao.UserDao;
import com.dao.impl.UserDaoImpl;
import com.entity.Img;
import com.entity.User;
import com.service.ImgService;
import com.service.impl.ImgServiceImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.Serial;
import java.util.Date;
import java.util.List;

@WebServlet(value = "/UploadServlet.do")
public class UploadController extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 判断上传表单是否为multipart/form-data类型
        HttpSession session = request.getSession();
//        User user = (User) session.getAttribute("loginuser"); // 在登录时将 User 对象放入了 会话
        // 中
        UserDao userDao = new UserDaoImpl();
        User user = userDao.select_by_name("张华崇");
        if (ServletFileUpload.isMultipartContent(request)) {

            try {
                // 1. 创建DiskFileItemFactory对象，设置缓冲区大小和临时文件目录
                DiskFileItemFactory factory = new DiskFileItemFactory();
                // System.out.println(System.getProperty("java.io.tmpdir"));//默认临时文件夹

                // 2. 创建ServletFileUpload对象，并设置上传文件的大小限制。
                ServletFileUpload sfu = new ServletFileUpload(factory);
                sfu.setSizeMax(10 * 1024 * 1024);// 以byte为单位 不能超过10M 1024byte =
                // 1kb 1024kb=1M 1024M = 1G
                sfu.setHeaderEncoding("utf-8");

                // 3.
                // 调用ServletFileUpload.parseRequest方法解析request对象，得到一个保存了所有上传内容的List对象。
                List<FileItem> fileItemList = sfu.parseRequest(request);

                // 4. 遍历list，每迭代一个FileItem对象，调用其isFormField方法判断是否是上传文件
                for (FileItem fileItem : fileItemList) {
                    // 普通表单元素
                    if (fileItem.isFormField()) {
                        String name = fileItem.getFieldName();// name属性值
                        String value = fileItem.getString("utf-8");// name对应的value值

                        System.out.println(name + " = " + value);
                    }
                    // <input type="file">的上传文件的元素
                    else {
                        String fileName = fileItem.getName();// 文件名称
                        System.out.println("原文件名：" + fileName);// Koala.jpg

                        String suffix = fileName.substring(fileName.lastIndexOf('.'));
                        System.out.println("扩展名：" + suffix);// .jpg

                        // 新文件名（唯一）
                        String newFileName = new Date().getTime() + suffix;
                        System.out.println("新文件名：" + newFileName);// image\1478509873038.jpg

                        // 5. 调用FileItem的write()方法，写入文件
                        File file = new File("C:/Users/张华崇/IdeaProjects/WebProject/web/images/" + newFileName);
                        File file1 = new File("http://r7rbzq.natappfree.cc/Home/images/" + newFileName);
                        System.out.println(file1.getAbsolutePath());
                        response.getWriter().write(file1.getAbsolutePath());
                        fileItem.write(file);

                        // 6. 调用FileItem的delete()方法，删除临时文件
                        fileItem.delete();

                        /*
                         * 存储到数据库时注意 1.保存源文件名称 Koala.jpg 2.保存相对路径
                         * image/1478509873038.jpg
                         *
                         * int rows = JdbcHelper.insert(SQL, false, "touxiang/" + newFileName, myid, fileName);
                         */
                        if (user != null) {
//                            QueryRunner queryRunner = new QueryRunner();
                            int myid = user.getID();
                            Img img = new Img(myid, newFileName, fileName);
                            ImgService imgService = new ImgServiceImpl();
                            int i = imgService.add(img);

//                            String SQL = "INSERT INTO img(user_id,image_path,old_name)VALUES(?,?,?)";
//                            Object[] params = {myid,newFileName,fileName};
//                            Object insert = queryRunner.insert(DbUtils.getConnection(), SQL, new ScalarHandler<>(), params);
                            if (i == 1) {
                                session.setAttribute("image_name", fileName);
                                session.setAttribute("image_path", "images/" + newFileName);
                                response.sendRedirect(request.getContextPath() + "/persons.do");
//                                response.sendRedirect(request.getContextPath() + "/upImage.html");
                            }
                        } else {
                            session.setAttribute("loginFail", "请登录");
                            response.sendRedirect(request.getContextPath() + "/login.html");
                        }

                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}

