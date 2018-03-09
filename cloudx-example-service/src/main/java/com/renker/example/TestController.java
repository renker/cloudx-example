package com.renker.example;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renker.cloud.security.mapper.UserMapper;
import com.renker.cloud.security.model.User;

@RestController
public class TestController {
	@Resource
	private UserMapper userMapper;
	
	
	@RequestMapping("hellow")
	public String hellow(){
		return "success";
	}
	
	@RequestMapping("findById")
	public User findById(){
		return userMapper.selectByPrimaryKey("6E2FEA7CE7944222A54F0157160335E0");
	}
}
