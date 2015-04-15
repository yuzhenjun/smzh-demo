package com.smzh;

/**
 * ���app_key,app_secret
 * 
 * @author zhenjun
 *
 */
public class Constants {
	private static String ips="javagoo.tk";
	private static String ip="10.222.138.61";

	public final static String clientId = "c1ebe466-1cdc-4bd3-ab69-77c3561b9dee";

	public final static String clientSecret = "d8346ea2-6017-43ed-ad68-19c0f971738b";
	
	
	public final static String accessTokenUrls = "https://"+ips+":8443/openapi/oauth2/accessToken";
	
	public final static String accessTokenUrl = "http://"+ip+":8080/openapi/oauth2/accessToken";

	public final static String redirectUrl = "http://"+ip+":8080/testapi/accessTokenServlet";

	 
}
