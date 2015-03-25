package com.smzh;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.smzh.beans.Param;
import com.smzh.beans.SearchResult;
import com.smzh.beans.TableBean;

public class QuickStart {
	/**
	 * httpclient实现登录
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	
	
	public static CloseableHttpClient login(String loginUrl,String userName,String password) throws ClientProtocolException, IOException{
		BasicCookieStore cookieStore = new BasicCookieStore();
		/**
		 * 在用户登录成功是可以保存用户的cookies
		 */
		CloseableHttpClient client = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
		HttpPut put = new HttpPut(loginUrl);
		//设置连接信息,连接时长
		RequestConfig config = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).build();
		put.setConfig(config);
		//设置请求类型
		ContentType content=ContentType.create("application/json",Charset.defaultCharset());
		
		//设置用户名密码
		JSONObject json = new JSONObject();
		json.put("loginName", userName);
		json.put("password", password);
		StringEntity params = new StringEntity(json.toString(),content);
		put.setEntity(params);
		CloseableHttpResponse response = client.execute(put);
		System.out.println(response.getStatusLine().getStatusCode());
		for (Cookie cookie : cookieStore.getCookies()){
			System.out.println(cookie);
		}
		BasicClientCookie cookie1 = new BasicClientCookie("loginName", "admin");
		cookie1.setExpiryDate(new Date(new Date().getTime()+60*60*1000*24));//24小时
		BasicClientCookie cookie2 = new BasicClientCookie("password", "1");
		cookie2.setExpiryDate(new Date(new Date().getTime()+60*24*60*60*1000*24*10));
		cookieStore.addCookie(cookie1);
		cookieStore.addCookie(cookie2);
		for (Cookie cookie : cookieStore.getCookies()){
			System.out.println(cookie);
		}
		return client;
	}
	
	/**
	 * 模拟登录portal
	 * 并发送post请求
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static void imitateLogin() throws ClientProtocolException, IOException{
		CloseableHttpClient client =QuickStart.login("http://10.222.138.160:8080/webcache.portal/rest/login","admin","1");
	    HttpPost put = new HttpPost("http://10.222.138.160:8080/webcache.portal/rest/bandwidth2/all");
	    ContentType content=ContentType.create("application/json",Charset.defaultCharset());
	    JSONObject json = new JSONObject();
		json.put("district", "");
		json.put("endTime", "2015-03-20 14:00:00");
		json.put("isp", "");
		json.put("sp", "-1");
		json.put("startTime", "2015-03-19 14:00:00");
		StringEntity params = new StringEntity(json.toString(),content);
		put.setEntity(params);
		CloseableHttpResponse response = client.execute(put);
		String result=EntityUtils.toString(response.getEntity());
		System.out.println(result);
	}
	/**
	 * 使用httpclient4发送get请求
	 */
	@SuppressWarnings("unchecked")
	public static void requestGet()  throws IOException{
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
			System.out.println(bean);
			EntityUtils.consume(entity);
			if(response!=null)
			response.close();
			client.close();
	}

	
	/**
	 * 使用httpcilent发送post请求
	 * 
	 * 传递一般参数
	 */
	@SuppressWarnings("unchecked")
	public static void requestPost() throws IOException{
		
		CloseableHttpClient client = HttpClients.createDefault();
		// 创建post对象
		HttpPost post=new HttpPost("http://localhost:8080/common.web/test/post");
		//设置参数 
		List<NameValuePair> nvps=new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("limit", "20"));
		nvps.add(new BasicNameValuePair("offset", "20"));
		nvps.add(new BasicNameValuePair("arr","1"));
		nvps.add(new BasicNameValuePair("arr","2"));
		nvps.add(new BasicNameValuePair("arr","qw"));
		post.setEntity(new UrlEncodedFormEntity(nvps));

		CloseableHttpResponse response =client.execute(post);
		HttpEntity entity  = response.getEntity();
		String result=EntityUtils.toString(entity);
		System.out.println(result);
		SearchResult<TableBean> bean=JSON.parseObject(result,SearchResult.class);
		System.out.println(bean);
		if(response!=null)
			response.close();
		client.close();
	}
	
	@SuppressWarnings("unchecked")
	public static void requestPostJson()throws IOException{
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost postjson=new HttpPost("http://localhost:8080/common.web/test/postjson");
		//设置请求头
		postjson.addHeader("Content-Type","application/json;charset=UTF-8");
		//设置请求类型
		ContentType content=ContentType.create("application/json",Charset.defaultCharset());
		//设置请求参数
		ObjectMapper objectMapper = new ObjectMapper();
		Param param=new Param();
		param.setLimit(12);
		param.setOffset(30);
		String json=objectMapper.writeValueAsString(param);
		StringEntity params = new StringEntity(json,content);
		postjson.setEntity(params);
		
		//发送请求
		CloseableHttpResponse response=client.execute(postjson);
		System.out.println(response.getStatusLine());
		HttpEntity entity = response.getEntity();
		String result=EntityUtils.toString(entity);
		System.out.println(result);
		SearchResult<TableBean>  bean=JSON.parseObject(result,SearchResult.class);
		System.out.println(bean);
		if(response!=null)
			response.close();
		client.close();
	}
	
	/**
	 * 发送put请求
	 */
	@SuppressWarnings("unchecked")
	public static void requestPut()throws IOException{
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPut put=new HttpPut("http://localhost:8080/common.web/test/put");
		//设置请求头
		put.addHeader("Content-Type","application/json;charset=UTF-8");
		//设置请求类型
		ContentType content=ContentType.create("application/json",Charset.defaultCharset());
		//设置请求参数
		ObjectMapper objectMapper = new ObjectMapper();
		Param param=new Param();
		param.setLimit(12);
		param.setOffset(30);
		String json=objectMapper.writeValueAsString(param);
		StringEntity params = new StringEntity(json,content);
		put.setEntity(params);
		
		//发送请求
		CloseableHttpResponse response=client.execute(put);
		System.out.println(response.getStatusLine());
		HttpEntity entity = response.getEntity();
		String result=EntityUtils.toString(entity);
		System.out.println(result);
		SearchResult<TableBean>  bean=JSON.parseObject(result,SearchResult.class);
		System.out.println(bean);
		if(response!=null)
			response.close();
		client.close();
	}
	
	
	@SuppressWarnings("unchecked")
	public static void requestdelete()throws IOException{
		CloseableHttpClient client = HttpClients.createDefault();
		HttpDelete delete=new HttpDelete("http://localhost:8080/common.web/test/delete?limit=10&offset=10");
		
		//发送请求
		CloseableHttpResponse response=client.execute(delete);
		System.out.println(response.getStatusLine());
		HttpEntity entity = response.getEntity();
		String result=EntityUtils.toString(entity);
		System.out.println(result);
		SearchResult<TableBean>  bean=JSON.parseObject(result,SearchResult.class);
		System.out.println(bean);
		if(response!=null)
			response.close();
		client.close();
	}
	
	public static void main(String[] args) throws IOException {	
   
	
	//QuickStart.requestGet();
	//QuickStart.requestPost();
	//QuickStart.requestPostJson();
	//QuickStart.requestPost();
	  QuickStart.requestdelete();
		
		
		
		
	}

}
