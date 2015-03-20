package com.smzh;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;



public class HttpClientTest {

	public static void main(String[] args) {
		CloseableHttpClient client = HttpClients.createDefault();  
		HttpGet get=new HttpGet("http://www.baidu.com");
		try {
			CloseableHttpResponse response=client.execute(get);
			System.out.println(response.getStatusLine().getStatusCode());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
