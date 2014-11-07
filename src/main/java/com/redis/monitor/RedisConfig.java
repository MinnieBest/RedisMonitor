package com.redis.monitor;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


public class RedisConfig {
	
	private static Map<String,String> descMap = new HashMap<String, String>();
	
	static {
		descMap.put("daemonize", "# daemonize no 默认情况下，redis不是在后台运行的，如果需要在后台运行，把该项的值更改为yes");
		descMap.put("pidfile", "# 当redis在后台运行的时候，Redis默认会把pid文件放在/var/run/redis.pid，你可以配置到其他地址。# 当运行多个redis服务时，需要指定不同的pid文件和端口");
		descMap.put("port", "# 指定redis运行的端口，默认是6379");
		descMap.put("bind", "# 指定redis只接收来自于该IP地址的请求，如果不进行设置，那么将处理所有请求，# 在生产环境中最好设置该项");
		descMap.put("unixsocket", "# Specify the path for the unix socket that will be used to listen for# incoming connections. There is no default, so Redis will not listen# on a unix socket when not specified.");
		descMap.put("unixsocketperm", "# Specify the path for the unix socket that will be used to listen for# incoming connections. There is no default, so Redis will not listen");
		descMap.put("timeout", "# 设置客户端连接时的超时时间，单位为秒。当客户端在这段时间内没有发出任何指令，那么关闭该连接# 0是关闭此设置");
		descMap.put("loglevel", "# 指定日志记录级别# Redis总共支持四个级别：debug、verbose、notice、warning，默认为verbose# debug		记录很多信息，用于开发和测试# varbose	有用的信息，不像debug会记录那么多# notice	普通的verbose，常用于生产环境# warning	只有非常重要或者严重的信息会记录到日志");
		descMap.put("logfile", "# 配置log文件地址# 默认值为stdout，标准输出，若后台模式会输出到/dev/null#logfile stdout");
		descMap.put("syslog-enabled", "# To enable logging to the system logger, just set 'syslog-enabled' to yes,# and optionally update the other syslog parameters to suit your needs.");
		descMap.put("syslog-ident", "# Specify the syslog identity.");
		descMap.put("syslog-facility", "# Specify the syslog facility.  Must be USER or between LOCAL0-LOCAL7.");
		descMap.put("databases", "# 可用数据库数# 默认值为16，默认数据库为0，数据库范围在0-（database-1）之间");
		descMap.put("save", "################################ 快照  ################################### 保存数据到磁盘，格式如下:##   save <seconds> <changes>##   指出在多长时间内，有多少次更新操作，就将数据同步到数据文件rdb。#   相当于条件触发抓取快照，这个可以多个条件配合#    #   比如默认配置文件中的设置，就设置了三个条件##   save 900 1  900秒内至少有1个key被改变#   save 300 10  300秒内至少有300个key被改变#   save 60 10000  60秒内至少有10000个key被改变");
		descMap.put("rdbcompression", "# 存储至本地数据库时（持久化到rdb文件）是否压缩数据，默认为yes");
		descMap.put("dbfilename", "# 本地持久化数据库文件名，默认值为dump.rdb");
		descMap.put("dir", "# 工作目录## 数据库镜像备份的文件放置的路径。# 这里的路径跟文件名要分开配置是因为redis在进行备份时，先会将当前数据库的状态写入到一个临时文件中，等备份完成时，# 再把该该临时文件替换为上面所指定的文件，而这里的临时文件和上面所配置的备份文件都会放在这个指定的路径当中。# # AOF文件也会存放在这个目录下面# # 注意这里必须制定一个目录而不是文件");
		descMap.put("masterauth", "# 主从复制. 设置该数据库为其他数据库的从数据库. # 设置当本机为slav服务时，设置master服务的IP地址及端口，在Redis启动时，它会自动从master进行数据同步## slaveof <masterip> <masterport># 当master服务设置了密码保护时(用requirepass制定的密码)# slav服务连接master的密码# ");
		descMap.put("slave-serve-stale-data", "# 当从库同主机失去连接或者复制正在进行，从机库有两种运行方式：## 1) 如果slave-serve-stale-data设置为yes(默认设置)，从库会继续相应客户端的请求# # 2) 如果slave-serve-stale-data是指为no，出去INFO和SLAVOF命令之外的任何请求都会返回一个#    错误'SYNC with master in progress'#");
		descMap.put("repl-ping-slave-period", "# 从库会按照一个时间间隔向主库发送PINGs.可以通过repl-ping-slave-period设置这个时间间隔，默认是10秒");
		descMap.put("repl-timeout", "# repl-timeout 设置主库批量数据传输时间或者ping回复时间间隔，默认值是60秒# 一定要确保repl-timeout大于repl-ping-slave-period");
		descMap.put("requirepass", "# 设置客户端连接后进行任何其他指定前需要使用的密码。# 警告：因为redis速度相当快，所以在一台比较好的服务器下，一个外部的用户可以在一秒钟进行150K次的密码尝试，这意味着你需要指定非常非常强大的密码来防止暴力破解");
		descMap.put("maxclients", "# 设置同一时间最大客户端连接数，默认无限制，Redis可以同时打开的客户端连接数为Redis进程可以打开的最大文件描述符数，# 如果设置 maxclients 0，表示不作限制。# 当客户端连接数到达限制时，Redis会关闭新的连接并向客户端返回max number of clients reached错误信息");
		descMap.put("maxmemory", "# 指定Redis最大内存限制，Redis在启动时会把数据加载到内存中，达到最大内存后，Redis会先尝试清除已到期或即将到期的Key# Rdis同时也会移除空的list对象## 当此方法处理后，仍然到达最大内存设置，将无法再进行写入操作，但仍然可以进行读取操作# # 注意：Redis新的vm机制，会把Key存放内存，Value会存放在swap区## maxmemory的设置比较适合于把redis当作于类似memcached的缓存来使用，而不适合当做一个真实的DB。# 当把Redis当做一个真实的数据库使用的时候，内存使用将是一个很大的开销");
		descMap.put("maxmemory-policy", "");
		
	}
	
	
	private String key;
	private String value;
	private String description;
	public String getKey() {
		return key;
	} 
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String key) {
		try {
			this.description = descMap.get(key) == null ? "" : URLEncoder.encode(descMap.get(key) , "utf-8").replace("+", "%20") ;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
}
