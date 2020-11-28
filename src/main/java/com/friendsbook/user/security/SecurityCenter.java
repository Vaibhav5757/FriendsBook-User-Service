package com.friendsbook.user.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityCenter extends WebSecurityConfigurerAdapter{
	
	@Value("${SECURITY-USER-NAME}")
	private String basicSecurityUser;
	@Value("${SECURITY-USER-PASSWORD}")
	private String basicSecurityPassword;
	
	// Currently I'm using basic auth as it's the easiest to implement.
	// will update in future to more reliable methods which can be used among microservices
	// like 'validation using keys'
	// please note OAuth is not valid here as token will expire after some time and this being
	// an internal microservice(and not external) we cannot generate token again and again
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.anyRequest()
			.authenticated()
			.and()
			.httpBasic();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser(basicSecurityUser)
			.password(this.passwordEncoder().encode(basicSecurityPassword));
	}
	
	// makes certain APIs public
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
			.antMatchers("/wake-up**")
			.antMatchers("/user/sign-up");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
