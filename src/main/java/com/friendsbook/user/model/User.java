package com.friendsbook.user.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="users")
@Data
@NoArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@NotBlank
	private String name, password;
	
	@NotBlank
	@Email
	private String email;
	
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> roles = new ArrayList<String>();
	
	@ElementCollection(fetch = FetchType.LAZY)
	private List<String> following = new ArrayList<String>();
	
	
	@ElementCollection(fetch = FetchType.LAZY)
	private List<String> followers = new ArrayList<String>();
	
	private Date joined, lastPasswordUpdated;
	
	private boolean isAccountLocked;
	
	public void addRoles(String role) {
		this.roles.add(role);
	}

	public User(@NotBlank String name, @NotBlank String password, @NotBlank @Email String email) {
		super();
		this.name = name;
		this.password = password;
		this.email = email;
	}
}
