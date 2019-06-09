package com.mowen.boot;

import org.apache.log4j.Logger;
import org.jboss.errai.reflections.Reflections;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * mowen_parent com.mowen.boot
 *
 * @author: mowen
 * @createTime: 2019/6/9 9:21
 * @group:
 * @description:
 */
public class ApplicationContext {
	private static Logger logger = Logger.getLogger(ApplicationContext.class);
	private static Properties properties;

	private static Map<String, Object> beans;

	static {
		beans = new HashMap<>();
	}

	private ApplicationContext(){}

	public static void loadProperties(String filePath){
		try {
			InputStream stream = BootStrap.class.getClassLoader().getResourceAsStream(filePath);
			Properties prop = new Properties();
			prop.load(stream);
			properties = prop;
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public static void loadbean(List<String> dirs){
		logger.info("load Dir beans" + dirs);
		if(dirs == null || dirs.size() == 0){
			return;
		}
		for (String dir : dirs) {
			Reflections reflections = new Reflections(dir, null);
			//todo:完成对类的装载，类似于spring di
		}

	}

	public static Object findBean(String key){
		return beans.get(key);
	}

	public static String getPropertiesValue(String key){
		return properties.getProperty(key);
	}

	public static int getPropertiesInt(String key){
		String value =  properties.getProperty(key);
		if(value != null && value != ""){
			return Integer.valueOf(value);
		}
		return 0;
	}

}
