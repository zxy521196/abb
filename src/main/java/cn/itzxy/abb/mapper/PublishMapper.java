package cn.itzxy.abb.mapper;

import cn.itzxy.abb.model.Publish;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import javax.websocket.server.ServerEndpoint;
import java.util.List;

@Mapper
public interface PublishMapper {
    @Insert("insert into publish(title,body,tag,uid,publish_time) values(#{title},#{body},#{tag},#{uid},#{publish_time})")
    void insert(Publish publish);
    @Select("select * from publish")
    List<Publish> findAll();
    @Select("select * from publish limit #{page},#{size}")
    List<Publish> pageList(@Param("page") int page, @Param("size") int size);
    @Select("select count(1) from publish")
    Integer getTotalcount();
    @Select("select * from publish where uid=#{uid} limit #{page},#{size}")
    List<Publish> findByUid(@Param("uid") int uid,@Param("page") int page,@Param("size")int size);
    @Select("select count(1) from publish where uid=#{uid}")
    Integer getMyquestionCount(@Param("uid") int uid);
    @Select("select * from publish where id=#{id}")
    Publish findById(@Param("id") int id);
    @Update("update publish set title=#{title},body=#{body},tag=#{tag},publish_time=#{publish_time},view_count=#{view_count},love_count=#{love_count},answer_count=#{answer_count} where id=#{id}")
    int update(Publish publish);
}
