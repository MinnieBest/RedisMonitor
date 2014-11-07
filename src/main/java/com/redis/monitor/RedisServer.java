package com.redis.monitor;

import java.util.List;

public class RedisServer {
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
	private List<RedisServer> slaveRedisServer;
	private RedisServer masterRedisServer;
	
	public RedisServer() {}

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

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public boolean isMaster() {
		return isMaster;
	}

	public void setMaster(boolean isMaster) {
		this.isMaster = isMaster;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public List<RedisServer> getSlaveRedisServer() {
		return slaveRedisServer;
	}

	public void setSlaveRedisServer(List<RedisServer> slaveRedisServer) {
		this.slaveRedisServer = slaveRedisServer;
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

	public RedisServer getMasterRedisServer() {
		return masterRedisServer;
	}

	public void setMasterRedisServer(RedisServer masterRedisServer) {
		this.masterRedisServer = masterRedisServer;
	}

	@Override
	public String toString() {
		return "RedisServer [uuid=" + uuid + ", name=" + name
				+ ", description=" + description + ", host=" + host + ", port="
				+ port + ", maxActive=" + maxActive + ", maxIdle=" + maxIdle
				+ ", maxWait=" + maxWait + ", testOnBorrow=" + testOnBorrow
				+ ", isMaster=" + isMaster + ", slaveRedisServer="
				+ slaveRedisServer + ", masterRedisServer=" + masterRedisServer
				+ "]";
	}
	
}
