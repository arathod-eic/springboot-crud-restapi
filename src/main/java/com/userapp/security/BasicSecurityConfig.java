package com.userapp.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class BasicSecurityConfig {

	@Bean
	public InMemoryUserDetailsManager userDetailsManager() {
		UserDetails user1 = User.builder().username("amit").password("{noop}rathod").roles("EMPLOYEE").build();

		UserDetails user2 = User.builder().username("shreya").password("{noop}rathod").roles("EMPLOYEE", "MANAGER").build();

		UserDetails user3 = User.builder().username("kajal").password("{noop}rathod").roles("EMPLOYEE", "MANAGER", "ADMIN")
				.build();

		return new InMemoryUserDetailsManager(List.of(user1, user2, user3));
	}
}
