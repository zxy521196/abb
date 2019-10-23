package cn.itzxy.abb.controller;

import cn.itzxy.abb.exception.ExceptionBean;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CurrentErrorController {
    @ExceptionHandler(Exception.class)
    ModelAndView handleControllerException(Throwable ex,
                                           Model model) {
        if(ex instanceof ExceptionBean){
            model.addAttribute("message",ex.getMessage());
        }
        else{
            model.addAttribute("message","服务器冒烟了");
        }
        return new ModelAndView("error");
    }
}
