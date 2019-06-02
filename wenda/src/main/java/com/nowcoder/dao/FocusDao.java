package com.nowcoder.dao;

import com.nowcoder.model.Focus;
import org.apache.ibatis.annotations.*;

@Mapper
public interface FocusDao {
    String TABLE_NAME = " focus ";
    String INSERT_FIELDS = " user_id, entity_id, entity_type, created_date ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Insert({"insert into ",TABLE_NAME,"(",INSERT_FIELDS,")","values(#{userId},#{entityId},#{entityType},#{createdDate})"})
    int insertFocus(Focus focus);

    @Select({"select count(*) from",TABLE_NAME," where entity_id=#{entityId} and entity_type=#{entityType}"})
    int getFocusCount(@Param("entityId") int entityId,@Param("entityType") int entityType);

    @Delete({"delete from ",TABLE_NAME,"where user_id=#{userId} and entity_id=#{entityId} and entity_type=#{entityType}"})
    int delFocus(Focus focus);

    @Select({"select",SELECT_FIELDS,"from ",TABLE_NAME,"where user_id=#{userId} and entity_id=#{entityId} and entity_type=#{entityType}"})
    Focus SelectByUser(@Param("userId") int userId,@Param("entityId") int entityId,@Param("entityType") int entityType);
}
