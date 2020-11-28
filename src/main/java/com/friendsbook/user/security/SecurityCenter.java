package com.friendsbook.user.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityCenter extends WebSecurityConfigurerAdapter{
	
	// Currently I'm using basic auth as it's the easiest to implement
	// will update in future to more reliable methods which can be used among microservices
	// like validation using keys
	// please note OAuth is not valid here as token will expire after some time and this being
	// an internal microservice(and not external) we cannot generate token again and again
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()// authorize requests
			.anyRequest()// any request
			.authenticated()// and authenticate
			.and()
			.httpBasic();// use http basic for authentication
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
