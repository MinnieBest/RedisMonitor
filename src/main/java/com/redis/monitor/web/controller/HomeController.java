package com.redis.monitor.web.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.redis.monitor.RedisCacheThreadLocal;
import com.redis.monitor.RedisInfoDetail;
import com.redis.monitor.RedisJedisPool;
import com.redis.monitor.RedisServer;
import com.redis.monitor.entity.Operate;

@Controller
public class HomeController  extends BaseProfileController {
	
	@RequestMapping(value="/index.htm",method=RequestMethod.GET)
	public ModelAndView index(HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		
		//TODO redis info信息加载
		List<RedisInfoDetail> rifList = redisManager.getRedisInfo();
		mv.addObject("rifList", rifList);
		
		//TODO redis memery加载
		
		//TODO redis keys加载
		
		//TODO redis slave log加载
		List<Operate> opList = redisManager.findAllOperateDetail();
		mv.addObject("opList", opList);
		
		Cookie cookie = new Cookie("uuid", RedisCacheThreadLocal.getUuid());
		response.addCookie(cookie);
		mv.setViewName("index");
		return mv;
	}
	
	@RequestMapping(value="/chartMemery.htm",method=RequestMethod.GET)
	public ModelAndView chartMemery() {
		ModelAndView mv = getJsonModelAndView();
		mv.addObject(redisManager.getMemeryInfo());
		return mv;
	}
	
	@RequestMapping(value="/chartKeys.htm",method=RequestMethod.GET)
	public ModelAndView chartKeys() {
		ModelAndView mv = getJsonModelAndView();
		mv.addObject(redisManager.getKeysSize());
		return mv;
	}
	
	
	
	@RequestMapping(value="/flushall.htm",method=RequestMethod.GET)
	public ModelAndView flushall() {
		ModelAndView mv = getJsonModelAndView();
		String result = redisManager.flushAll();
		mv.addObject("statu", result);
		mv.addObject("msg","刷新成功");
		return mv;
	}
	
	@RequestMapping(value="/flushDb.htm",method=RequestMethod.GET)
	public ModelAndView flushDb() {
		ModelAndView mv = getJsonModelAndView();
		String result = redisManager.flushDb();
		mv.addObject("statu", result);
		mv.addObject("msg","刷新成功");
		return mv;
	}
	

}
