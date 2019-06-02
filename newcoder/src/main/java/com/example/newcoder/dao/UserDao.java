package com.example.newcoder.dao;

import com.example.newcoder.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *Created by zhaoxiaoqin on 2019/3/13
 */
@Component
@Mapper
public interface UserDao {
    String TABLE_NAME="user";
    String INSERT_FIELD="name ,password ,salt ,head_url ";
    String SELECT_FIELD="id,"+INSERT_FIELD;

    @Insert({"insert into ",TABLE_NAME,"(",INSERT_FIELD,") values(#{name},#{password},#{salt},#{headurl})"})
    void addUser(User user);
    @Select({"select ",SELECT_FIELD," from",TABLE_NAME,"where id=#{id}"})
    User selectbyid(int id);
    @Update({"update ",TABLE_NAME," set password=#{password} where id = #{id}"})
    void updatepassword(User user);

    @Select({"select ",SELECT_FIELD," from ",TABLE_NAME," where name=#{name}"})
    List<User> selectbyname(@Param("name") String name);

    @Select({"select * from ",TABLE_NAME," where name=#{name} and password=#{password}"})
    User selectUser(User user);

    @Select({"select * from",TABLE_NAME,"where name like CONCAT(#{name},'%')"})
    List<User> selectbynamelike(@Param("name")String name);
}
