package com.example.newcoder.dao;

import com.example.newcoder.model.Ticket;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.lang.annotation.Target;

/**
 *Created by zhaoxiaoqin on 2019/4/9
 */
@Component
@Mapper
public interface TicketDao {
    String TABLE_NAME="ticket";
    String INSERT_FIELD="user_id ,expire ,status ,ticket";
    String SELECT_FIELD="id,"+INSERT_FIELD;

    @Select({"select ",SELECT_FIELD," from ",TABLE_NAME,"where ticket= #{ticket}"})
    Ticket selectTicketbyTicket(@Param("ticket") String ticket);

    @Insert({"insert into ",TABLE_NAME,"(",INSERT_FIELD,") values(#{userid},#{expire},#{status},#{ticket})"})
    void insertTicket(Ticket ticket);

    @Update({"update ", TABLE_NAME," set status=#{status},user_id=#{userid},expire=#{expire} where ticket = #{ticket}"})
    void UpdateTicket(Ticket ticket);
}
