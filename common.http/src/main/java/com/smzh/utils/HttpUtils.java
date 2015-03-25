package com.smzh.utils;

import java.io.IOException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.log4j.Logger;

public class HttpUtils {
	private static Logger logger = Logger.getLogger(HttpUtils.class);

	/**
	 * 关闭client 和response对象
	 * 
	 * @param client
	 * @param response
	 */
	public static void close(CloseableHttpClient client,
			CloseableHttpResponse response) {
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
