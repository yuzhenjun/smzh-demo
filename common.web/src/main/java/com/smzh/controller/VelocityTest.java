package com.smzh.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.stereotype.Controller;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.smzh.beans.Traffic;

@Controller
@RequestMapping("vm")
public class VelocityTest {
	
	private VelocityEngine velocityEngine;
	
	 @RequestMapping(method = RequestMethod.GET)  
	public  void getXML(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		Random random=new Random();
		/*
		 * 第一种获取vm模板的方式
			Properties p = new Properties();
			p.put("file.resource.loader.class","org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
			String templatepath="vm/rtmptraffice.vm";
			Velocity.init(p);
			
			第二种方式
			Properties p = new Properties();
			path 不以’/'开头时默认是从此类所在的包下取资源，以’/'开头则是从
			ClassPath根下获取。其只是通过path构造一个绝对路径，最终还是由ClassLoader获取资源。
			需要在mvc-config中配置velocity.properties属性
			p.load(this.getClass().getResourceAsStream("/conf/velocity.properties"));
			Velocity.init(p);
			String templatepath="vm/rtmptraffice.vm";
			Template template =Velocity.getTemplate(templatepath);
			VelocityContext context = new VelocityContext();
			context.put("date", new Date());
			context.put("name","zhenjun");
			
			template.merge(context, writer);
		*/
		
		/**
		 * 第三种方式
		 */
		Map<String, Object> context=new HashMap<String, Object>();
		String xml = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "rtmptraffice.vm","UTF-8", context);
		context.put("date", new Date());
		context.put("name","zhenjun");
		List<Traffic> list=new ArrayList<Traffic>();
		for(int i=0;i<10;i++){
			Traffic traffic=new Traffic();
			traffic.setTime(new Date().toString());
			traffic.setTraffice(random.nextDouble());
			list.add(traffic);
		}
		resp.setContentType("text/xml");
		context.put("result", list);
		PrintWriter writer =  resp.getWriter();
		writer.print(xml);
		writer.flush();
		writer.close();
	}
	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}
	 
	 
}
