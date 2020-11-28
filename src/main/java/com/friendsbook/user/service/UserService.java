package com.friendsbook.user.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.friendsbook.user.model.User;
import com.friendsbook.user.repository.UsersRepository;
import com.friendsbook.user.util.ApiResponse;
import com.friendsbook.user.util.ApiException;

@Service
public class UserService {
	
	@Autowired
	private UsersRepository usrRepo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	private Logger logger = LoggerFactory.getLogger(UserService.class);
	
	// add a new User to database
	public ResponseEntity<ApiResponse> createUser(User obj) throws ApiException{
		
		// check if email is already in use or not
		User target = this.usrRepo.findByEmail(obj.getEmail());
		if(target != null)
			throw new ApiException("Email " + obj.getEmail() + " is already in use");
		
		// Hash the password
		obj.setPassword(this.encoder.encode(obj.getPassword()));
		
		// set joined date and last password updated
		obj.setJoined(new Date());
		obj.setLastPasswordUpdated(new Date());
		
		// add User Role by Default
		obj.addRoles("ROLE_USER");
		
		// save the user
		try {
			this.usrRepo.save(obj);
			ApiResponse response = new ApiResponse("User Created Successfully");
			return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
		}catch(Exception err) {
			logger.error(err.getMessage());
		}
		return null;
	}

}
