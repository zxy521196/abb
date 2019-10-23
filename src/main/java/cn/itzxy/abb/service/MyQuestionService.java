package cn.itzxy.abb.service;

import cn.itzxy.abb.dto.PageDto;
import cn.itzxy.abb.dto.PublishDto;
import cn.itzxy.abb.mapper.PublishMapper;
import cn.itzxy.abb.mapper.UserMapper;
import cn.itzxy.abb.model.Publish;
import cn.itzxy.abb.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class MyQuestionService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PublishMapper publishMapper;
    public PageDto publishDtoList(int uid,
                                  int page,
                                  int size){
        PageDto pageDto=new PageDto();
        List<PublishDto> publishDtoList=new ArrayList<>();
        pageDto.setPage(publishMapper.getMyquestionCount(uid),page,size);
        if(page>pageDto.getAllPage()){
            page=pageDto.getAllPage();
        }
        List<Publish> publishList = publishMapper.findByUid(uid,size*(page-1),size);
        for(Publish publish:publishList){
            User user = userMapper.selectByPrimaryKey(publish.getUid());
            PublishDto publishDto = new PublishDto();
            BeanUtils.copyProperties(publish,publishDto);
            publishDto.setUser(user);
            publishDtoList.add(publishDto);
        }
        pageDto.setPage(page);
        pageDto.setPublishDtoList(publishDtoList);
        pageDto.setAllCount(publishMapper.getMyquestionCount(uid));
        return  pageDto;
    }

    public PublishDto findByid(int id) {
        Publish publish = publishMapper.findById(id);
        publish.setView_count(publish.getView_count()+1);
        publishMapper.update(publish);
        PublishDto publishDto=new PublishDto();
        BeanUtils.copyProperties(publish,publishDto);
        User user = userMapper.selectByPrimaryKey(publish.getUid());
        publishDto.setUser(user);
        return publishDto;
    }
}
