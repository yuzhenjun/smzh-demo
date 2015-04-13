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

public class CacheServlet extends HttpServlet {
	public final static String url="http://10.222.138.160:8080/openapi/oauth2/cdnCache";
	/** */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String access_token = request.getParameter("access_token");
		List<NameValuePair> nvps=new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("access_token", access_token));
		nvps.add(new BasicNameValuePair("urls", "1212121212"));
		String result=RequestPost.execute(url, nvps);
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
