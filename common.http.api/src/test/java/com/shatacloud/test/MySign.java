/**
 * @title MySign.java
 * @package com.shatacloud.test
 * @projectName common.http.api
 * @author zhenjun.yu
 * @date 2015年4月16日 下午4:41:23
 */
package com.shatacloud.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author zhenjun.yu

 */
public class MySign {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
     String clientId="c1ebe466-1cdc-4bd3-ab69-77c3561b9dee";
     String clientSecret="d8346ea2-6017-43ed-ad68-19c0f971738b";
     String page="1-10";
     String state="-1";
     String spCode="cztv";
     
     List<String> list=new ArrayList<String>();
		list.add(clientId);
		list.add(clientSecret);
		list.add(page);
		list.add(state);
		list.add(spCode);
		Collections.sort(list);
		String signstr="";
		for(String param:list){
			System.out.println(param);
			signstr+=param;
		}
		System.out.println("sign="+DigestUtils.md5Hex(signstr));
	}

}
