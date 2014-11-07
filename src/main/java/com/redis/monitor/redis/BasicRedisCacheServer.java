package com.redis.monitor.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.JedisPool;
import redis.clients.util.Slowlog;

public interface BasicRedisCacheServer {
	public List<String> get(String key, String mapKey);
	public String get(String key);
	public <T> T get(String key,Class<T> clazz);
	public Set<String> getMapKeys(String key);
	public Set<String> getKeysByPattern(String patternKey);

	public Map<String, String> getMap(String key);

	public List<String> getMapVal(String key);
	
	public List<byte[]> get(byte[] key, byte[] mapKey) ;
	
	public String getMapVal(String cacheKey,String mapKey);
	
	public boolean isExists(String key);

	public boolean isExists(byte[] byteKey);

	public Set<String> getSet(String key);
	
	public List<String> getList(String key);
	
	public List<String> get(String key, String[] mapKeys);
	
	/**
	 * 堆栈(后进先出)
	 * @param key
	 * @return
	 */
	public String brpopDataQueue(String key);
	
	/**
	 * 出队(先进先出)
	 * @param key
	 * @return
	 */
	public String blpopDataQueue(String key);
	
	public long llenQueueSize(String key);
	
	public String getRedisInfo();
	
	public String ping();
	
	public String flushAll();
	
	public String flushDb();
	
	public void monitor();
	
	public List<String> configGet(String pattern);
	
	public List<String> configGetAll();
	
	public String configSet(String key,String value);
	
	public Long dbSize();
	
	public void save(String key, String value);

	public void save(String key, String value, int expireTime);

	public void save(String key, Object obj);

	public void save(String key, Object obj, int expireTime);

	public void replace(String key, Map<String, String> map, int expireTime);

	public void save(String key, Map<String, String> map);

	public void save(String key, Map<String, String> map, int expireTime);
	
	public void saveMapVal(String cacheKey,String mapKey,String mapVal);
	
	public void saveMapVal(byte[] cacheKey, byte[] mapKey, byte[] mapVal) ; 

	public void del(String key);
	
	public void del(String... keys);

	public void del(String key, String mapKey);
	
	public void saveSet(String key, String str, int expireTime); 

	public void delFromSet(String key, String setValue);
	
	public void saveList(String key,List<String> list,int expireTime);
	
	public void saveList(String key,List<String> list);
	
	/**
	 * 元素追加到队列头部
	 * @param key
	 * @param data
	 */
	public void lpushQueue(String key,String data);
	
	/**
	 * 队列后追加元素(入队)
	 * @param key
	 * @param data
	 */
	public void rpushQueue(String key,String data);
	
	public List<Slowlog> slowlogs();
	
	public long ttl(String key);
	
	public String set(String key, String value) ;
	
	public JedisPool getJedisPool();
	
	public boolean isConnect() ;
}

