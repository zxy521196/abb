package cn.itzxy.abb.controller;

import cn.itzxy.abb.exception.ExceptionBean;
import cn.itzxy.abb.mapper.PublishMapper;
import cn.itzxy.abb.mapper.UserMapper;
import cn.itzxy.abb.model.Publish;
import cn.itzxy.abb.model.User;
import cn.itzxy.abb.service.PublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
    @Autowired
    private PublishMapper publishMapper;
    @Autowired
    private Publish publish;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PublishService publishService;
    @GetMapping("/publish/{id}")
    public String publish(@PathVariable("id") int id,
                          Model model){
        Publish publish = publishMapper.findById(id);
        if(publish==null){
            throw new ExceptionBean("对不起，你查找的问题不存在！");
        }
        model.addAttribute("title", publish.getTitle());
        model.addAttribute("body",publish.getBody());
        model.addAttribute("tag",publish.getTag());
        model.addAttribute("id",id);
        return "publish";
    }

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }
    @PostMapping("/publish")
    public String doPublish(@RequestParam(value = "title",required = false) String title,
                            @RequestParam(value = "body",required = false) String body,
                            @RequestParam(value = "tag",required = false) String tag,
                            @RequestParam(value = "id",required = false) Integer id,
                            HttpServletRequest request,
                            Model model){
        if(title==null || title==""){
            model.addAttribute("error","问题标题不能为空");
            return "publish";
        }
        if(body==null || body==""){
            model.addAttribute("error","问题内容不能为空");
            return "publish";
        }
        if(tag==null || tag==""){
            model.addAttribute("error","问题标签不能为空");
            return "publish";
        }
        model.addAttribute("title",title);
        model.addAttribute("body",body);
        model.addAttribute("tag",tag);
        User user = (User) request.getSession().getAttribute("user");

        if(user!=null) {
            publish.setTitle(title);
            publish.setBody(body);
            publish.setTag(tag);
            publish.setPublish_time(System.currentTimeMillis());
            publish.setUid(user.getId());
            //publishMapper.insert(publish);
            publishService.creatOrUpdate(publish,id);
            return "redirect:/index";
        }
        else{
            model.addAttribute("error","你还没有登录，登录后才能发布问题！");
            return "publish";
        }
    }
}
