package com.dao.impl;

import com.entity.User;
import com.utils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import com.dao.UserDao;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;

public class UserDaoImpl implements UserDao {

    private QueryRunner queryRunner = new QueryRunner();

    @Override
    public int insert(User user) {
        String sql = "insert into user (name,direction,sex,is_super_user,password,period_number,student_number,tel,mail) values (?,?,?,?,?,?,?,?,?)";
        Object[] params = {user.getName(), user.getDirection(), user.getSex(), 0, user.getPassword(), user.getPeriod_number(), user.getStudent_number(), user.getTel(), user.getMail()};
        try {
            queryRunner.insert(DbUtils.getConnection(), sql, new ScalarHandler<>(), params);
            return 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }
    }

    @Override
    public int insert_register(String name, String password, String tel) {
        String sql = "insert into user (name,password,tel) values (?,?,?)";
        Object[] params = {name, password, tel};
        try {
            queryRunner.insert(DbUtils.getConnection(), sql, new ScalarHandler<>(), params);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            return 0;
        }
    }

    @Override
    public int delete(String name) {
        return 0;
    }

    @Override
    public int update(User user) {
        try {
            int update = queryRunner.update(DbUtils.getConnection(), "update user set direction=?,sex=?,student_number=?,tel=?,mail=?,period_number=? where id = ?", user.getDirection(), user.getSex(), user.getStudent_number(), user.getTel(), user.getMail(), user.getPeriod_number(), user.getID());
            return update;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    @Override
    public User select_by_name(String name) {
        try {
            User user = queryRunner.query(DbUtils.getConnection(), "select * from user where name=?;", new BeanHandler<User>(User.class), name);
            return user;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public User select_by_tel(String tel) {
        try {
            User user = queryRunner.query(DbUtils.getConnection(), "select * from user where tel=?;", new BeanHandler<>(User.class), tel);
            return user;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public User select_by_mail(String mail) {
        try {
            User user = queryRunner.query(DbUtils.getConnection(), "select * from user where mail=?;", new BeanHandler<>(User.class), mail);
            return user;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public User select_by_id(int id) {
        try {
            User user = queryRunner.query(DbUtils.getConnection(), "select * from user where id=?;", new BeanHandler<User>(User.class), id);
            return user;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public int update_password(String password, String name) {
        String sql = "update user set password=? where name=?";
        Object[] params = {password, name};
        try {
            queryRunner.update(DbUtils.getConnection(), sql, params);
            return 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }
    }
}
