package com.smzh.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.smzh.beans.Traffic;

@Controller
@RequestMapping("vm")
public class VelocityTest {
	 @RequestMapping(method = RequestMethod.GET)  
	public  void getXML(HttpServletRequest req, HttpServletResponse resp) throws ResourceNotFoundException, ParseErrorException, Exception{
		Random random=new Random();
		/*第一种获取vm模板的方式*/
//		Properties p = new Properties();
//		p.put("file.resource.loader.class","org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
//		String templatepath="vm/rtmptraffice.vm";
//		Velocity.init(p);
		
		/*第二种方式*/
		Properties p = new Properties();
	/*	path 不以’/'开头时默认是从此类所在的包下取资源，以’/'开头则是从
		ClassPath根下获取。其只是通过path构造一个绝对路径，最终还是由ClassLoader获取资源。 */
		p.load(this.getClass().getResourceAsStream("/conf/velocity.properties"));
		Velocity.init(p);
		
		String templatepath="vm/rtmptraffice.vm";
		Template template =Velocity.getTemplate(templatepath);
		VelocityContext context = new VelocityContext();
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
		template.merge(context, writer);
		writer.flush();
		writer.close();
	}
}
