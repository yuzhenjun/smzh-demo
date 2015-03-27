package com.smzh.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthAccessTokenResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.message.types.GrantType;

import com.smzh.Constants;

/**
 * 获取code
 * 根据code获取access_token
 * @author zhenjun
 *
 */
public class AccessTokenServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json;charset=UTF-8");
		String code = request.getParameter("code");
		OAuthAccessTokenResponse oAuthResponse=extractUsername(code);
		
		PrintWriter out=response.getWriter();
		out.println(oAuthResponse.getBody());
		out.flush();
		out.close();
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	private OAuthAccessTokenResponse extractUsername(String code) {

		try {
			OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());

			// 根据code请求accessToken
			OAuthClientRequest accessTokenRequest = OAuthClientRequest.tokenLocation(Constants.accessTokenUrl)
					.setGrantType(GrantType.AUTHORIZATION_CODE).setClientId(Constants.clientId)
					.setClientSecret(Constants.clientSecret).setCode(code)
					.setRedirectURI(Constants.redirectUrl).buildQueryMessage();

			OAuthAccessTokenResponse oAuthResponse = oAuthClient.accessToken(accessTokenRequest, OAuth.HttpMethod.POST);

			String accessToken = oAuthResponse.getAccessToken();
			Long expiresIn = oAuthResponse.getExpiresIn();
			return oAuthResponse;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
