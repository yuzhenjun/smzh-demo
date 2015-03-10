package com.smzh.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("demo1")
public class ViewResolverTestController {
	private static Log logger = LogFactory.getLog(ViewResolverTestController.class);
		@RequestMapping(value = "/tojsp",method = RequestMethod.GET)
	    public String test1(HttpServletRequest request, ModelMap map) {
	        logger.info("使用JSP视图解析器");
	        return "/main";
	    }
	    @RequestMapping(value ="/toftl" ,method = RequestMethod.GET)
	    public String test2(HttpServletRequest request, ModelMap map) {
	        logger.info("使用Freemarker视图解析器");
	        return "/hello.ftl";
	    }
	    
	    @RequestMapping(value = "/tovm", method = RequestMethod.GET)
	    public String test3(HttpServletRequest request, ModelMap map) {
	        logger.info("使用Velocity视图解析器");
	        return "/hello.vm";
	    }
}
