package cn.itcast.dao;

import cn.itcast.domain.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserDao {
    @Select("select * from user where name=#{name}")
    public List<User> getUserByname(String name);
}
