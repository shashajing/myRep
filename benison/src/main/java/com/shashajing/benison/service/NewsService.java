package com.shashajing.benison.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.shashajing.benison.dao.NewsDao;
import com.shashajing.benison.entity.News;

@Component
@Transactional
public class NewsService {
	
	@Autowired
	private NewsDao newsDao;
	
	public List<News> searchUser(Map<String, Object> parameters) {
		return newsDao.searchNews(parameters);
	}
	
	public long addNews(News news) {
		long num = 0L;
		
		try {
			num = newsDao.addNews(news);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return num;
	}
	
	public long updateNews(News news) {
		long num = 0L;
		
		try {
			num = newsDao.updateNews(news);
		} catch (Exception e) {
			
		}
		return num;
	}
	
	public long deleteNews(List<Integer> ids) {
		long num = 0L;
		
		try {
			num = newsDao.deleteNews(ids);
		} catch (Exception e) {
			
		}
		return num;
	}
	

}
