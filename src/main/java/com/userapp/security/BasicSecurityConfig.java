package com.userapp.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class BasicSecurityConfig {

	/**
	 * Purpose: stores the users in memory -for authentication and authorization purpose
	 * @return InMemoryUserDetailsManager
	 */
	@Bean
	public InMemoryUserDetailsManager userDetailsManager() {
		UserDetails user1 = User.builder().username("amit").password("{noop}rathod").roles("EMPLOYEE").build();
		//{noop} - allows to save password in the string format without any encryption
		UserDetails user2 = User.builder().username("shreya").password("{noop}rathod").roles("EMPLOYEE", "MANAGER").build();

		UserDetails user3 = User.builder().username("kajal").password("{noop}rathod").roles("EMPLOYEE", "MANAGER", "ADMIN")
				.build();

		return new InMemoryUserDetailsManager(List.of(user1, user2, user3));
	}
	
	/*
	 * Purpose: Authorization of the users to allow accesss based on the role
	 */
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(configurer -> 
		configurer
		.requestMatchers(HttpMethod.GET, "/api/users").hasRole("EMPLOYEE") //employee can get a single user
		.requestMatchers(HttpMethod.GET, "/api/users/*").hasAnyRole("EMPLOYEE", "MANAGER") // manager and employee can get single and multiple user
		.requestMatchers(HttpMethod.POST, "/api/users").hasRole("MANAGER") //manager can add user
		.requestMatchers(HttpMethod.PATCH, "/api/users/*").hasRole("MANAGER") //manager can update the user partially
		.requestMatchers(HttpMethod.DELETE, "/api/users/*").hasRole("ADMIN") //admin can only remove the user
		);
		
		/*
		 * IMPORTANT:
		 * "/api/users"	Only /api/users
		 * "/api/users/*"	Matches /api/users/1, /api/users/99, etc.
		 * "/api/users/**"	Matches everything under /api/users/, including /api/users/1/profile
         * "/api/users/{id}"	❌ Won't work — not parsed as path variables here. Use wildcard (*)
		 */
		
		//uses basic auth
		http.httpBasic(Customizer.withDefaults());
		
		
		//disable csrf protection for this stateless api
		http.csrf(csrf -> csrf.disable());
		
		return http.build();
		
	}
}
