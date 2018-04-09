package com.renker.example.manage.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URL;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import feign.Target;

public class RestTempleProxy implements InvocationHandler{
	
	private static RestTemplate restTemplate = null;
	private Object target;
	

	@Override
	public Object invoke(Object obj, Method method, Object[] args) throws Throwable {
		
		System.out.println("调用开始 ----------------------------------");
		URL url = new URL("http", "example-server", "example-server/findById");
		CloudResource cloudResource = ((Field) target).getAnnotation(CloudResource.class);
		
		RequestMapping controllerRequestMapping = getAnnotation(method.getDeclaringClass(),RequestMapping.class);
		
		RequestMapping methodRequestMapping = method.getAnnotation(RequestMapping.class);
		
		URI uri = new URI("http", cloudResource.value(), "/"+controllerRequestMapping.value()[0]+"/"+methodRequestMapping.value()[0], null);
		
		Object result = getRestTemplate().getForObject(uri, method.getReturnType());
		return result;
	}
	
	public void baseUrl(Method method,List<String> url){
		RequestMapping requestMapping = getAnnotation(method.getDeclaringClass(),RequestMapping.class);
		if(requestMapping != null && StringUtils.isBlank(requestMapping.path().toString())){
			
		}
		
	}
	
	public static <T extends Annotation> T getAnnotation(Class<?> obj,Class<T> target){
		if(obj.isAnnotationPresent(target)){
			return (T) obj.getAnnotation(target);
		}else{
			return null;
		}
		
	}

	public RestTempleProxy(Object target) {
		super();
		this.target = target;
	}

	private  static RestTemplate getRestTemplate() {
		if(restTemplate == null){
			return SpringContextHolder.getBean(RestTemplate.class);
		}
		return null;
	}


}
