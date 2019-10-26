package cn.itzxy.abb.controller;

import cn.itzxy.abb.dto.CommentDto;
import cn.itzxy.abb.mapper.CommentMapper;
import cn.itzxy.abb.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;


@Controller
public class CommentController {
    @Autowired
    private CommentMapper commentMapper;
    @ResponseBody
    @PostMapping("/discuss")
    public Object discuss(@RequestBody CommentDto commentDto){
        Comment comment=new Comment();
        comment.setParentId(commentDto.getParentId());
        comment.setType(commentDto.getType());
        comment.setReviewId(1);
        comment.setCommentTime(System.currentTimeMillis());
        comment.setBody(commentDto.getBody());
        comment.setLikeCount(0L);
        commentMapper.insert(comment);
        HashMap<String, String> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("200","成功");
        return objectObjectHashMap;
    }
}
