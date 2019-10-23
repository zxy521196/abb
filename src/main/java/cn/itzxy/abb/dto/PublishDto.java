package cn.itzxy.abb.dto;

import cn.itzxy.abb.model.User;
import lombok.Data;


@Data
public class PublishDto {
    private Integer id;
    private String title;
    private String body;
    private String tag;
    private  Integer uid;
    private  int love_count;
    private  int answer_count;
    private  int view_count;
    private Long publish_time;
    private User user;
}
