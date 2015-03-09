package com.smzh.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smzh.beans.Traffic;

@Controller
@RequestMapping("vm")
public class VelocityTest {
	 @RequestMapping(method = RequestMethod.GET)  
	public @ResponseBody String getXML(HttpServletRequest req,
			HttpServletResponse resp) throws ResourceNotFoundException, ParseErrorException, Exception{
		Random random=new Random();
		VelocityEngine  vm = new VelocityEngine();
		VelocityContext context = new VelocityContext();
		context.put("date", new Date());
		context.put("name","zhenjun");
		String path=req.getContextPath();
		List<Traffic> list=new ArrayList<Traffic>();
		for(int i=0;i<10;i++){
			Traffic traffic=new Traffic();
			traffic.setTime(new Date().toString());
			traffic.setTraffice(random.nextDouble());
		}
		context.put("result", list);
		String templatepath="vm/rtmptraffice.vm";
		String xmlpath="E:/vm/traffic.xml";
		File xml= new File(xmlpath);
		Template template;
			template = vm.getTemplate(templatepath, "UTF-8");
		
		if(xml.exists()){
			xml.delete();
		}else{
			xml.getParentFile().mkdirs();
		}
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(xml);
		} catch (FileNotFoundException e) {
			
		}
		template.merge(context, writer);
		writer.close();
		return path;
	}
}
