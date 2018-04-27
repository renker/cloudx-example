package com.renker.example.trace2;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("trace")
public class TestController {
	@RequestMapping("hellow")
	public String hellow(){
		return "trace2 return：hellow";
	}
}
