package com.smzh.get;


public class RequestGetTest{
	public static void main(String[] args) {
		String url="http://localhost:8080/common.web/test/get?limit=10&offset=10";
		String result=RequestGet.execute(url);
		System.out.println(result);
	}

}
