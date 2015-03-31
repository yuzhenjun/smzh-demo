/**
 * @title Oauth2Controller.java
 * @package com.server.oauth2.controller.oauth
 * @projectName common.server.oauth2
 * @author yzj
 * @date 2015年3月31日 下午4:33:26
 */
package com.server.oauth2.controller.oauth;

import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuer;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.as.request.OAuthAuthzRequest;
import org.apache.oltu.oauth2.as.request.OAuthTokenRequest;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.apache.oltu.oauth2.common.message.types.ParameterStyle;
import org.apache.oltu.oauth2.common.message.types.ResponseType;
import org.apache.oltu.oauth2.common.utils.OAuthUtils;
import org.apache.oltu.oauth2.rs.request.OAuthAccessResourceRequest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.server.oauth2.service.impl.ClientService;
import com.server.oauth2.service.impl.OAuthService;
import com.server.oauth2.service.impl.UserService;
import com.server.oauth2.utils.Oauth2Utils;

@Controller
@RequestMapping("oauth2")
public class Oauth2Controller {
	@Autowired
	private OAuthService oAuthService;
	@Autowired
	private ClientService clientService;
	@Autowired
	private UserService userService;
	
	
	 @RequestMapping("authorize")
	 public Object authorize(Model model,HttpServletRequest request)throws URISyntaxException, OAuthSystemException {
	    	 HttpHeaders headers = Oauth2Utils.getHttpHeaders();
	    	 JSONObject result=new JSONObject();
	        try {
	            //构建OAuth 授权请求
	            OAuthAuthzRequest oauthRequest = new OAuthAuthzRequest(request);
	            //检查传入的客户端id是否正确
	            if (!oAuthService.checkClientId(oauthRequest.getClientId())) {
	            		result.put("error", "invalid client_id");
	                return new ResponseEntity<Object>(result.toString(), headers,HttpStatus.BAD_REQUEST);
	            }
	            Subject subject = SecurityUtils.getSubject();
	            //如果用户没有登录，跳转到登陆页面
	            if(!subject.isAuthenticated()) {
	                if(!login(subject, request)) {//登录失败时跳转到登陆页面
	                    return "/oauth2login";
	                }
	            }

	            String username = (String)subject.getPrincipal();
	            //生成授权码
	            String authorizationCode = null;
	            //responseType目前仅支持CODE，另外还有TOKEN
	            String responseType = oauthRequest.getParam(OAuth.OAUTH_RESPONSE_TYPE);
	            if (responseType.equals(ResponseType.CODE.toString())) {
	                OAuthIssuerImpl oauthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());
	                authorizationCode = oauthIssuerImpl.authorizationCode();
	                oAuthService.addAuthCode(authorizationCode, username);
	            }

	            //进行OAuth响应构建
	            OAuthASResponse.OAuthAuthorizationResponseBuilder builder =OAuthASResponse.authorizationResponse(request, HttpServletResponse.SC_FOUND);
	            //设置授权码
	            builder.setCode(authorizationCode);
	            //得到到客户端重定向地址
	            String redirectURI = oauthRequest.getParam(OAuth.OAUTH_REDIRECT_URI);

	            //构建响应
	            final OAuthResponse response = builder.location(redirectURI).buildQueryMessage();

	            //根据OAuthResponse返回ResponseEntity响应
	            headers.setLocation(new URI(response.getLocationUri()));
	            return new ResponseEntity<Object>(headers, HttpStatus.valueOf(response.getResponseStatus()));
	        } catch (OAuthProblemException e) {
	            //出错处理
	            String redirectUri = e.getRedirectUri();
	            if (OAuthUtils.isEmpty(redirectUri)) {
	                //告诉客户端没有传入redirectUri直接报错
	            	result.put("error", "OAuth callback url needs to be provided by client!!!");
	                return new ResponseEntity<Object>(result.toString(), HttpStatus.NOT_FOUND);
	            }

	            //返回错误消息（如?error=）
	            final OAuthResponse response = OAuthASResponse.errorResponse(HttpServletResponse.SC_FOUND).error(e).location(redirectUri).buildQueryMessage();
	            headers.setLocation(new URI(response.getLocationUri()));
	            return new ResponseEntity<Object>(headers, HttpStatus.valueOf(response.getResponseStatus()));
	        }
	    }

	
	 @RequestMapping(value = "accessToken", method = RequestMethod.POST)
	public HttpEntity<String> token(HttpServletRequest request)throws URISyntaxException, OAuthSystemException {
			HttpHeaders headers = Oauth2Utils.getHttpHeaders();
			JSONObject result=new JSONObject();
			try {
				// 构建OAuth请求
				OAuthTokenRequest oauthRequest = new OAuthTokenRequest(request);

				// 检查提交的客户端id是否正确
				if (!oAuthService.checkClientId(oauthRequest.getClientId())) {
					result.put("error", "invalid client_id");
					return new ResponseEntity<String>(result.toString(),headers,HttpStatus.BAD_REQUEST);
				}

				// 检查客户端安全KEY是否正确
				if (!oAuthService.checkClientSecret(oauthRequest.getClientSecret())) {
					result.put("error", "invalid client_secret");
					return new ResponseEntity<String>(result.toString(),headers,HttpStatus.UNAUTHORIZED);
				}

				String authCode = oauthRequest.getParam(OAuth.OAUTH_CODE);
				// 检查验证类型，此处只检查AUTHORIZATION_CODE类型，其他的还有PASSWORD或REFRESH_TOKEN
				if (oauthRequest.getParam(OAuth.OAUTH_GRANT_TYPE).equals(GrantType.AUTHORIZATION_CODE.toString())) {
					if (!oAuthService.checkAuthCode(authCode)) {
						result.put("error", "invalid code");
						return new ResponseEntity<String>(result.toString(),headers,HttpStatus.BAD_REQUEST);
					}
				}

				// 生成Access Token
				OAuthIssuer oauthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());
				final String accessToken = oauthIssuerImpl.accessToken();
				oAuthService.addAccessToken(accessToken,oAuthService.getUsernameByAuthCode(authCode));// 将access_token和username放入缓存

				// 生成OAuth响应
				OAuthResponse response = OAuthASResponse.tokenResponse(HttpServletResponse.SC_OK)
						.setAccessToken(accessToken).setExpiresIn(String.valueOf(oAuthService.getExpireIn()))
						.buildJSONMessage();
				// 根据OAuthResponse生成ResponseEntity
				return new ResponseEntity<String>(response.getBody(),headers,HttpStatus.valueOf(response.getResponseStatus()));

			} catch (OAuthProblemException e) {
				// 构建错误响应
				OAuthResponse res = OAuthASResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST).error(e).buildJSONMessage();
				return new ResponseEntity<String>(res.getBody(),headers,HttpStatus.valueOf(res.getResponseStatus()));
			}
		}
	 
	 
	    @RequestMapping("/tokenInfo")
	    public HttpEntity<String> userInfo(HttpServletRequest request) throws OAuthSystemException {
	    	 HttpHeaders headers = Oauth2Utils.getHttpHeaders();
	         JSONObject result=new JSONObject();
	        try {
	            //构建OAuth资源请求
	            OAuthAccessResourceRequest oauthRequest = new OAuthAccessResourceRequest(request, ParameterStyle.QUERY);
	            //获取Access Token
	            String accessToken = oauthRequest.getAccessToken();
	            //验证Access Token
	            if (!oAuthService.checkAccessToken(accessToken)) {
	                // 如果不存在/过期了，返回未验证错误，需重新验证
	                result.put("error", "accessToken has expired ");
	                return new ResponseEntity<String>(result.toString(),headers,HttpStatus.UNAUTHORIZED);
	            }
	            //返回用户名
	            String username = oAuthService.getUsernameByAccessToken(accessToken);
	            result.put("username", username);
	            return new ResponseEntity<String>(result.toString(),headers,HttpStatus.OK);
	        } catch (OAuthProblemException e) {
	            return Oauth2Utils.getErrorsResponseEntity(e);
	        }
	    }
	
	
	
    /**
     * 登录验证
     * @param subject
     * @param request
     * @return
     */
    private boolean login(Subject subject, HttpServletRequest request) {
        if("get".equalsIgnoreCase(request.getMethod())) {
            return false;
        }
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return false;
        }
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
            return true;
        } catch (Exception e) {
            request.setAttribute("error", "登录失败:用户名或密码错误!");
            return false;
        }
    }
}
