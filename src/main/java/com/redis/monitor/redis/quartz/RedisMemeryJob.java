package com.redis.monitor.redis.quartz;

import java.util.HashMap;
import java.util.Map;


import com.redis.monitor.redis.BasicRedisCacheServer;

public class RedisMemeryJob extends AbstractRedisJob {
	@Override
	protected Map<String,Object> getSaveData(BasicRedisCacheServer redisCacheServer) {
		String[] strs = redisCacheServer.getRedisInfo().split("\n");
		Map<String, Object> map = null;
		for (int i = 0; i < strs.length; i++) {
			String s = strs[i];
			String[] detail = s.split(":");
			if (detail[0].equals("used_memory_human")) {
				map = new HashMap<String, Object>();
				map.put("used_memory_human",detail[1].substring(0, detail[1].length() - 1));
				map.put("create_time", getDateStr());
				break;
			}
		}
		return map;
	}

}
