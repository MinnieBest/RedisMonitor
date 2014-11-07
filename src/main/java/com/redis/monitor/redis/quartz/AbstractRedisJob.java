package com.redis.monitor.redis.quartz;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redis.monitor.RedisJedisPool;
import com.redis.monitor.json.FastJson;
import com.redis.monitor.redis.BasicRedisCacheServer;

public abstract class AbstractRedisJob implements RedisJob {
	public static final Logger logger = LoggerFactory.getLogger(AbstractRedisJob.class);
	public String preffix;
	
	protected String getFileName(String preffix,String uuid) {
		SimpleDateFormat dayFormat = new SimpleDateFormat("yyyyMMdd");
		String fileName = preffix + "-" + uuid + "-" + dayFormat.format(new Date()) + ".txt";
		return Thread.currentThread().getContextClassLoader().getResource("").getFile() + "/monitor-log/" + preffix + "/" + fileName;
	}
	
	protected String getDrirectoryPath(String preffix) {
		return  Thread.currentThread().getContextClassLoader().getResource("").getFile() + "/monitor-log/" + preffix;
	}
	
	protected String getDateStr() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(new Date());
	}
	
	public String getDateStr(long timeStmp) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(new Date(timeStmp));
	}
	
	protected void saveData() {
		Set<String> uuidSet = RedisJedisPool.getRedisUuids();
		if (uuidSet != null && uuidSet.size() > 0) {
			for (Iterator<String> itr = uuidSet.iterator(); itr.hasNext();) {
				String key = itr.next();
				FileOutputStream outputStream = null;
				File file = null;
				try {
					file = new File(getFileName(getPreffix(),key));
					File dirFile = new File(getDrirectoryPath(getPreffix()));
					if (!dirFile.exists()) {
						dirFile.mkdirs();
					}
					if (!file.exists()) {
						logger.debug("create monitor file,file name : {}",file.getName());
						file.createNewFile();
					}
					outputStream = new FileOutputStream(file, true);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

				BasicRedisCacheServer redisCacheServer = RedisJedisPool.getRedisCacheServer(key);
				Object obj = getSaveData(redisCacheServer);
				
				if (obj != null) {
					try {
						StringBuffer sb = new StringBuffer();
						if (obj instanceof List) {
							for (Object o : (List)obj) {
							  String json = FastJson.toJson(o);
							  sb.append(json + "\n");
							}
						} else {
							String json = FastJson.toJson(obj);
							sb.append(json).append("\n");
						}
						logger.debug("save date to monitor file,file name :{}, data : {} ",new Object[]{file.getName(),sb.toString()});
						IOUtils.write(sb.toString(),outputStream);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
			}
		}
	}
	
	protected abstract Object getSaveData(BasicRedisCacheServer redisCacheServer);

	public String getPreffix() {
		return preffix;
	}

	public void setPreffix(String preffix) {
		this.preffix = preffix;
	}
	
	

}
