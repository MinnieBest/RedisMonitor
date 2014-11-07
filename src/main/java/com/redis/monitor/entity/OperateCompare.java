package com.redis.monitor.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class OperateCompare implements Comparator<Operate> {


	@Override
	public int compare(Operate o1, Operate o2) {
		try {
			Date o1Date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(o1.getCreateTime());
			Date o2Date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(o2.getCreateTime());
			
			if (o1Date.getTime() > o2Date.getTime()) {
				return -1;
			} else if (o1Date.getTime() == o2Date.getTime()) {
				return 0;
			} else {
				return 1;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

}
