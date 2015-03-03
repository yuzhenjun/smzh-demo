package com.smzh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("test")
public class TestController {
	 /** 
     * 测试Spring MVC 
     * @return 
     */  
    @RequestMapping(method = RequestMethod.GET)  
    public  ModelAndView testSpring()   
    {  
        return new ModelAndView ("/hello");  
    }  
}
