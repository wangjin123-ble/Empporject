package com.dao.impl;

import com.dao.BookDao;
import com.entity.Book;
import com.utils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements BookDao {

    private QueryRunner queryRunner = new QueryRunner();

    @Override
    public Book select(String name) {
        try {
            Book book = queryRunner.query(DbUtils.getConnection(), "select * from books where name = ?; ", new BeanHandler<Book>(Book.class), name);
            return book;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }
}
