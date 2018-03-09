package com.junit;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.renker.cloud.security.model.User;
import com.renker.cloud.security.rest.service.IUserRestService;
import com.renker.example.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class DataSourceTet {
	@Resource
	private IUserRestService userService;
	
	@Test
	public void db(){
		User user = userService.findById("6E2FEA7CE7944222A54F0157160335E0");
		System.out.println(user.getAccount());
	}
}
