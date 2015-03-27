package com.smzh.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthBearerClientRequest;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthResourceResponse;
import org.apache.oltu.oauth2.common.OAuth;

import com.smzh.Constants;

public class UserInfoServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		String access_token = request.getParameter("access_token");
		OAuthResourceResponse oAuthResponse=extractUsername(access_token);
		System.out.println(oAuthResponse.getBody());
		PrintWriter out=response.getWriter();
		out.println(oAuthResponse.getBody());
		out.flush();
		out.close();
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	private OAuthResourceResponse extractUsername(String accessToken) {

		try {
			OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
			
			OAuthClientRequest userInfoRequest = new OAuthBearerClientRequest(Constants.userInfoUrl).setAccessToken(accessToken).buildQueryMessage();
			OAuthResourceResponse resourceResponse = oAuthClient.resource(userInfoRequest, OAuth.HttpMethod.GET,OAuthResourceResponse.class);
			return resourceResponse;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
