package com.renker.example.manage.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.renker.cloud.security.model.User;
import com.renker.cloud.security.rest.service.IUserRestService;
import com.renker.example.manage.annotation.CloudResource;
import com.renker.example.manage.service.person.IUserService;

@RestController
@RequestMapping("manage/rest")
public class TestController {
	@Resource
	private RestTemplate restTemplate;
	
	@CloudResource("example-server")
	public IUserRestService userRestService;
	
	@Resource
	private IUserService userService;
	
	@RequestMapping("hellow")
	public String hellow(){
		String res = restTemplate.getForEntity("http://example-server/hellow", String.class).getBody();
		return res;
	}
	
	@RequestMapping("test")
	public User test(){
		Map<String, String> params = new HashMap<>();
		params.put("id", "6E2FEA7CE7944222A54F0157160335E0");
		User user = restTemplate.getForObject("http://example-server/security/userRestService/findById?id={id}", User.class,params);
		return user;
	}
	
	@RequestMapping("findById")
	public User findById(){
		User user = restTemplate.getForObject("http://example-server/findById", User.class);
		return user;
	}
	
	@RequestMapping("proxy")
	public User proxyFindById(){
		User user = userRestService.findById("6E2FEA7CE7944222A54F0157160335E0");
		return user;
	}
	
	@RequestMapping("feign")
	public User feign() {
		User user = userService.findById("6E2FEA7CE7944222A54F0157160335E0");
		return user;
	}
	
}
