package com.demo.FirstMVC.Dao;

import com.demo.FirstMVC.Entity.User;

import java.util.List;

public interface UserDao {
    List<User>selectbyname(String name);
}
