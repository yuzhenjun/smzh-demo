package com.smzh.https;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.smzh.post.RequestPost;
import com.smzh.utils.HttpUtils;

public class HttpsRequest {
	private static Logger logger = Logger.getLogger(RequestPost.class);

	public static CloseableHttpClient createSSLInsecureClient() {
		try {
			//忽略证书验证
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
						// 信任所有
						public boolean isTrusted(X509Certificate[] chain,
								String authType) throws CertificateException {
							return true;
						}
					}).build();
			//信任所有
	//SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, new String[] { "TLSv1" }, null,new AllowAllHostnameVerifier());
	SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, new String[] { "TLSv1" },
		                null,SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
			return HttpClients.custom().setSSLSocketFactory(sslsf).build();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		}
		return HttpClients.createDefault();
	}

	public static String executePost(String url, List<NameValuePair> nvps) {
		String result = "";
		CloseableHttpClient client = HttpsRequest.createSSLInsecureClient();
		CloseableHttpResponse response = null;
		HttpPost post = new HttpPost(url);
		try {
			post.setEntity(new UrlEncodedFormEntity(nvps));
			response = client.execute(post);
			HttpEntity entity = response.getEntity();
			result = EntityUtils.toString(entity);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			logger.error("不支持编码异常", e);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			logger.error("post请求异常", e);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("post请求异常", e);
		} finally {
			HttpUtils.close(client, response);
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
	public static String executePostJson(String url, Object param) {
		String result = "";
		CloseableHttpClient client = HttpsRequest.createSSLInsecureClient();
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
			logger.info("发送post请求url:"+url);
			logger.info("json 参数:"+json);
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
			HttpUtils.close(client, response);
		}
		logger.info("返回结果 result:"+result);
		return result;
	}
	

	public static String executeGet(String url) {
		CloseableHttpClient client =HttpsRequest.createSSLInsecureClient();
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
			HttpUtils.close(client, response);
		}
		return result;
	}

}
