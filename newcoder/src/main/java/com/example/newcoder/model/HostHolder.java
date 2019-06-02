package com.example.newcoder.model;

import org.springframework.stereotype.Component;

/**
 * create by zhaoxiaoqin on 2019/3/14
 */
@Component
public class HostHolder{
    private ThreadLocal<User> users=new ThreadLocal<>();
    public User getUser()
    {
        return users.get();
    }
    public void setUser(User user)
    {
        users.set(user);
    }
    public void clear()//clear user
    {
        users.remove();
    }
}
