package com.service;

import com.entity.Allbooks;
import com.entity.Book;

import java.util.List;

public interface BooksService {
     List<Allbooks> showAllBooks();

    public int updateBooks(int ID);




    public int removeBooks(int ID);
    public List<Allbooks> searchBooks(String name);


    public List<Allbooks> showBooks(String bookname);

}
