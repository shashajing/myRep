package com.shashajing.benison.dao;

import java.util.List;
import java.util.Map;

import com.shashajing.benison.entity.News;

/**
 * 
 * @author yanghanjing
 *
 */
@MyBatisDao
public interface NewsDao {

	List<News> searchNews(Map<String, Object> parameters);
	
	int countNews(Map<String, Object> parameters);
	
	long addNews(News news);
	
	long updateNews(News news);
	
	long deleteNews(List<Integer> ids);
}
