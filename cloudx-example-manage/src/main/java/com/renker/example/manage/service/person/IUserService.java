package com.renker.example.manage.service.person;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.renker.cloud.security.rest.service.IUserRestService;

@FeignClient(name="example-server")
public interface IUserService extends IUserRestService{

}
