package com.renker.example;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;


@SpringBootApplication
@ComponentScan(basePackages={"com.renker.cloud","com.renker.example"})
@EnableTransactionManagement
@EnableDiscoveryClient
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Value("${spring.datasource.type}")
	private String type;
	
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.master")
	public DataSource dataSource(){
		return DataSourceBuilder.create().type(DruidDataSource.class).build();
	}
	
}
