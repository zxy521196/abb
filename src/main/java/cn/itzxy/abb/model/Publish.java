package cn.itzxy.abb.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
public class Publish {
    private Integer id;
    private String title;
    private String body;
    private String tag;
    private  Integer uid;
    private  int love_count;
    private  int answer_count;
    private  int view_count;
    private Long publish_time;

    @Override
    public String toString() {
        return "Publish{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", tag='" + tag + '\'' +
                ", uid=" + uid +
                ", love_count=" + love_count +
                ", answer_count=" + answer_count +
                ", view_count=" + view_count +
                '}';
    }
}
