package com.smzh.get;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.smzh.utils.HttpUtils;

public class RequestGet {
	private static Logger logger = Logger.getLogger(RequestGet.class);

	/**
	 * 
	 * @param url 接口url地址
	 * @return
	 */
	public static String execute(String url) {
		CloseableHttpClient client = HttpClients.createDefault();
		String result = "";
		// ================================httpget使用===================================
		HttpGet get = new HttpGet(url);
		get.addHeader("Content-Type", "application/x-www-form-urlencoded");
		CloseableHttpResponse response = null;
		try {
			response = client.execute(get);
			HttpEntity entity = response.getEntity();
			result = EntityUtils.toString(entity);
			EntityUtils.consume(entity);
		} catch (IOException e) {
			logger.error("发送get请求失败", e);
		} finally {
			HttpUtils.close(client, response);
		}
		return result;

	}
}
