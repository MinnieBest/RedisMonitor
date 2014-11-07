package com.redis.monitor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.redis.monitor.RedisServer;
import com.redis.monitor.redis.BasicRedisCacheServer;
import com.redis.monitor.redis.RedisCacheServer;


public class RedisJedisPool {
	
	private static Map<String,Redis> map = new ConcurrentHashMap<String, Redis>();
	
	public static final String DEFAULT_UUID = Constants.DEFAULT_UUID;
	
	public static int LOAD_SIZE = 0;
	
	public RedisJedisPool () {}

	public static void initRedisJedisPool(List<RedisServer> rsList) {
		Redis redis = null;
		for (RedisServer rs : rsList) {
			BasicRedisCacheServer server = new RedisCacheServer(rs.getHost(), rs.getPort(), rs.getMaxActive(), rs.getMaxIdle(), rs.getMaxWait(), rs.isTestOnBorrow());
			redis = new Redis(server, rs);
			map.put(rs.getUuid(), redis);
			
			List<RedisServer> slaveRs = rs.getSlaveRedisServer();
			for (RedisServer slRs : slaveRs) {
				BasicRedisCacheServer readServer = new RedisCacheServer(slRs.getHost(), slRs.getPort(), slRs.getMaxActive(), slRs.getMaxIdle(), slRs.getMaxWait(), slRs.isTestOnBorrow());
				redis = new Redis(readServer, slRs);
				map.put(slRs.getUuid(), redis);
			}
		}
		LOAD_SIZE = map.size();
	}
	
	public static void addNewRedisServer(RedisServer rs) {
		if (rs != null) {
			BasicRedisCacheServer server = new RedisCacheServer(rs.getHost(), rs.getPort(), rs.getMaxActive(), rs.getMaxIdle(), rs.getMaxWait(), rs.isTestOnBorrow());
			
			Redis redis = new Redis(server, rs);
			map.put(rs.getUuid(), redis);
		}
	}
	
	public static void removeRedisServer(String uuid) {
		if (uuid != null && !uuid.equals("") && map.containsKey(uuid)) map.remove(uuid);
	}
	
	public static BasicRedisCacheServer getRedisCacheServer(String uuid) {
		return map.get(uuid) == null ? null : map.get(uuid).getBasicRedisCacheServer();
	}
	
	public static BasicRedisCacheServer getRedisCacheServer() {
		String uuid = RedisCacheThreadLocal.getUuid();
		return map.get(uuid) == null ? null : map.get(uuid).getBasicRedisCacheServer();
	}
	
	public static RedisServer getRedisServer(String uuid) {
		return map.get(uuid) == null ? null : map.get(uuid).getRedisServer();
	}
	
	public static RedisServer getRedisServer() {
		return map.get(RedisCacheThreadLocal.getUuid()).getRedisServer();
	}
	
	public static List<RedisServer> getAllRedisServer() {
		List<RedisServer> rsList = new ArrayList<RedisServer>();
		for (Iterator<String> itr = map.keySet().iterator(); itr.hasNext();) {
			String key = itr.next();
			rsList.add(map.get(key).getRedisServer());
		}
		return rsList;
	}
	
	public static boolean isExists(String uuid) {
		return map.containsKey(uuid);
	}
	
	public static Set<String> getRedisUuids() {
		return map.keySet();
	}
	
	public static Collection<Redis> getAllRedis() {
		return map.values();
	}
	
}
