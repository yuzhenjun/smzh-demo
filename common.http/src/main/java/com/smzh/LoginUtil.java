package com.smzh;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class LoginUtil {
	public static CloseableHttpClient login(String url, String name, String pass) {
		CloseableHttpClient   client = HttpClients.createDefault();  
//		client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
//		client.getHttpConnectionManager().getParams().setSoTimeout(5000); 
//		client.getHttpConnectionManager().getParams().setConnectionTimeout(3000);
//		client.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
//			     new DefaultHttpMethodRetryHandler(0, false));
		//HttpPost putMethod = new HttpPost(url);
//		putMethod.setRequestHeader("Accept", "*/*");
//		putMethod.setRequestHeader("Accept-Encoding", "gzip, deflate");
//		putMethod.setRequestHeader("Accept-Language","zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
//		putMethod.setRequestHeader("Content-Type","application/json; charset=UTF-8");
//		putMethod.setRequestHeader("Accept-Encoding", "POST");
//		StringEntity myEntity = new StringEntity("important message", ContentType.create("text/plain", "UTF-8"));  
//		JSONObject json = new JSONObject();
//		json.put("loginName", "admin");
//		json.put("password", "1");
//		InputStream is = new ByteArrayInputStream(json.toString().getBytes());
//		RequestEntity re = new InputStreamRequestEntity(is);
//		putMethod.setRequestEntity(re);
//		try {
//			client.executeMethod(putMethod);
//			int status=putMethod.getStatusCode();
//			if(status==HttpStatus.SC_OK){
//				logger.info("系统登录成功");
//				System.out.println(putMethod.getResponseBodyAsString());  
//			}else{
//				logger.error("登录信息错误");
//			}
//		} catch (HttpException e) {
//			logger.error("系统登录异常"+url, e);
//			e.printStackTrace();
//		} catch (IOException e) {
//			logger.error("系统登录异常"+url, e);
//		}finally{
//			putMethod.releaseConnection();
//		}
		return client;
	}
}
