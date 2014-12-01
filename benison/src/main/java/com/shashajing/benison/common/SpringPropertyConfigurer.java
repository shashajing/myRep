/**
 * @author jay_liang
 * @date 2013-12-30 下午3:36:56
 */
package com.shashajing.benison.common;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.Resource;

/**
 * 
 * @author yanghanjing
 *
 */
public class SpringPropertyConfigurer extends PropertyPlaceholderConfigurer {

	private static final Logger log = LoggerFactory	.getLogger(SpringPropertyConfigurer.class);
	
	private static Map<String, Object> ctxPropertiesMap;

	@Override
	public void setLocations(Resource[] locations) {
		Map<String, Resource> resourceMap = new HashMap<String, Resource>();
		for (Resource loc : locations) {
			File file = null;
			try {
				file = loc.getFile();
				if (log.isDebugEnabled()) {
					log.debug("old resouce is : " + file.getAbsolutePath());
				}
			} catch (IOException e) {
				log.error("load properties error", e);
			}
			String fileName = file.getName();
			Resource source = resourceMap.get(fileName);
			if (source != null) {
				if (file.getPath().indexOf("classes") > 0) {
					continue;
				}
			}
			resourceMap.put(fileName, loc);
		}

		Resource[] newResouce = resourceMap.values().toArray(
				new Resource[resourceMap.size()]);
		System.out.println("resouce is : " + resourceMap);
		if (log.isDebugEnabled()) {
			log.debug("resouce is : " + resourceMap);
		}
		super.setLocations(newResouce);
	}

	@Override
	protected void processProperties(
			ConfigurableListableBeanFactory beanFactoryToProcess,
			Properties props) throws BeansException {
		super.processProperties(beanFactoryToProcess, props);
		/*ctxPropertiesMap = new HashMap<String, Object>();
		for (Object key : props.keySet()) {
			String keyStr = key.toString();
			String value = props.getProperty(keyStr);
			ctxPropertiesMap.put(keyStr, value);
			System.setProperty(keyStr, value);
		}*/
		Map<String, String> dbInfo = new HashMap<String, String>();
		dbInfo.put("driverClass", props.getProperty("aliLibrary.db.driverClass"));
		dbInfo.put("jdbcUrl", props.getProperty("aliLibrary.db.jdbcUrl"));
		dbInfo.put("username", props.getProperty("aliLibrary.db.username"));
		dbInfo.put("password", props.getProperty("aliLibrary.db.password"));
		/*try {
			ctxPropertiesMap = SystemBasicConfigUtil.loadPropertiesFromDB(dbInfo);
		} catch (Exception e) {
			log.error("从数据库加载基础信息失败，改为从配置文件加载基础信息", e);
			ctxPropertiesMap = new HashMap<String, Object>();
			for (Object key : props.keySet()) {
				String keyStr = key.toString();
				String value = props.getProperty(keyStr);
				ctxPropertiesMap.put(keyStr, value);
				System.setProperty(keyStr, value);
			}
		}
		// 如果从数据库没有成功加载基础信息，则改为从配置文件加载基础信息
		if (null == ctxPropertiesMap || ctxPropertiesMap.isEmpty()) {
			ctxPropertiesMap = new HashMap<String, Object>();
			for (Object key : props.keySet()) {
				String keyStr = key.toString();
				String value = props.getProperty(keyStr);
				ctxPropertiesMap.put(keyStr, value);
				System.setProperty(keyStr, value);
			}
		}*/
	}
}
