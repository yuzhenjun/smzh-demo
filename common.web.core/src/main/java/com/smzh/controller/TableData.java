package com.smzh.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smzh.beans.TableBean;
import com.smzh.util.SearchResult;

@Controller
@RequestMapping("table")
public class TableData {
	/**
	 * @param limit   查询条数
	 * @param offset  起始查询位置
	 * @param order
	 * @return
	 */
	@RequestMapping("data")
	public @ResponseBody SearchResult<TableBean> getData(int limit, int offset,String order) {
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
}
