package com.friendsbook.user.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.friendsbook.user.model.User;
import com.friendsbook.user.repository.UsersRepository;
import com.friendsbook.user.util.ApiException;
import com.friendsbook.user.util.LoginBody;

@Service
public class UserService {
	
	@Autowired
	private UsersRepository usrRepo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	private Logger logger = LoggerFactory.getLogger(UserService.class);
	
	// add a new User to database
	public ResponseEntity<String> createUser(User obj) throws ApiException{
		
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
			return new ResponseEntity<String>("User Created Successfully", HttpStatus.OK);
		}catch(Exception err) {
			logger.error(err.getMessage());
		}
		return null;
	}
	
	public ResponseEntity<User> login(LoginBody obj) throws ApiException{
		User target = this.usrRepo.findByEmail(obj.getEmail());
		// check if user exists with this email address or not
		if(target == null)
			throw new ApiException("No Account exists with the email " + obj.getEmail());
		// check if password is correct or not
		if(new BCryptPasswordEncoder().matches(obj.getPassword(), target.getPassword()))
			return new ResponseEntity<User>(target, HttpStatus.OK);
		else
			throw new ApiException("Incorrect password for email " + obj.getEmail());// throw error if password mismatch
	}

}
