package cn.itzxy.abb.controller;

import cn.itzxy.abb.dto.PageDto;
import cn.itzxy.abb.dto.PublishDto;
import cn.itzxy.abb.mapper.PublishMapper;
import cn.itzxy.abb.mapper.UserMapper;
import cn.itzxy.abb.model.Publish;
import cn.itzxy.abb.model.User;
import cn.itzxy.abb.service.PublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PublishService publishService;
    @RequestMapping(value="/index")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(value = "page",defaultValue = "1") int page,
                        @RequestParam(value = "size",defaultValue = "7") int size){
        PageDto pageDto = publishService.publishDtoList(page, size);
        model.addAttribute("pageDto",pageDto);
        return "index";
    }
}
