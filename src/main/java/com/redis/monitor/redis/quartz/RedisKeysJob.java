package com.redis.monitor.redis.quartz;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import com.redis.monitor.json.FastJson;
import com.redis.monitor.redis.BasicRedisCacheServer;

public class RedisKeysJob extends AbstractRedisJob {
	@Override
	protected Map<String,Object> getSaveData(BasicRedisCacheServer redisCacheServer) {
		long dbSize = redisCacheServer.dbSize();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("create_time", getDateStr());
		map.put("dbSize", dbSize);
		return map;
	}

}
