package com.smzh.put;

import com.smzh.beans.Param;

public class RequestPutTest {
	public static void main(String[] args) {
		String url = "http://localhost:8080/common.web/test/put";
		Param param = new Param();
		param.setLimit(12);
		param.setOffset(30);
		String result = RequestPut.executeJson(url, param);
		System.out.println(result);
	}
}
