package com.service.impl;

import com.dao.BooksDao;
import com.dao.impl.BooksDaoImpl;
import com.entity.Allbooks;
import com.entity.Book;
import com.service.BooksService;
import com.utils.DbUtils;

import java.util.ArrayList;
import java.util.List;

public class BooksServiceImpl implements BooksService {

    private BooksDao booksDao=new BooksDaoImpl();


    @Override
    public List<Allbooks> showAllBooks() {
        List<Allbooks> allbooks=new ArrayList<>();
        try {
            DbUtils.begin();
            List<Allbooks> temps = booksDao.selectAll();
            if(temps!=null){
                allbooks=temps;
            }
            DbUtils.commit();
        } catch (Exception e) {
            DbUtils.rollback();
            e.printStackTrace();
        }
        return allbooks;
    }

    @Override
    public int updateBooks(int ID) {
        int result=0;
        try {
            DbUtils.begin();
            result = booksDao.update(ID);
            DbUtils.commit();
        } catch (Exception e) {
            DbUtils.rollback();
            e.printStackTrace();
        }
        return result;
    }




    @Override
    public int removeBooks(int ID) {
        int result=0;
        try {
            DbUtils.begin();
            result = booksDao.delete(ID);
            DbUtils.commit();
        } catch (Exception e) {
            DbUtils.rollback();
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Allbooks> searchBooks(String name){
        List<Allbooks> result=null;
        try {
            DbUtils.begin();
            result = booksDao.select(name);
            DbUtils.commit();
        } catch (Exception e) {
            DbUtils.rollback();
            e.printStackTrace();
        }
        return result;

    }


    @Override
    public List<Allbooks> showBooks(String bookname) {
        List<Allbooks> result=null;
        try {
            DbUtils.begin();
            result=booksDao.select(bookname);
            DbUtils.commit();
        } catch (Exception e) {
            DbUtils.rollback();
            e.printStackTrace();
        }
        return result;
    }




}
