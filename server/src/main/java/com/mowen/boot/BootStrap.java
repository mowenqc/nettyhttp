package com.mowen.boot;

import com.mowen.server.NettyServer;
import org.apache.log4j.Logger;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;

/**
 * mowen_parent com.mowen.boot
 * @description:
 * @author: mowen
 * @createTime: 2019/6/8 9:49
 * @group:
 */
public class BootStrap {

	private static Logger logger = Logger.getLogger(BootStrap.class);
	public static void main(String[] args) {
		try {
			String conf = args[0];
			if(!conf.endsWith("properties")){
				conf = "conf/conf.properties";
			}
			ApplicationContext.loadbean(Arrays.asList(conf));
			NettyServer nettyServer = new NettyServer(ApplicationContext.getPropertiesInt("port"));
			nettyServer.start();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

}
