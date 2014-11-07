package com.redis.monitor.web.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.redis.monitor.RedisJedisPool;
import com.redis.monitor.RedisServer;

@Controller
@RequestMapping(value="/server")
public class RedisServerController extends BaseProfileController {
	
	@RequestMapping(value="/redisServer.htm")
	public String toRedisServer() {
		return "redisServer";
	}
	
	@RequestMapping(value="/redisServerList.htm",method=RequestMethod.POST)
	public ModelAndView redisServerList() {
		ModelAndView mv = getJsonModelAndView();
		List<RedisResult> list = resultList();
		mv.addObject("rows", list);
		mv.addObject("total", list == null ? 0 : list.size());
		return mv;
	}
	
	@RequestMapping(value="/newServer.htm",method=RequestMethod.POST)
	public String addRedisServer(HttpServletRequest request,@ModelAttribute RedisServer redisServer) {
		RedisJedisPool.removeRedisServer(redisServer.getUuid());
		RedisJedisPool.addNewRedisServer(redisServer);
		//TODO redis列表加载
		List<RedisServer> rsList = redisManager.redisServerList();
		request.setAttribute("redisServerList", rsList);
/*		ModelAndView mv = getJsonModelAndView();
		List<RedisResult> list = resultList();
		mv.addObject("rows", list);
		mv.addObject("total", list == null ? 0 : list.size());*/
		return "redisServer";
	}
	
	@RequestMapping(value="/removeServer.htm",method=RequestMethod.GET)
	public ModelAndView removeRedisServer(HttpServletRequest request,@RequestParam(required=true) String uuid) {
		RedisJedisPool.removeRedisServer(uuid);
		//TODO redis列表加载
		List<RedisServer> rsList = redisManager.redisServerList();
		request.setAttribute("redisServerList", rsList);
		ModelAndView mv = getJsonModelAndView();
		List<RedisResult> list = resultList();
		mv.addObject("rows", list);
		mv.addObject("total", list == null ? 0 : list.size());
		return mv;
	}
	
	private List<RedisResult> resultList() {
		List<RedisServer> rsList = RedisJedisPool.getAllRedisServer();
		List<RedisResult> list = null;
		if (rsList != null && rsList.size() > 0) {
			list = new LinkedList<RedisServerController.RedisResult>();
			RedisResult rr = null;
			for (RedisServer rs : rsList) {
				rr = new RedisResult();
				rr.setUuid(rs.getUuid());
				rr.setDescription(rs.getDescription());
				rr.setHost(rs.getHost());
				rr.setMaster(rs.isMaster());
				RedisServer mr = rs.getMasterRedisServer();
				rr.setMasterRedisServer(mr == null ? null : mr.getUuid() + " | " + mr.getHost() + ":" + mr.getPort());
				rr.setMaxActive(rs.getMaxActive());
				rr.setMaxIdle(rs.getMaxIdle());
				rr.setMaxWait(rs.getMaxWait());
				rr.setName(rs.getName());
				rr.setPort(rs.getPort());
				List<RedisServer> rList = rs.getSlaveRedisServer();
				if (rList == null || rList.size() == 0) {
					rr.setSlaveRedisServer(null);
				} else {
					StringBuffer sb = new StringBuffer();
					for (RedisServer r : rList) {
						sb.append(r.getHost() + ":" + r.getPort() + "|");
					}
					if ((sb.charAt(sb.length()-1) + "").equals("|")) {
						sb.deleteCharAt(sb.length()-1);
					}
					rr.setSlaveRedisServer(sb.toString());
				}
				list.add(rr);
			}
		}
		return list;
	}
	
	class RedisResult {
		private String uuid;
		private String name;
		private String description;
		private String host;
		private int port;
		private int maxActive;
		private int maxIdle;
		private int maxWait;
		private boolean testOnBorrow;
		private boolean isMaster;
		private String slaveRedisServer;
		private String masterRedisServer;
		public String getUuid() {
			return uuid;
		}
		public void setUuid(String uuid) {
			this.uuid = uuid;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getHost() {
			return host;
		}
		public void setHost(String host) {
			this.host = host;
		}
		public int getPort() {
			return port;
		}
		public void setPort(int port) {
			this.port = port;
		}
		public int getMaxActive() {
			return maxActive;
		}
		public void setMaxActive(int maxActive) {
			this.maxActive = maxActive;
		}
		public int getMaxIdle() {
			return maxIdle;
		}
		public void setMaxIdle(int maxIdle) {
			this.maxIdle = maxIdle;
		}
		public int getMaxWait() {
			return maxWait;
		}
		public void setMaxWait(int maxWait) {
			this.maxWait = maxWait;
		}
		public boolean isTestOnBorrow() {
			return testOnBorrow;
		}
		public void setTestOnBorrow(boolean testOnBorrow) {
			this.testOnBorrow = testOnBorrow;
		}
		public boolean isMaster() {
			return isMaster;
		}
		public void setMaster(boolean isMaster) {
			this.isMaster = isMaster;
		}
		public String getSlaveRedisServer() {
			return slaveRedisServer;
		}
		public void setSlaveRedisServer(String slaveRedisServer) {
			this.slaveRedisServer = slaveRedisServer;
		}
		public String getMasterRedisServer() {
			return masterRedisServer;
		}
		public void setMasterRedisServer(String masterRedisServer) {
			this.masterRedisServer = masterRedisServer;
		}
		
		
	}

}
