package com.redis.monitor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;

import com.redis.monitor.redis.BasicRedisCacheServer;
import com.redis.monitor.redis.RedisCacheServer;

import redis.clients.jedis.Client;
import redis.clients.jedis.Jedis;


public class SocketMonitor {
	
	private static final Map<String, BlockingQueue<String>> map = new ConcurrentHashMap<String, BlockingQueue<String>>();
	private static final Map<String,Jedis> clientMap = new ConcurrentHashMap<String, Jedis>();
	
	
	public static void set(String data) {
		try {
			BlockingQueue<String> blockingQueue = map.get(RedisCacheThreadLocal.getUuid());
			if (blockingQueue == null) {
				blockingQueue = new LinkedBlockingQueue<String>();
				map.put(RedisCacheThreadLocal.getUuid(), blockingQueue);
			}
			blockingQueue.put(data);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void set(final Jedis jedis) {
		final String uuid = RedisCacheThreadLocal.getUuid();
		BlockingQueue<String> blockingQueue = map.get(uuid);
		if (!clientMap.containsKey(uuid))  clientMap.put(uuid, jedis);
		boolean flag = false;
		if (blockingQueue == null) {
			flag = true;
			blockingQueue = new LinkedBlockingQueue<String>();
			map.put(uuid, blockingQueue);
		}
		
		if (flag) {
			new Thread(new Runnable() {
				private static final int sleepTime = 1000 * 10;
				private static final int ifNoDataWhenFree = 1000 * 10;
				private long beginTime = System.currentTimeMillis(); 
				@Override
				public void run() {
					try {
						do {
							String reply = jedis.getClient().getBulkReply();
							System.out.println("###:" + reply);
							long nowTime = System.currentTimeMillis();
				        	if ((nowTime - beginTime) > ifNoDataWhenFree) {
				        		beginTime = nowTime;
				        		try {
									Thread.sleep(sleepTime);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
				        	}
							map.get(uuid).put(reply);
						} while (jedis.isConnected());
						
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
			},"monitor-thread").start(); 
		}
	}
		
	
	public static void disconnectClient() {
		Jedis jedis = clientMap.get(RedisCacheThreadLocal.getUuid());
		BasicRedisCacheServer brc = RedisJedisPool.getRedisCacheServer(RedisCacheThreadLocal.getUuid());
		if (jedis != null) {
			brc.getJedisPool().returnResource(jedis);
			clientMap.remove(RedisCacheThreadLocal.getUuid());
		}
	}
	
	public static void disconnectClient(String uuid) {
		Jedis jedis = clientMap.get(uuid);
		BasicRedisCacheServer brc = RedisJedisPool.getRedisCacheServer(RedisCacheThreadLocal.getUuid());
		if (jedis != null) {
			brc.getJedisPool().returnResource(jedis);
			clientMap.remove(RedisCacheThreadLocal.getUuid());
		}
	}
	
	public static String getData() throws Exception {
		return map.get(RedisCacheThreadLocal.getUuid()).take();
	}
	
	public static List<String> getDataList() throws Exception {
		List<String> resList = new ArrayList<String>() ;
 		Queue<String> queue = map.get(RedisCacheThreadLocal.getUuid()) ; 
		while(true){
			String obj = queue.poll() ;
			if(obj == null || resList.size() > 100 ) {
				break ;
			}
			String[] logArr = obj.split(" ") ;
			if(logArr != null && logArr.length > 0 ) {
				try{
					logArr[0] = DateFormatUtils.format(new Double(Double.parseDouble(logArr[0]) * 1000).longValue() , "yyyy-MM-dd HH:mm:ss") ; 
				}catch(Exception e ) {}
			}
			resList.add(StringUtils.join(logArr , " ") ) ;
		}
		return resList ;
	}
	
	public static void clear() {
		map.get(RedisCacheThreadLocal.getUuid()).clear();
	}
	
	public static void clearAll() {
		for (Iterator<String> itr = map.keySet().iterator(); itr.hasNext();) {
			String key = itr.next();
			BlockingQueue<String> queue = map.get(key);
			queue.clear();
		}
	}
}
