package com.example.newcoder.service;

import com.example.newcoder.dao.UserDao;
import com.example.newcoder.model.User;
import com.example.newcoder.util.md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.SQLException;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserDao userDao;
    public void addUser(User user)
    {
        userDao.addUser(user);
    }
    public User selectbyid(int id)
    {
        User user=userDao.selectbyid(id);
        return user;
    }
    public void updatepassword(User user)
    {
        userDao.updatepassword(user);
    }
    public List<User> selectbyname(String name)
    {
        return userDao.selectbyname(name);
    }
    public boolean login(String username,String password)
    {
        List<User> users=userDao.selectbyname(username);
        if(users.size()<=0)
        {
            return false;
        }
        for(User user:users)
        {
            String salt=user.getSalt();
            String realpassword=md5.GetMD5Code(password+salt);//密码加salt再进行加密
            if(!realpassword.equals(user.getPassword()))
            {//若密码错误
                return false;
            }
            break;
        }
        return true;
    }
    public List<User> selectbynamelike(String name)
    {
        return userDao.selectbynamelike(name);
    }

    public boolean RegUser(String username,String password)
    {
        md5 getmd5=new md5();

        User user=new User();
        user.setName(username);
        String salt= Random(6);
        String realpassword=md5.GetMD5Code(password+salt);//密码加salt再进行加密
        user.setPassword(realpassword);
        user.setSalt(salt);
        try {
            userDao.addUser(user);
            return true;
        }catch (Exception e)
        {
            return false;
        }
    }

    public static String Random(int length) {
        char[] str= new char[length];
        int i = 0;
        int num=3;//数字的个数
        while (i < length) {
            int f = (int) (Math.random() * num);
            if (f == 0)
                str[i] = (char) ('A' + Math.random() * 26);
            else if (f == 1)
                str[i] = (char) ('a' + Math.random() * 26);
            else
                str[i] = (char) ('0' + Math.random() * 10);
            i++;
        }
        String random_str = new String(str);
        return random_str;
    }

}
