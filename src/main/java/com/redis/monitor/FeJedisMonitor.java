package com.redis.monitor;

import redis.clients.jedis.Client;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisMonitor;
import redis.clients.jedis.JedisPool;

public  class FeJedisMonitor extends JedisMonitor {
	
	private Jedis jedis;
	
	public FeJedisMonitor(Jedis jedis) {
		this.jedis = jedis;
	}
	
	
	public void proceed(Client client) {
        SocketMonitor.set(jedis);
    }
	
	public  void onCommand(String command) {
		 
	}
}
