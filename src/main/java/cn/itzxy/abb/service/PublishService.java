package cn.itzxy.abb.service;

import cn.itzxy.abb.dto.PageDto;
import cn.itzxy.abb.dto.PublishDto;
import cn.itzxy.abb.exception.ExceptionBean;
import cn.itzxy.abb.mapper.PublishMapper;
import cn.itzxy.abb.mapper.UserMapper;
import cn.itzxy.abb.model.Publish;
import cn.itzxy.abb.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PublishService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PublishMapper publishMapper;
    public PageDto publishDtoList(int page, int size){
        PageDto pageDto=new PageDto();
        List<PublishDto> publishDtoList=new ArrayList<>();
        pageDto.setPage(publishMapper.getTotalcount(),page,size);
        if(page>pageDto.getAllPage()){
            page=pageDto.getAllPage();
        }
        List<Publish> publishList = publishMapper.pageList(size*(page-1),size);
        for(Publish publish:publishList){
            User user = userMapper.selectByPrimaryKey(publish.getUid());
            PublishDto publishDto = new PublishDto();
            BeanUtils.copyProperties(publish,publishDto);
            publishDto.setUser(user);
            publishDtoList.add(publishDto);
        }
        pageDto.setPage(page);
        pageDto.setPublishDtoList(publishDtoList);
        pageDto.setAllCount(publishMapper.getTotalcount());
        return  pageDto;
    }

    public void creatOrUpdate(Publish publish, Integer id) {
        Publish publishById=null;
        if(id!=null) {
            publishById = publishMapper.findById(id);
            if(publishById==null){
                throw new ExceptionBean("对不起，你查找的问题不存在！！");
            }
        }
        if(publishById==null){
            publishMapper.insert(publish);
        }
        else{
            publishById.setTag(publish.getTag());
            publishById.setTitle(publish.getTitle());
            publishById.setBody(publish.getBody());
            publishById.setPublish_time(publish.getPublish_time());
            publishMapper.update(publishById);
        }
    }
}
