package com.dao;

import com.entity.Img;
import com.entity.User;

import java.util.List;

public interface ImgDao {
    public void insert(Img img);

    public List<Img> select(int user_id);
}
