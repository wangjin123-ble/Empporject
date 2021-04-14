package com.service;

import com.entity.User;

public interface UserService {
    public User login_by_tel(String tel, String password);

    public void register(String password, String mail);

    public int change_password(String password, String name);

    public int modify(User user);
}
