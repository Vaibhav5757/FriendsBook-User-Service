package com.friendsbook.user.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.friendsbook.user.model.User;
import com.friendsbook.user.service.UserService;
import com.friendsbook.user.util.ApiException;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService usrSvc;
	
	@PostMapping("/sign-up")
	public ResponseEntity<String> addUser(@Valid @RequestBody User obj) throws ApiException{
		return this.usrSvc.createUser(obj);
	}
}
