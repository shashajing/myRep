/**
 * @author jay_liang
 * @date 2013-12-30 下午3:36:56
 */
package com.shashajing.benison.common;

import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.shashajing.benison.utils.SystemPropertyUtils;

/**
 * 
 * @author yanghanjing
 *
 */
public class SpringPropertyConfigurer extends PropertyPlaceholderConfigurer {

	@Override
	protected void processProperties(
			ConfigurableListableBeanFactory beanFactoryToProcess,
			Properties props) throws BeansException {
		super.processProperties(beanFactoryToProcess, props);
		//把配置文件的系统参数放到内存对象
		for (Object key : props.keySet()) {
			String keyStr = key.toString();
			String value = props.getProperty(keyStr);
			SystemPropertyUtils.setSystemProperty(keyStr, value);
		}
		
	}
}
