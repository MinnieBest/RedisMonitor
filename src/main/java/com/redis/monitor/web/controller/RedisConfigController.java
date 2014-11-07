package com.redis.monitor.web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.redis.monitor.RedisConfig;

@Controller
@RequestMapping(value="/config")
public class RedisConfigController extends BaseProfileController{
	
	@RequestMapping(value="/toConfigDetail.htm",method=RequestMethod.GET)
	public String toConfigDetail() {
		return "redisConfig";
	}
	
	@RequestMapping(value="/configDetail.htm",method=RequestMethod.GET)
	public ModelAndView configDetail() {
		ModelAndView mv = getJsonModelAndView();
		List<RedisConfig> rcList = redisManager.getRedisConfigXmlDetail();
		mv.addObject("rows",rcList); 
		mv.addObject("total",rcList.size());
		return mv;
	}

}
