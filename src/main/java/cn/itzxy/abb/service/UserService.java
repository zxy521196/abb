package cn.itzxy.abb.service;

import cn.itzxy.abb.mapper.UserMapper;
import cn.itzxy.abb.model.User;
import cn.itzxy.abb.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    public void createUser(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccountIdEqualTo(user.getAccountId());
        List<User> users = userMapper.selectByExample(userExample);
        if(users.size()==0){
            userMapper.insert(user);
        }
        else{
            User user1=new User();
            user1.setToken(user.getToken());
            user1.setBio(user.getBio());
            user1.setName(user.getName());
            user1.setAvatarUrl(user.getAvatarUrl());
            user1.setAccountId(user.getAccountId());
            UserExample example = new UserExample();
            example.createCriteria().andIdEqualTo(users.get(0).getId());
            userMapper.updateByExampleSelective(user1,example);
        }
    }
}
