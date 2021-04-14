package com.dao;

import com.entity.Allbooks;
import com.entity.Book;


import java.util.List;

public interface BooksDao {





    public  int delete(int ID ) ;

    public List<Allbooks> select (String bookname);
    public List<Allbooks> select (int ID);

    public int update(int ID);

     List<Allbooks> selectAll() ;


}
