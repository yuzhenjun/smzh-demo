/**
 * @title HttpRequestUtils.java
 * @package com.server.oauth2.utils
 * @projectName common.server.oauth2
 * @author yzj
 * @date 2015年4月1日 上午10:22:20
 */
package com.server.oauth2.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * http请求工具
 */
public class HttpRequestUtils {
	private static Logger logger = Logger.getLogger(HttpRequestUtils.class);

	
	/**
	 * 发送get请求
	 * @param url 接口url地址
	 * @return
	 */
	public static String requestGet(String url) {
		CloseableHttpClient client = HttpClients.createDefault();
		String result = "";
		// ================================httpget使用===================================
		HttpGet get = new HttpGet(url);
		CloseableHttpResponse response = null;
		try {
			response = client.execute(get);
			HttpEntity entity = response.getEntity();
			result = EntityUtils.toString(entity);
			EntityUtils.consume(entity);
		} catch (IOException e) {
			logger.error("发送get请求失败", e);
		} finally {
			HttpRequestUtils.close(client, response);
		}
		return result;
	}
	
	/**
	 * 一般请求方式<br>
	 * nvps.add(new BasicNameValuePair("limit", "20")); nvps.add(new
	 * BasicNameValuePair("offset", "20")); nvps.add(new
	 * BasicNameValuePair("arr","1"));数组形式 nvps.add(new
	 * BasicNameValuePair("arr","2")); nvps.add(new
	 * BasicNameValuePair("arr","qw"));
	 * 
	 * 发送post请求
	 * 
	 * @param url
	 *            请求接口url
	 * @param nvps
	 *            接口请求参数
	 * @return
	 */
	public static String requestPost(String url, List<NameValuePair> nvps) {
		String result = "";
		CloseableHttpClient client = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		HttpPost post = new HttpPost(url);
		try {
			post.setEntity(new UrlEncodedFormEntity(nvps));
			response = client.execute(post);
			HttpEntity entity = response.getEntity();
			result = EntityUtils.toString(entity);
		} catch (UnsupportedEncodingException e) {
			logger.error("不支持编码异常", e);
		} catch (ClientProtocolException e) {
			logger.error("post请求异常", e);
		} catch (IOException e) {
			logger.error("post请求异常", e);
		} finally {
			HttpRequestUtils.close(client, response);
		}
		return result;
	}

	/**
	 * 发送post请求传递json参数 
	 * example:
	 *  ObjectMapper objectMapper = new ObjectMapper();
	 *  Param  param=new Param(); 
	 *  param.setLimit(12);
	 *  param.setOffset(30);
	 *  String json=objectMapper.writeValueAsString(param);
	 * 
	 * @param url
	 *            接口url
	 * @param param
	 *            接口参数 可以使对象 Param param=new Param();
	 * @return
	 */
	public static String requestPostJson(String url, Object param) {
		String result = "";
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost(url);
		// 设置请求头
		post.addHeader("Content-Type", "application/json;charset=UTF-8");
		// 设置请求类型
		ContentType content = ContentType.create("application/json",Charset.defaultCharset());
		// 设置请求参数
		ObjectMapper objectMapper = new ObjectMapper();
		String json;
		CloseableHttpResponse response = null;
		;
		try {
			json = objectMapper.writeValueAsString(param);
			StringEntity params = new StringEntity(json, content);
			post.setEntity(params);
			response = client.execute(post);
			HttpEntity entity = response.getEntity();
			result = EntityUtils.toString(entity);
		} catch (JsonGenerationException e) {
			logger.error("json转换错误", e);
		} catch (JsonMappingException e) {
			logger.error("json转换错误", e);
		} catch (IOException e) {
			logger.error("json格式错误或请求异常", e);
		} finally {
			HttpRequestUtils.close(client, response);
		}
		return result;
	}
	
	
	
	/**
	 * 一般请求方式<br>
	 * nvps.add(new BasicNameValuePair("limit", "20")); nvps.add(new
	 * BasicNameValuePair("offset", "20")); nvps.add(new
	 * BasicNameValuePair("arr","1"));数组形式 nvps.add(new
	 * BasicNameValuePair("arr","2")); nvps.add(new
	 * BasicNameValuePair("arr","qw"));
	 * 
	 * 发送Put请求
	 * 
	 * @param url
	 *            请求接口url
	 * @param nvps
	 *            接口请求参数
	 * @return
	 */
	public static String requestPut(String url, List<NameValuePair> nvps) {
		String result = "";
		CloseableHttpClient client = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		HttpPut put = new HttpPut(url);
		try {
			put.setEntity(new UrlEncodedFormEntity(nvps));
			response = client.execute(put);
			HttpEntity entity = response.getEntity();
			result = EntityUtils.toString(entity);
		} catch (UnsupportedEncodingException e) {
			logger.error("不支持编码异常", e);
		} catch (ClientProtocolException e) {
			logger.error("put请求异常", e);
		} catch (IOException e) {
			logger.error("put请求异常", e);
		} finally {
			HttpRequestUtils.close(client, response);
		}
		return result;
	}

	
	
	
	
	/**
	 * 发送Put请求传递json参数 
	 * example:
	 *  ObjectMapper objectMapper = new ObjectMapper();
	 *  Param  param=new Param(); 
	 *  param.setLimit(12);
	 *  param.setOffset(30);
	 *  String json=objectMapper.writeValueAsString(param);
	 * 
	 * @param url 接口url
	 * @param param  接口参数 可以使对象 
	 * @return
	 */
	public static String requestPutJson(String url, Object param) {
		String result = "";
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPut put = new HttpPut(url);
		// 设置请求头
		put.addHeader("Content-Type", "application/json;charset=UTF-8");
		// 设置请求类型
		ContentType content = ContentType.create("application/json", Charset.defaultCharset());
		// 设置请求参数
		ObjectMapper objectMapper = new ObjectMapper();
		String json;
		CloseableHttpResponse response = null;
		;
		try {
			json = objectMapper.writeValueAsString(param);
			StringEntity params = new StringEntity(json, content);
			put.setEntity(params);
			response = client.execute(put);
			HttpEntity entity = response.getEntity();
			result = EntityUtils.toString(entity);
		} catch (JsonGenerationException e) {
			logger.error("json转换错误", e);
		} catch (JsonMappingException e) {
			logger.error("json转换错误", e);
		} catch (IOException e) {
			logger.error("json格式错误或请求异常", e);
		} finally {
			HttpRequestUtils.close(client, response);
		}
		return result;
	}
	
	/**
	 * 关闭client 和response对象
	 * 
	 * @param client
	 * @param response
	 */
	public static void close(CloseableHttpClient client,CloseableHttpResponse response) {
		if (response != null) {
			try {
				response.close();
			} catch (IOException e) {
				logger.error("关闭response失败", e);
			}
		}

		if (client != null) {
			try {
				client.close();
			} catch (IOException e) {
				logger.error("关闭client失败", e);
			}
		}
	}
}
