/**
 * @title AccessTokenHttpsServlet.java
 * @package com.smzh.servlet
 * @projectName common.http.api
 * @author zhenjun.yu
 * @date 2015年4月14日 下午3:17:13
 */
package com.smzh.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.smzh.Constants;
import com.smzh.https.HttpsRequest;

/**
 * @author zhenjun.yu

 */
public class AccessTokenHttpsServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json;charset=UTF-8");
		String code = request.getParameter("code");
		List<NameValuePair> nvps=new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("client_id",Constants.clientId));
		nvps.add(new BasicNameValuePair("client_secret",Constants.clientSecret));
		nvps.add(new BasicNameValuePair("grant_type","authorization_code"));
		nvps.add(new BasicNameValuePair("code",code));
		nvps.add(new BasicNameValuePair("redirect_uri",Constants.redirectUrl));
		String result=HttpsRequest.executePost(Constants.accessTokenUrls, nvps);
		
		PrintWriter out=response.getWriter();
		out.println(result);
		out.flush();
		out.close();
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}