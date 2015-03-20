package com.smzh.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.smzh.beans.Param;
import com.smzh.beans.SearchResult;
import com.smzh.beans.TableBean;

@Controller
@RequestMapping("test")
public class TestHttpClientController {
	/**
	 * 测试Spring MVC
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView testSpring() {
		return new ModelAndView("/hello");
	}

	@RequestMapping(method = RequestMethod.GET,value="get")
	public @ResponseBody SearchResult<TableBean> httpGet(int limit, int offset) {
		SearchResult<TableBean> searchResult = new SearchResult<TableBean>();
		List<TableBean> list=new ArrayList<TableBean>();
		for(int i=offset;i<offset+limit;i++){
			TableBean bean=new TableBean();
			bean.setId(i);
			bean.setName("Item"+i);
			bean.setPrice("$"+i);
			list.add(bean);
		}
		searchResult.setRows(list);
		searchResult.setTotal(800);
		return searchResult;
	}

	@RequestMapping(method = RequestMethod.POST,value="post")
	public @ResponseBody SearchResult<TableBean>  httpPost(int limit, int offset,String []arr) {
		SearchResult<TableBean> searchResult = new SearchResult<TableBean>();
		List<TableBean> list=new ArrayList<TableBean>();
		for(int i=offset;i<offset+limit;i++){
			TableBean bean=new TableBean();
			bean.setId(i);
			bean.setName("Item"+i);
			bean.setPrice("$"+i);
			list.add(bean);
		}
		searchResult.setRows(list);
		searchResult.setTotal(800);
		return searchResult;
	}

	/**
	 * 需要添加@RequestBody
	 * @param param
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST,value="postjson")
	public @ResponseBody SearchResult<TableBean>  httpPostJson(@RequestBody Param param) {
		SearchResult<TableBean> searchResult = new SearchResult<TableBean>();
		int offset=param.getOffset();
		int limit=param.getLimit();
		List<TableBean> list=new ArrayList<TableBean>();
		for(int i=offset;i<offset+limit;i++){
			TableBean bean=new TableBean();
			bean.setId(i);
			bean.setName("Item"+i);
			bean.setPrice("$"+i);
			list.add(bean);
		}
		searchResult.setRows(list);
		searchResult.setTotal(800);
		return searchResult;
	}

	@RequestMapping(method = RequestMethod.DELETE,value="delete")
	public @ResponseBody SearchResult<TableBean>  httpDelete(int limit, int offset) {
		SearchResult<TableBean> searchResult = new SearchResult<TableBean>();
		List<TableBean> list=new ArrayList<TableBean>();
		for(int i=offset;i<offset+limit;i++){
			TableBean bean=new TableBean();
			bean.setId(i);
			bean.setName("Item"+i);
			bean.setPrice("$"+i);
			list.add(bean);
		}
		searchResult.setRows(list);
		searchResult.setTotal(800);
		return searchResult;
	}

	@RequestMapping(method = RequestMethod.PUT,value="put")
	public @ResponseBody SearchResult<TableBean>  httpPut(@RequestBody Param param) {
		SearchResult<TableBean> searchResult = new SearchResult<TableBean>();
		int offset=param.getOffset();
		int limit=param.getLimit();
		List<TableBean> list=new ArrayList<TableBean>();
		for(int i=offset;i<offset+limit;i++){
			TableBean bean=new TableBean();
			bean.setId(i);
			bean.setName("Item"+i);
			bean.setPrice("$"+i);
			list.add(bean);
		}
		searchResult.setRows(list);
		searchResult.setTotal(800);
		return searchResult;
	}
}
