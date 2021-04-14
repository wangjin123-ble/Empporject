package com.service.impl;

import com.dao.BookDao;
import com.dao.UserDao;
import com.dao.impl.BookDaoImpl;
import com.dao.impl.UserDaoImpl;
import com.entity.Book;
import com.service.BookService;
import com.utils.DbUtils;

import java.util.List;

public class BookServiceImpl implements BookService {
    private BookDao bookDao = new BookDaoImpl();

    @Override
    public Book search_by_name(String name) {
        Book result = null;
        try {
            DbUtils.begin();
            result = bookDao.select(name);
            DbUtils.commit();
        } catch (Exception e) {
            DbUtils.rollback();
            e.printStackTrace();
        }
        return result;
    }
}
