package com.redis.monitor;

import com.redis.monitor.redis.BasicRedisCacheServer;

public class RedisCacheThreadLocal {
	
	private  static ThreadLocal<BasicRedisCacheServer> redisCacheThreadLocal = new ThreadLocal<BasicRedisCacheServer>();
	private  static ThreadLocal<String> uuidThreadLocal = new ThreadLocal<String>();
	
	public static void set(String uuid) {
		BasicRedisCacheServer basicRedisCacheServer = RedisJedisPool.getRedisCacheServer(uuid);
		if (basicRedisCacheServer != null) {
			BasicRedisCacheServer brc = redisCacheThreadLocal.get();
			if (brc == null) {
				redisCacheThreadLocal.set(RedisJedisPool.getRedisCacheServer(uuid));
				uuidThreadLocal.set(uuid);
			}
		}
	}
	
	public static BasicRedisCacheServer get() {
		return redisCacheThreadLocal.get();
	}
	
	public static String getUuid() {
		return uuidThreadLocal.get();
	}
	
	public static void remove() {
		redisCacheThreadLocal.remove();
		uuidThreadLocal.remove();
	}
	
	
 
}
