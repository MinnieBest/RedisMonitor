package com.redis.monitor.manager.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.codec.Encoder;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.util.Slowlog;

import com.redis.monitor.RedisCacheThreadLocal;
import com.redis.monitor.RedisConfig;
import com.redis.monitor.RedisInfoDetail;
import com.redis.monitor.RedisJedisPool;
import com.redis.monitor.RedisServer;
import com.redis.monitor.SocketMonitor;
import com.redis.monitor.entity.Operate;
import com.redis.monitor.entity.OperateCompare;
import com.redis.monitor.json.FastJson;
import com.redis.monitor.manager.RedisManager;
import com.redis.monitor.redis.BasicRedisCacheServer;
import com.redis.monitor.utils.FileUtils;

public class RedisManagerImpl implements RedisManager {
	
	public static final Logger logger = LoggerFactory.getLogger(RedisManagerImpl.class);
	
	public List<RedisServer> redisServerList() {
		return RedisJedisPool.getAllRedisServer();
	}

	public List<RedisInfoDetail> getRedisInfo() {
		String info = getBasicRedisCacheServer().getRedisInfo();
		List<RedisInfoDetail> ridList = new ArrayList<RedisInfoDetail>();
		String[] strs = info.split("\n");
		RedisInfoDetail rif = null;
		if(strs != null && strs.length > 0){
			for (int i = 0; i < strs.length; i++) {
				rif = new RedisInfoDetail();
				String s = strs[i];
				String[] str = s.split(":");
				if(str != null && str.length > 1 ) {
					String key = str[0];
					String value = str[1];
					rif.setKey(key);
					rif.setValue(value);
					ridList.add(rif);
				}
			}
		}
		return ridList;
	}

	public List<RedisConfig> getRedisConfigXmlDetail() {
		List<String> configList = getBasicRedisCacheServer().configGetAll();
		int index = 0;
		List<RedisConfig> list = new LinkedList<RedisConfig>();
	    for (String str : configList) {
	    	if (index % 2 == 0) {
	    		RedisConfig rc = new RedisConfig();
	    		rc.setKey(str);
	    		rc.setValue(configList.get(index+1));
	    		rc.setDescription(str);
	    		list.add(rc);
	    	}
	    	index++; 
	    }
		return list;
	}

	public Map<String, String> getRedisConfigByPattern(String pattern) {
		return null;
	}

	public String configRedisConfigXml(String key, String value) {
		return null;
	}

	public Long getRedisDbSize() {
		return null;
	}

	public String ping() {
		return null;
	}

	public String flushAll() {
		return getBasicRedisCacheServer().flushAll();
	}

	public String flushDb() {
		return  getBasicRedisCacheServer().flushDb();
	}
	
	public List<Operate> findAllOperateDetail() {
		/*File file = FileUtils.getFile("operate", RedisCacheThreadLocal.getUuid());
		List<Operate> opList = null;
		if (!file.exists()) {
			return opList;
		}
		try {
			List<String> list = IOUtils.readLines(new FileInputStream(file));
			StringBuffer sb = new StringBuffer();
			for (String str : list) {
				sb.append(str);
			}
			opList = FastJson.jsonToList("[" + sb.toString() + "]", Operate.class);
			Collections.sort(opList, new OperateCompare());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return opList;*/
        List<Slowlog> list = getBasicRedisCacheServer().slowlogs();
		List<Operate> opList = null;
		Operate op  = null;
		boolean flag = false;
		if (list != null && list.size() > 0) {
			opList = new LinkedList<Operate>();
			for (Slowlog sl : list) {
				String args = FastJson.toJson(sl.getArgs());
				if (args.equals("[\"PING\"]") || args.equals("[\"SLOWLOG\",\"get\"]") || args.equals("[\"DBSIZE\"]") || args.equals("[\"INFO\"]")) {
					continue;
				}	
				op = new Operate();
				flag = true;
				op.setId(sl.getId());
				op.setCreateTime(getDateStr());
				op.setExecuteTime(getDateStr(sl.getTimeStamp() * 1000));
				op.setUsedTime(sl.getExecutionTime()/1000.0 + "ms");
				op.setArgs(args);
				
				opList.add(op);
			}
		} 
		if (flag) 
			return opList;
		else 
			return null;
	}
	
	
	private String getDateStr(long timeStmp) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(new Date(timeStmp));
	}
	
	public Set<String> getKeysByPattern(String uuid , String patternKey) {
		return getBasicRedisCacheServer(uuid).getKeysByPattern(patternKey) ; 
	}
	
	@Override
	public String get(String key ) {
		try{
			return getBasicRedisCacheServer().get(key) ;
		} catch(Exception e) {
			return null ;
		}
	}

	public Map<String, String> getMap(String key) {
		try{
			return getBasicRedisCacheServer().getMap(key) ;
		} catch(Exception e) {
			return null ;
		}
	}
	
	@Override
	public List<String> getList(String key) {
		try{
			return getBasicRedisCacheServer().getList(key) ;
		}catch(Exception e) {
			return null ;
		}
	}
	
	@Override
	public Set<String> getSet(String key) {
		try{
			return getBasicRedisCacheServer().getSet(key) ;
		}catch(Exception e) {
			return null ;
		}
	}
	
	public Map<String,Object> getMemeryInfo() {
		String[] strs = getBasicRedisCacheServer().getRedisInfo().split("\n");
		Map<String, Object> map = null;
		for (int i = 0; i < strs.length; i++) {
			String s = strs[i];
			String[] detail = s.split(":");
			if (detail[0].equals("used_memory")) {
				map = new HashMap<String, Object>();
				map.put("used_memory",detail[1].substring(0, detail[1].length() - 1));
				map.put("create_time", new Date().getTime());
				break;
			}
		}
		return map;
	}
	

	public Map<String,Object> getKeysSize() {
		long dbSize = getBasicRedisCacheServer().dbSize();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("create_time", new Date().getTime());
		map.put("dbSize", dbSize);
		return map;
	}
	
	
	private String getDateStr() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(new Date());
	}
	
	/**
	 * start to monitor redis
	 */
	public void startMonitor(String uuid) {
		getBasicRedisCacheServer(uuid).monitor(); 
	}
	
	public void stopMonitor(String uuid) {
		SocketMonitor.disconnectClient(); 
	}
	
	public BasicRedisCacheServer getBasicRedisCacheServer() {
		return RedisCacheThreadLocal.get();
	}
	
	public BasicRedisCacheServer getBasicRedisCacheServer(String uuid ) {
		return RedisJedisPool.getRedisCacheServer(uuid) ;
	}
	
	@Override
	public String set(String key , Object value) {
		return getBasicRedisCacheServer().set(key , String.valueOf(value)) ;
	}

	@Override
	public void delete(String... key){
		getBasicRedisCacheServer().del(key); 
	}
	
	@Override
	public long ttl(String key ){
		return getBasicRedisCacheServer().ttl(key) ;
	}
}
