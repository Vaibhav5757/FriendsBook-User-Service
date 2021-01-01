package com.friendsbook.user.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.friendsbook.user.model.User;
import com.friendsbook.user.service.UserService;

@Component
public class ApplicationInitializer implements CommandLineRunner {
	
	@Autowired
	private UserService usrSvc;

	@Override
	public void run(String... args) throws Exception {
		// Add a default user with admin privileges
		User admin = new User("Vaibhav","password","vaibhav@gmail.com");
		admin.addRoles("ROLE_ADMIN");
		this.usrSvc.createUser(admin);
	}

}
