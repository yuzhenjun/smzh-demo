package com.smzh;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.smzh.beans.Param;
import com.smzh.beans.SearchResult;
import com.smzh.beans.TableBean;

public class QuickStart {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		CloseableHttpClient client = HttpClients.createDefault();
		//================================httpget使用===================================
		HttpGet get = new HttpGet("http://localhost:8080/common.web/test/get?limit=10&offset=10");
		CloseableHttpResponse response = null;
			response = client.execute(get);
			System.out.println(response.getStatusLine());
			HttpEntity entity = response.getEntity();
			String result=EntityUtils.toString(entity);
			System.out.println(result);
			SearchResult<TableBean> bean=JSON.parseObject(result,SearchResult.class);
			EntityUtils.consume(entity);
			if(response!=null)
			response.close();
				
		
		//================================httpPost使用===================================
			/**
			 * 传递一般参数
			 */
		HttpPost post=new HttpPost("http://localhost:8080/common.web/test/post");
		List<NameValuePair> nvps=new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("limit", "20"));
		nvps.add(new BasicNameValuePair("offset", "20"));
		post.setEntity(new UrlEncodedFormEntity(nvps));

		response=client.execute(post);
		entity = response.getEntity();
		result=EntityUtils.toString(entity);
		System.out.println(result);
		bean=JSON.parseObject(result,SearchResult.class);
		System.out.println(bean);
		if(response!=null)
			response.close();
		/**
		 * 传递json对象
		 */
		HttpPost postjson=new HttpPost("http://localhost:8080/common.web/test/post");
		JSONObject json = new JSONObject();
		json.put("limit", "10");
		json.put("offset", "30");
		StringEntity params = new StringEntity(json.toString());
		postjson.setEntity(params);
		response=client.execute(postjson);
		System.out.println(response.getStatusLine());
		entity = response.getEntity();
		result=EntityUtils.toString(entity);
		System.out.println(result);
		bean=JSON.parseObject(result,SearchResult.class);
		System.out.println(bean);
		if(response!=null)
			response.close();
		
	}

}
