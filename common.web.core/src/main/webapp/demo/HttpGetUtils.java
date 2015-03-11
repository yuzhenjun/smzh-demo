/**
 * @title HttpGet.java
 * @package com.sihuatech.http
 * @projectName spportal.tools
 * @author zhenjun.yu
 * @date 2014年11月28日 下午5:10:58
 */
package com.sihuatech.http;

import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


/**
 * @author zhenjun.yu

 */
public class HttpGetUtils {

	public static void test(){
		CloseableHttpClient client = HttpClients.createDefault();  
		HttpGet get=new HttpGet("http://www.baidu.com");
		System.out.println(get.getURI());
		
		try {
			CloseableHttpResponse response=client.execute(get);
			System.out.println(response.getProtocolVersion());  
			System.out.println(response.getStatusLine().getStatusCode());  
			System.out.println(response.getStatusLine().getReasonPhrase());  
			System.out.println(response.getStatusLine().toString());
			for(Header header:response.getAllHeaders())
		    System.out.println(header);//得到响应头信息
			
			
			StringEntity myEntity = new StringEntity("important message",ContentType.create("text/plain", "UTF-8"));  
			System.out.println(myEntity.getContentType());  
			System.out.println(myEntity.getContentLength());  
			System.out.println(EntityUtils.toString(myEntity));  
			System.out.println(EntityUtils.toByteArray(myEntity).length);  
			
			HttpEntity entity=response.getEntity();
			System.out.println(entity.getContentType());
			System.out.println(entity.getContentLength());
			//System.out.println(EntityUtils.toString(entity));//得到返回文本内容
			response.close();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			get.releaseConnection();
			try {
				client.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		    
	}
	
	public static void main(String[] args) {
		HttpGetUtils.test();
	}
}
