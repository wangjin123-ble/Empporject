package com.dao;

import com.entity.Book;

import java.util.List;

public interface BookDao {
    Book select(String name);
}
