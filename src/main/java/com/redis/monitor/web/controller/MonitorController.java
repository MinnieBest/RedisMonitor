package com.redis.monitor.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.redis.monitor.Constants;
import com.redis.monitor.SocketMonitor;

@Controller
public class MonitorController extends BaseProfileController{

	@RequestMapping("/monitor.htm")
	public ModelAndView toMonitor(){
		ModelAndView mv = new ModelAndView("monitor") ;
		return mv ;
	}
	
	@RequestMapping("/monitor/data.htm")
	public ModelAndView monitorData(){
		try {
			List<String> data = null ;
			data = SocketMonitor.getDataList() ;
			
			return putModel(data) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null ; 
	}
	
	@RequestMapping("/monitor/start.htm")
	public ModelAndView start(){
		String uuid  = getUuid() ;
		Map<String , Object> res = new HashMap<String , Object>() ;
		res.put(Constants.RES_STATUS, 0) ;
		try{
			redisManager.startMonitor(uuid) ;	
		} catch(Exception e ) {
			res.put(Constants.RES_STATUS, 1) ;
			res.put(Constants.RES_MSG , "开始监控时发生错误") ;
		}
		return putModel(res) ; 
	}
	
	@RequestMapping("/monitor/stop.htm")
	public ModelAndView stop(){
		Map<String , Object> res = new HashMap<String , Object>() ;
		res.put(Constants.RES_STATUS, 0) ;
		try{
			SocketMonitor.disconnectClient();
		} catch(Exception e ) {
			res.put(Constants.RES_STATUS, 1) ;
			res.put(Constants.RES_MSG , "停止监控时发生错误") ;
		}
		return putModel(res) ;
	}
}
