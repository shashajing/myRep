package com.shashajing.benison.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shashajing.benison.dao.NewsDao;
import com.shashajing.benison.entity.News;

@Component
@Transactional
//@Transactional(rollbackFor=Exception.class)
//@Transactional(notRollbackFor=RunTimeException.class)
//@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
public class NewsService {
	
	@Autowired
	private NewsDao newsDao;
	
    @Autowired  
    private RedisTemplate<Serializable, Serializable> redisTemplate;  
	
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
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public void setNewsTitle(final News news) {  
	    redisTemplate.execute(new RedisCallback<Object>() {  
	        @Override
	        public Object doInRedis(RedisConnection connection)  
	                throws DataAccessException {  
	            connection.set(
	                    redisTemplate.getStringSerializer().serialize(  
	                            "news.title.newsId." + news.getNewsId()),  
	                    redisTemplate.getStringSerializer().serialize(  
	                    		news.getTitle()));  
	            return null;  
	        }
	    });  
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED) 
	public void setNewsContent(final News news) {
		redisTemplate.execute(new RedisCallback<Object>() {  
			@Override
			public Object doInRedis(RedisConnection connection)  
					throws DataAccessException {  
				connection.set(  
						redisTemplate.getStringSerializer().serialize(  
								"news.content.newsId." + news.getNewsId()),  
								redisTemplate.getStringSerializer().serialize(  
										news.getContent()));  
				return null;  
			}
		});
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED) 
	public String getNewsContent(final String newsId) {
		return redisTemplate.execute(new RedisCallback<String>() {  
			@Override
			public String doInRedis(RedisConnection connection)  
					throws DataAccessException {
				byte[] key =  redisTemplate.getStringSerializer().serialize("news.content.newsId." + newsId);
				if (connection.exists(key)) {
					return redisTemplate.getStringSerializer().deserialize(connection.get(key));
				} else {
					return null;
				}
			}
		});
	}
	
	public String getNewsTitle(final String newsId) {
		return redisTemplate.execute(new RedisCallback<String>() {  
			@Override
			public String doInRedis(RedisConnection connection)  
					throws DataAccessException {
				byte[] key =  redisTemplate.getStringSerializer().serialize("news.title.newsId." + newsId);
				if (connection.exists(key)) {
					return redisTemplate.getStringSerializer().deserialize(connection.get(key));
				} else {
					return null;
				}
			}
		});
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED) 
	public News getNewsTitleAndContent(final String newsId) {
		return redisTemplate.execute(new RedisCallback<News>() {  
			@Override
			public News doInRedis(RedisConnection connection)  
					throws DataAccessException {
				News news = new News();
				byte[] titleKey =  redisTemplate.getStringSerializer().serialize("news.title.newsId." + newsId);
				byte[] contentKey =  redisTemplate.getStringSerializer().serialize("news.content.newsId." + newsId);
				if (connection.exists(titleKey)) {
					byte[] titleValue = connection.get(titleKey);
					if (null != titleValue) {
						news.setTitle(redisTemplate.getStringSerializer().deserialize(connection.get(titleValue)));
					}
				}
				if (connection.exists(contentKey)) {
					byte[] contentValue = connection.get(contentKey);
					if (null != contentValue) {
						news.setContent(redisTemplate.getStringSerializer().deserialize(connection.get(contentValue)));
					}
				}
				return news;
			}
		});
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED) 
	public void deleteNewsTitle(final String newsId) {  
	    redisTemplate.execute(new RedisCallback<Object>() {  
	        public Object doInRedis(RedisConnection connection) {  
	            connection.del(redisTemplate.getStringSerializer().serialize(  
	                    "news.title.newsId." + newsId));  
	            return null;  
	        }  
	    });  
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED) 
	public void deleteNewsContent(final String newsId) {  
		redisTemplate.execute(new RedisCallback<Object>() {  
			public Object doInRedis(RedisConnection connection) {  
				connection.del(redisTemplate.getStringSerializer().serialize(  
						"news.content.newsId." + newsId));  
				return null;  
			}  
		});  
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED) 
	public void deleteNews(String newsId) {
		deleteNewsTitle(newsId);
		deleteNewsContent(newsId);
	}
	
	public static void main(String[] a){
		
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/spring/applicationContext.xml");
		
		NewsService service = (NewsService)applicationContext.getBean("newsService");
		
		/*for (int i = 0; i < 20; i++) {
			News news = new News();
			news.setNewsId(i);
			news.setTitle("title1");
			news.setContent("content1");
			service.setNewsContent(news);
			service.setNewsTitle(news);
		}*/
		
		for (int i = 0; i < 20; i++) {
			System.out.println(service.getNewsTitle(String.valueOf(i)));
		}
		
		
		
		
		
		
		
		applicationContext.close();
		
	}
	
}
