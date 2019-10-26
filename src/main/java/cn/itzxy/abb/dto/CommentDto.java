package cn.itzxy.abb.dto;

import lombok.Data;

@Data
public class CommentDto {
    private int parentId;
    private  String body;
    private  int type;
}
