package com.friendsbook.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/wake-up")
public class RootController {

	@Autowired
	private RestTemplate http;
	
	private Logger logger = LoggerFactory.getLogger(RootController.class);

	@GetMapping("/")
	public String wakeUp() {
		logger.info("Wake up call recieved!!!");
		return "Yo, I Woke up!!!";
	}

	@GetMapping("/front-end")
	public String callToFrontEnd(){
		ResponseEntity<String> response = this.http.exchange("http://FRONT-END-SERVICE/wake-up/", HttpMethod.GET, null, String.class);
		return response.getBody();
	}

}
