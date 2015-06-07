package com.shashajing.benison.filter;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.constructs.web.filter.SimplePageCachingFilter;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * ehcache 缓存页面过滤器
 * @author yanghanjing
 *
 */
public class PageCachingFilter extends SimplePageCachingFilter {

	/**
	 * 覆盖父类的方法，以便获取spring 容器中定义的  EhCacheManager
	 */
	@Override
	protected CacheManager getCacheManager() {
		CacheManager ehCacheManager = null;
		WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(getFilterConfig().getServletContext());
		ehCacheManager = (CacheManager)webApplicationContext.getBean("ehCacheManager");
		if (ehCacheManager != null) {
			return ehCacheManager;
		} else {
			try {
				ClassPathResource classPathResource = new ClassPathResource("/cache/ehcache.xml");
				ehCacheManager = CacheManager.create(classPathResource.getInputStream());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
        return ehCacheManager;
    }

}
