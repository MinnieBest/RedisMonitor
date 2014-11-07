package com.redis.monitor;

import java.util.ResourceBundle;

public class Constants {

	public static final ResourceBundle config = ResourceBundle
			.getBundle("config");
	//
	// public static final String WEB_APP = config.getString("webapp");
	public static final int PORT = Integer.parseInt(config.getString("port"));
	public static final String DEFAULT_UUID = config.getString("default_uuid");
	public static final String JSON_VIEW = "jsonView";

	public static final String REDIS_SERVER_CONFIG_PATH = config
			.getString("redisServerConfigPath");
	public static final String REDIS_SERVER_CONFIG_FILE_NAME = config
			.getString("redisServerConfigName");

	public static final String RES_STATUS = "status";
	public static final String RES_MSG = "message";

}
