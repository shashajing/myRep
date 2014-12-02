package com.shashajing.benison.utils;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.google.common.collect.Maps;

/**
 * 系统参数工具类
 * @author yanghanjing
 *
 */
public class SystemPropertyUtils {
	
	private static final Map<String, String> map = Maps.newHashMap();
	
	/**
	 * 设置系统参数
	 * @param key
	 * @param value
	 */
	public static void setSystemProperty(String key, String value) {
		if (StringUtils.isNotBlank(key)) {
			map.put(key, value);
		}
	}
	
	/**
	 * 根据参数key取得参数值
	 * @param key
	 * @return
	 */
	public static String getProperty(String key) {
		return StringUtils.isBlank(key) ?null:map.get(key);
	}
	
	/**
	 * 根据参数key取得参数值,取不到返回默认值
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static String getPropertyUseDefault(String key, String defaultValue) {
		String value = getProperty(key);
		return StringUtils.isBlank(value) ?defaultValue:value;
	}
}
