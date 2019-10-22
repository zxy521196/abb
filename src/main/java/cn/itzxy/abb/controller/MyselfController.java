package cn.itzxy.abb.controller;

import cn.itzxy.abb.dto.PageDto;
import cn.itzxy.abb.mapper.UserMapper;
import cn.itzxy.abb.model.User;
import cn.itzxy.abb.service.MyQuestionService;
import cn.itzxy.abb.service.PublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class MyselfController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MyQuestionService myQuestionService;
    @RequestMapping("/my/{title}")
    public String my(HttpServletRequest request,
                     @PathVariable("title") String title,
                     Model model,
                     @RequestParam(value = "page",defaultValue = "1") int page,
                     @RequestParam(value = "size",defaultValue = "4") int size){
        User user = (User) request.getSession().getAttribute("user");
        if(title.equals("questions")){
            PageDto pageDto = myQuestionService.publishDtoList(user.getId(),page, size);
            model.addAttribute("pageDto",pageDto);
            model.addAttribute("select","questions");
            model.addAttribute("title","我的问题");
        }
        if(title.equals("answers")){
            model.addAttribute("select","answers");
            model.addAttribute("title","我的回复");
        }
        if(title.equals("like")){
            model.addAttribute("select","like");
            model.addAttribute("title","我的关注");
        }
        return "myself";
    }
    @GetMapping("/loginout")
    public String loginOut(HttpServletRequest request,
                           HttpServletResponse response){
        request.getSession().removeAttribute("user");
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                cookie.setMaxAge(0);
                response.addCookie(cookie);
                break;
            }
        }
        return "redirect:/index";
    }
}
