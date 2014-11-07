package com.redis.monitor.redis.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


import redis.clients.util.Slowlog;

import com.redis.monitor.entity.Operate;
import com.redis.monitor.json.FastJson;
import com.redis.monitor.redis.BasicRedisCacheServer;

public class RedisOperateJob extends AbstractRedisJob {

	@Override
	protected Object getSaveData(BasicRedisCacheServer redisCacheServer) {
		List<Slowlog> list = redisCacheServer.slowlogs();
		
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
				op.setUsedTime(sl.getExecutionTime() + "μs");
				op.setArgs(args);
				
				opList.add(op);
			}
		} 
		if (flag) 
			return opList;
		else 
			return null;
		
	}

}
