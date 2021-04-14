package com.dao;

import com.entity.User;

public interface UserDao {
    public int insert(User user);

    public int insert_register(String name, String password, String tel);

    public int delete(String username);

    public int update(User user);

    public User select_by_name(String username);

    public User select_by_tel(String tel);

    public User select_by_mail(String mail);

    public User select_by_id(int id);

    public int update_password(String password, String name);
}
