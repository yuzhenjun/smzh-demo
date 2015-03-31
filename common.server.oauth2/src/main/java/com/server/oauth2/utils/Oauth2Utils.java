/**
 * @title Oauth2Utils.java
 * @package com.server.oauth2.utils
 * @projectName common.server.oauth2
 * @author yzj
 * @date 2015年3月31日 下午4:03:23
 */
package com.server.oauth2.utils;

import java.nio.charset.Charset;

import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.utils.OAuthUtils;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class Oauth2Utils {
	/**
	 * 设置返回字符编码
	 * 
	 * @return
	 */
	public static HttpHeaders getHttpHeaders() {
		HttpHeaders headers = new HttpHeaders();
		MediaType mediaType = new MediaType("text", "html",Charset.forName("UTF-8"));
		headers.setContentType(mediaType);
		return headers;
	}
	
	/**
	 * 
	 * @param e
	 * @return
	 */
	public static ResponseEntity<String> getErrorsResponseEntity(OAuthProblemException e){
		HttpHeaders headers = Oauth2Utils.getHttpHeaders();
		JSONObject result=new JSONObject();
		 //检查是否设置了错误码
        String errorCode = e.getError();
        if (OAuthUtils.isEmpty(errorCode)) {
            result.put("error", "请求参数错误");
            return new ResponseEntity<String>(result.toString(),headers, HttpStatus.UNAUTHORIZED);
        }
        result.put("error","bad request");
        return new ResponseEntity<String>(result.toString(),headers,HttpStatus.BAD_REQUEST);
	}
	
	
	
}
