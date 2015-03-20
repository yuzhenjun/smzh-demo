package com.smzh;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class HttpPostUtil {
	private static BasicCookieStore cookieStore = new BasicCookieStore();

	private Cookie cookie;
	public static JSONObject test() {
		JSONObject result = new JSONObject();
		// BasicCookieStore cookieStore = new BasicCookieStore();
		CloseableHttpClient client = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
		// CloseableHttpClient client = HttpClients.createDefault();
		HttpPut post = new HttpPut("http://10.222.138.61:8080/spportal/rest/login");
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).build();// 设置请求和传输超时时间
		post.setConfig(requestConfig);
		JSONObject json = new JSONObject();
		json.put("loginName", "admin");
		json.put("password", "1");
		try {
			StringEntity params = new StringEntity(json.toString());// 传json参数
			post.setEntity(params);
			CloseableHttpResponse response = client.execute(post);
			System.out.println(response.getStatusLine().getStatusCode());
			if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
				for (Cookie cookie : cookieStore.getCookies()){
					System.out.println(cookie);
				}
			}
			
				
			response.close();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	private static void get() {
		JSONObject json = new JSONObject();
		json.put("timeType", "2014-12-01 00:00:00~2014-12-02 09:36:00");
		//cookieStore.clear();
		CloseableHttpClient client = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).build();// 设置请求和传输超时时间
		HttpPost post = new HttpPost("http://10.222.138.61:8080/spportal/rest/dns");
		post.setConfig(requestConfig);
		StringEntity params;
		try {
			params = new StringEntity(json.toString());
			post.setEntity(params);
			CloseableHttpResponse response = client.execute(post);
			System.out.println(response.getStatusLine().getStatusCode());
			HttpEntity entity = response.getEntity();
//			result = (JSONObject) JSONObject.stringToValue(EntityUtils
//					.toString(entity));
			System.out.println(EntityUtils.toString(entity));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// 传json参数
		catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		HttpPostUtil.test();
		HttpPostUtil.get();
	}
}
