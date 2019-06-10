package com.mowen.boot;

import com.mowen.server.NettyServer;
import org.apache.log4j.Logger;

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
			String conf = args != null&& args.length > 0?args[0]:"";
			if(conf ==  "" || !conf.endsWith("properties")){
				conf = "conf.properties";
			}
			ApplicationContext.loadProperties(conf);
			NettyServer nettyServer = new NettyServer(ApplicationContext.getPropertiesInt("port"));
			nettyServer.start();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

}
