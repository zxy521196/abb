package cn.itzxy.abb.controller;

import cn.itzxy.abb.dto.PublishDto;
import cn.itzxy.abb.service.MyQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class QuestionController {
    @Autowired
    private MyQuestionService myQuestionService;
    @RequestMapping("/question/{id}")
    public String questions(@PathVariable("id") int id,
                            Model model){
        PublishDto publishDto = myQuestionService.findByid(id);
        model.addAttribute("publishDto",publishDto);
        return "question";
    }
}
