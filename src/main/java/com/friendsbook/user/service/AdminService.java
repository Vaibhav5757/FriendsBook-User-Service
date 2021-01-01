package com.friendsbook.user.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.friendsbook.user.model.User;
import com.friendsbook.user.repository.UsersRepository;
import com.friendsbook.user.util.ApiException;
import com.friendsbook.user.util.JustEmailBody;

@Service
public class AdminService {
	
	@Autowired
	private UsersRepository usrRepo;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// Update normal User to Admin
	public ResponseEntity<String> updateToAdmin(JustEmailBody obj) throws ApiException {
		
		User target = this.usrRepo.findByEmail(obj.getEmail());
		// check if user exists with this email address or not
		if(target == null)
			throw new ApiException("No Account exists with the email " + obj.getEmail());
		
		// Add admin Role
		target.addRoles("ROLE_ADMIN");
		try {
			this.usrRepo.save(target);
			return new ResponseEntity<String>("User upgraded to admin privileges", HttpStatus.OK);
		}catch(Exception err) {
			logger.error(err.getMessage());
		}
		return null;
	}
	
}
