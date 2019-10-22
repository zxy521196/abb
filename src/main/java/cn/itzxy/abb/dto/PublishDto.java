package cn.itzxy.abb.dto;

import cn.itzxy.abb.model.User;
import lombok.Data;

import java.util.Date;

@Data
public class PublishDto {
    private Integer id;
    private String title;
    private String body;
    private String tag;
    private  Integer uid;
    private  Integer love_count;
    private  Integer answer_count;
    private  Integer view_count;
    private Long publish_time;
    private User user;
}
