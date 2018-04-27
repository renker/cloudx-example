package com.renker.example.trace1;

import javax.annotation.Resource;

import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("trace")
public class TestController {
	@Resource
	private RestTemplate restTemplate;
	@Resource
	private Tracer tracer;
	
	
	@RequestMapping("hellow")
	public String hellow(){
//		Span span = tracer.createSpan("hellow span");
//		Span span = tracer.getCurrentSpan();
		try {
			
//			span.tag(Span.SPAN_LOCAL_COMPONENT_TAG_NAME, "call trace2");
			String res = restTemplate.getForObject("http://example-trace2/trace/hellow", String.class);
			return res;
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
//			span.tag(Span.SPAN_PEER_SERVICE_TAG_NAME, "call trace2");
//			span.logEvent(Span.CLIENT_RECV);
//			tracer.close(span);
		}
		return "error";
	}
}
