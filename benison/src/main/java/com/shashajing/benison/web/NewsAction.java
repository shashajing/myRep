package com.shashajing.benison.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;
import com.opensymphony.xwork2.ActionSupport;
import com.shashajing.benison.entity.News;
import com.shashajing.benison.service.NewsService;

@Component("newsAction")
public class NewsAction extends ActionSupport{

	private List<News> newsList;
	
	@Autowired
	private NewsService newsService;

	public List<News> getNewsList() {
		return newsList;
	}
	
	
	public String goToHomePage() {
		
		Map<String, Object> parameters = Maps.newHashMap();
		newsList = newsService.searchUser(parameters);
		
		return SUCCESS;
	}
	
	
	
	
}
