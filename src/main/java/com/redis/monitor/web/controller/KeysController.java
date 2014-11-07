package com.redis.monitor.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.redis.monitor.Constants;

@Controller
public class KeysController extends BaseProfileController{
	private static final Logger logger = LoggerFactory.getLogger(KeysController.class ) ;
	
	@RequestMapping("/keys.htm")
	public ModelAndView keys(){
		ModelAndView mv = new ModelAndView("keys") ;
		return mv ;
	}
	
	@RequestMapping("/keys/getByPattern.htm")
	public ModelAndView getByPattern(@RequestParam(required=false) String patternKey  , @RequestParam(defaultValue="100") String showNum){
		try {
			String uuid  = getUuid() ;
			Set<String> keySet = redisManager.getKeysByPattern(uuid, patternKey) ;
			List<String> keyList = new ArrayList<String>() ;
			int i = 0 ;
			int num = Integer.parseInt(showNum) ;
			for(String key : keySet){
				if(i++ < num){
					keyList.add(key) ;
				} else {
					break ;
				}
			} 
			
			return putModel(keyList) ;
		} catch (Exception e) {
			logger.error("" , e); 
		}
		return null ; 
	}
	
	@RequestMapping("/keys/value.htm")
	public ModelAndView value(@RequestParam String key){
		Map<String , Object> res = new HashMap<String , Object>() ;
		res.put(Constants.RES_STATUS, 0) ;
		boolean isOk = false ;
		
		if (!isOk) {
			String value = redisManager.get(key) ;
			if (value != null ) {
				res.put("type", "string");
				res.put("value", value);
				isOk = true ;
			}
		}
		if (!isOk) {
			Map<String , String> map = redisManager.getMap(key) ;
			if (MapUtils.isNotEmpty(map)) {
				res.put("type", "map");
				res.put("value", map);
				isOk = true ;
			}
		}
		if (!isOk) {
			List<String> list = redisManager.getList(key) ;
			if (list != null && list.size() > 0 ) {
				res.put("type", "list");
				res.put("value", list);
				isOk = true ;
			}
		}
		if (!isOk) {
			Set<String> set = redisManager.getSet(key) ;
			if (set != null && set.size() > 0 ) {
				res.put("type", "set");
				res.put("value", set);
				isOk = true ;
			}
		}
		
		if(isOk){
			long ttl = redisManager.ttl(key) ;
			res.put("ttl", ttl) ;
		}
		
		try{
		} catch(Exception e ) {
			logger.error("" , e); 
			res.put(Constants.RES_STATUS, 1) ;
			res.put(Constants.RES_MSG , "查询出错") ;
		}
		return putModel(res) ;
	}
	
	@RequestMapping("/keys/updateString.htm")
	public ModelAndView updateString(@RequestParam String key , @RequestParam String value){
		Map<String , Object> data = new HashMap<String , Object>() ;
		data.put(Constants.RES_STATUS, 0) ;
		try{
			redisManager.set(key, value) ;
		} catch(Exception e) {
			logger.error("" , e); 
			data.put(Constants.RES_STATUS, 1) ;
			data.put(Constants.RES_MSG , "修改出错") ;
		} 
		return putModel(data) ;
	}
	
	@RequestMapping("/keys/deleteString.htm")
	public ModelAndView deleteString(@RequestParam String key) {
		Map<String , Object> data = new HashMap<String , Object>() ;
		data.put(Constants.RES_STATUS, 0) ;
		try{
			redisManager.delete(key) ;
		} catch(Exception e) {
			logger.error("" , e); 
			data.put(Constants.RES_STATUS, 1) ;
			data.put(Constants.RES_MSG , "删除出错") ;
		} 
		return putModel(data) ;
	}
}
