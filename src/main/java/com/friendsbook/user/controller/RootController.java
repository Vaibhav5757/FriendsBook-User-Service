package com.friendsbook.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wake-up")
public class RootController {
	
	private Logger logger = LoggerFactory.getLogger(RootController.class);

	@GetMapping("/")
	public String wakeUp() {
		logger.info("Wake up call recieved!!!");
		return "Yo, I Woke up!!!";
	}
}
