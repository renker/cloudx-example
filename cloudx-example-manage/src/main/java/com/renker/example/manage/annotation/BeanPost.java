package com.renker.example.manage.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import javax.annotation.Resource;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.renker.cloud.security.rest.service.IUserRestService;
import com.renker.example.manage.controller.TestController;

@Component
public class BeanPost implements BeanPostProcessor{
	

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	/**
	 * 使用JDK动态代理注入到调用的地方
	 */
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		try {
			Field[] fields = bean.getClass().getDeclaredFields();
			Field.setAccessible(fields, true);
			for (Field field : fields) {
				if(field.isAnnotationPresent(CloudResource.class)){
					RestTempleProxy restTempleProxy = new RestTempleProxy(field);
					Object proxy = Proxy.newProxyInstance(restTempleProxy.getClass().getClassLoader(), new Class[]{IUserRestService.class}, restTempleProxy);
					field.set(bean, proxy);
				}
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return bean;
	}

}
