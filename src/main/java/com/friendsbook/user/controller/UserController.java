package com.friendsbook.user.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.friendsbook.user.model.User;
import com.friendsbook.user.service.UserService;
import com.friendsbook.user.util.ApiException;
import com.friendsbook.user.util.FollowRequestBody;
import com.friendsbook.user.util.LoginBody;
import com.friendsbook.user.util.PasswordChangeBody;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService usrSvc;
	
	@PostMapping("/sign-up")
	public ResponseEntity<String> addUser(@Valid @RequestBody User obj) throws ApiException{
		return this.usrSvc.createUser(obj);
	}
	
	@PostMapping("/log-in")
	public ResponseEntity<User> login(@Valid @RequestBody LoginBody obj) throws ApiException{
		return this.usrSvc.login(obj);
	}
	
	@PutMapping("/change-password")
	public ResponseEntity<String> updatePassword(@Valid @RequestBody PasswordChangeBody obj)throws ApiException {
		return this.usrSvc.updatePassword(obj);
	}

	@PostMapping("/follow")
	public ResponseEntity<String> follow(@Valid @RequestBody FollowRequestBody obj)throws ApiException {
		return this.usrSvc.follow(obj);
	}
}
