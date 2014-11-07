package com.redis.monitor;

import com.redis.monitor.redis.BasicRedisCacheServer;

public class Redis {
	private BasicRedisCacheServer basicRedisCacheServer;
	private RedisServer redisServer;
	
	public Redis() {}
	
	public Redis(BasicRedisCacheServer basicRedisCacheServer,
			RedisServer redisServer) {
		super();
		this.basicRedisCacheServer = basicRedisCacheServer;
		this.redisServer = redisServer;
	}



	public BasicRedisCacheServer getBasicRedisCacheServer() {
		return basicRedisCacheServer;
	}

	public void setBasicRedisCacheServer(BasicRedisCacheServer basicRedisCacheServer) {
		this.basicRedisCacheServer = basicRedisCacheServer;
	}

	public RedisServer getRedisServer() {
		return redisServer;
	}

	public void setRedisServer(RedisServer redisServer) {
		this.redisServer = redisServer;
	}
}
