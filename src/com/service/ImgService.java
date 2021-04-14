package com.service;

import com.entity.Img;
import com.entity.User;

import java.util.List;

public interface ImgService {
    public int add(Img img);

    public List<Img> getImgs(int user_id);
}
