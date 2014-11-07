package com.redis.monitor;

/**
 * 服务关闭,如果内存配置有更新,将配置列表持久化到硬盘上
 * @author F1
 *
 */
public class AppHock {
	public static void hockRedisServerConfig() {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				RedisConfigInit.rewriteConfigXml();
			}
		});
		Runtime.getRuntime().addShutdownHook(t);
	}
}
