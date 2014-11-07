package com.redis.monitor.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


public class FileUtils {
	public static String getDrirectoryPath(String preffix) {
		return  Thread.currentThread().getContextClassLoader().getResource("").getFile() + "/monitor-log/" + preffix;
	}
	
	public static String getFileName(String preffix,String uuid) {
		SimpleDateFormat dayFormat = new SimpleDateFormat("yyyyMMdd");
		String fileName = preffix + "-" + uuid + "-" + dayFormat.format(new Date()) + ".txt";
		return Thread.currentThread().getContextClassLoader().getResource("").getFile() + "/monitor-log/" + preffix + "/" + fileName;
	}
	
	public static File getFile(String preffix,String uuid) {
		String fileName = getFileName(preffix, uuid);
		return new File(fileName);
	}
}
