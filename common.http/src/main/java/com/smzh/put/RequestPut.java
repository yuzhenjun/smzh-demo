package com.smzh.put;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
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

import com.smzh.utils.HttpUtils;
/**
 *
 * @author zhenjun
 *
 */
public class RequestPut {
	private static Logger logger = Logger.getLogger(RequestPut.class);
	
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
	public static String executeJson(String url, Object param) {
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
			HttpUtils.close(client, response);
		}
		return result;
	}
}
