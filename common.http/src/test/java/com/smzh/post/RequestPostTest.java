package com.smzh.post;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.smzh.beans.Param;


public class RequestPostTest{
	public static void main(String[] args) {
		String url="http://localhost:8080/common.web/test/post";
		String urlJson="http://localhost:8080/common.web/test/postjson";
		
		List<NameValuePair> nvps=new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("limit", "20"));
		nvps.add(new BasicNameValuePair("offset", "20"));
		nvps.add(new BasicNameValuePair("arr","1"));
		nvps.add(new BasicNameValuePair("arr","2"));
		nvps.add(new BasicNameValuePair("arr","qw"));
		
		String result=RequestPost.execute(url, nvps);
		System.out.println(result);
		
		
		Param param=new Param();
		param.setLimit(12);
		param.setOffset(30);
		result=RequestPost.executeJson(urlJson, param);
		System.out.println(result);
	}

}
