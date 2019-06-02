package com.demo.FirstMVC.Impl;

import com.demo.FirstMVC.Dao.UserDao;
import com.demo.FirstMVC.Entity.User;

import java.util.List;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;

@Component
public class UserDaoImpl implements UserDao {
    private JdbcTemplate jt;//连接模板
    @Override
    public List<User> selectbyname(String name) {
        String sql="select * from user where name like "+name+"%";
        List<User> users=jt.query(sql, new RowMapper<User>(){
            @Override
            public User mapRow(ResultSet rs, int arg1) throws SQLException {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setName(rs.getString("name"));
                return u;
            }});
        return users;
    }
    public void setJt(JdbcTemplate jt) {
    this.jt = jt;
    }
}
