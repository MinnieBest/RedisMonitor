package com.redis.monitor.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.redis.monitor.AppHock;

public class AppListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		AppHock.hockRedisServerConfig();
	}

}
