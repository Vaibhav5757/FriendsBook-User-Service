package com.friendsbook.user.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateProvider {

	private Logger logger = LoggerFactory.getLogger(RestTemplateProvider.class);

	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		logger.info("In Rest Template Constructor");
		return new RestTemplate();
	}
}
