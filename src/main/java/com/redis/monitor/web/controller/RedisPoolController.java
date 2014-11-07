package com.redis.monitor.web.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.redis.monitor.RedisJedisPool;
import com.redis.monitor.RedisServer;

@Controller
@RequestMapping("/redis/pool")
public class RedisPoolController extends BaseProfileController {
	
	@RequestMapping(value="/list.htm",method=RequestMethod.POST)
	public void redisPoolList() {
		
	}
	
	@RequestMapping(value="/checked.htm",method=RequestMethod.GET)
	public void checkedPool(HttpServletRequest request,HttpServletResponse response,@PathVariable String uuid) {
		Cookie cookie = new Cookie("uuid", uuid);
		response.addCookie(cookie);
	}
}

