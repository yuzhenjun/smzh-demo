/**
 * @title BandWidthServlet.java
 * @package com.smzh.servlet
 * @projectName common.http.api
 * @author zhenjun.yu
 * @date 2015年4月2日 下午5:15:16
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

import com.smzh.post.RequestPost;

/**
 * @author zhenjun.yu

 */
public class BandWidthServlet extends HttpServlet {
	public final static String urls="https://10.222.138.160:8443/openapi/oauth2/bandWidth/all";
	public final static String url="http://10.222.138.61:8080/openapi/oauth2/bandWidth/all";
	/** */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		/**
		 * 	
			district ""
			domain ""
			endRingTime ""
			endTime "2015-04-02 17:00:00"
			isp ""
			sp "-1"
			startTime "2015-04-01 17:00:00"
		 */
		response.setContentType("text/html;charset=UTF-8");
		String access_token = request.getParameter("access_token");
		List<NameValuePair> nvps=new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("startTime", "2015-04-06 15:00:00"));
		nvps.add(new BasicNameValuePair("endTime", 	"2015-04-07 15:00:00"));
		nvps.add(new BasicNameValuePair("sp", "-1"));
		nvps.add(new BasicNameValuePair("access_token", access_token));
		nvps.add(new BasicNameValuePair("domain", "12121121"));
		//getUrl.append("?access_token=").append(access_token).append("&startTime=2015-04-06 15:00:00&endTime=2015-04-07 15:00:00&sp=-1")
		//String result=HttpsRequest.executePost(urls, nvps);
		String result=RequestPost.execute(url, nvps);
		//String result=HttpsRequest.executePost(url, nvps);
		//String result=HttpsRequest.executeGet(getUrl.toString());
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
