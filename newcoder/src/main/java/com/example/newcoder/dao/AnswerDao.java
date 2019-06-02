package com.example.newcoder.dao;

import com.example.newcoder.model.Answer;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface AnswerDao {

    String TABLE_NAME="Answer";
    String INSERT_FIELD="content ,type_id ";
    String SELECT_FIELD="id,"+INSERT_FIELD;

    @Select({"select ",SELECT_FIELD,"from ",TABLE_NAME,"where id = #{id}"})
    Answer selectbyid(int id);

    List<Answer> selectbyTypeId(@Param("typeid") int typeid,@Param("limit") int limit);

    @Insert({"insert into",TABLE_NAME,"("+INSERT_FIELD+") values(#{content},#{typeid})"})
    void InsertAnswer(Answer answer);

    @Select({"select max(id) from ",TABLE_NAME})
    int getMaxAnswerId();

    @Update({"update ",TABLE_NAME,"set type_id = #{typeid} where id = #{id}"})
    void UpdateAnswer(Answer answer);
}
