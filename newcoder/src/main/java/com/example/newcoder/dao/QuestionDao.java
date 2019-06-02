package com.example.newcoder.dao;

import com.example.newcoder.model.Question;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface QuestionDao {
    String TABLE_NAME="Question";
    String INSERT_FIELD="title ,answer_id ";
    String SELECT_FIELD="id,"+INSERT_FIELD;


    @Select({"select ",SELECT_FIELD,"from ",TABLE_NAME,"where id = #{id}"})
    Question selectbyid(int id);//根据题号查找题目

    List<Question> getRandomQuestion(@Param("limit") int limit);//获取前limit个随机题目

    @Insert({"insert into ",TABLE_NAME,"(title) values(#{title})"})
    void InsertQuestionNoAnswerID(Question question);

    @Select({"select max(id) from ",TABLE_NAME})
    int getMaxQuestionId();

    @Update({"update ",TABLE_NAME,"set answer_id = #{answerid} where id = #{id}"})
    void UpdateQuestion(Question question);

    @Select({"select ",SELECT_FIELD," from ",TABLE_NAME})
    List<Question>  selectAll();
}
