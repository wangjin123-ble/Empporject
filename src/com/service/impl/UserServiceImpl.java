package com.service.impl;

import com.utils.DbUtils;
import com.dao.UserDao;
import com.dao.impl.UserDaoImpl;
import com.entity.User;
import com.service.UserService;
import com.utils.Md5Util;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public User login_by_tel(String tel, String password) {
        User result = null;
        try {
            DbUtils.begin();
            User user = userDao.select_by_tel(tel);
            if (user != null) {
                if (user.getPassword().equals(password)) {
                    result = user;
                }
            }
            DbUtils.commit();
        } catch (Exception e) {
            DbUtils.rollback();
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void register(String password, String mail) {
        try {
            DbUtils.begin();
            User user = new User();
//            user.setName(name);
            user.setPassword(password);
            user.setMail(mail);
            userDao.insert(user);
            DbUtils.commit();
        } catch (Exception e) {
            DbUtils.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public int change_password(String password, String name) {
        try {
            DbUtils.begin();
            userDao.update_password(Md5Util.md5(password), name);
            DbUtils.commit();
            return 1;
        } catch (Exception e) {
            DbUtils.rollback();
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int modify(User user) {
        int result = 0;
        try {
            DbUtils.begin();
            result = userDao.update(user);
            DbUtils.commit();
        } catch (Exception e) {
            DbUtils.rollback();
            e.printStackTrace();
        }
        return result;
    }
}
