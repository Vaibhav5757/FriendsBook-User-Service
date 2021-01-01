package com.friendsbook.user.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.friendsbook.user.service.AdminService;
import com.friendsbook.user.util.ApiException;
import com.friendsbook.user.util.JustEmailBody;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminSvc;
	
	@PutMapping("/update-to-admin")
	public ResponseEntity<String> updateToAdmin(@Valid @RequestBody JustEmailBody obj) throws ApiException{
		return this.adminSvc.updateToAdmin(obj);
	}
}
