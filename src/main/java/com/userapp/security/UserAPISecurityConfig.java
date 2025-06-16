package com.userapp.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.vote.RoleHierarchyVoter;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

@EnableWebSecurity
@Configuration
@Order(2)
public class UserAPISecurityConfig {
	
	@Bean
	public UserDetailsService userDetailsService(DataSource dataSource) {
		return new JdbcUserDetailsManager(dataSource);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http, DefaultWebSecurityExpressionHandler expressionHandler) throws Exception {
		http.securityMatcher("/api/users/**").		
		authorizeHttpRequests(configurer -> 
		configurer
		.requestMatchers(HttpMethod.GET, "/api/users").hasRole("EMPLOYEE") //Employee can get all the user
		.requestMatchers(HttpMethod.GET, "/api/users/*").hasAnyRole("EMPLOYEE", "MANAGER") // manager and employee can get single and multiple user
		.requestMatchers(HttpMethod.POST, "/api/users").hasRole("MANAGER") //manager can add user
		.requestMatchers(HttpMethod.PATCH, "/api/users/*").hasRole("MANAGER") //manager can update the user partially
		.requestMatchers(HttpMethod.DELETE, "/api/users/*").hasRole("ADMIN") //admin can only remove the user
		);

		http.httpBasic(Customizer.withDefaults());
		http.csrf(csrf -> csrf.disable());
		
		return http.build();
	}
	
	@Bean
	static RoleHierarchy roleHierarchy() {
		return RoleHierarchyImpl.withDefaultRolePrefix()
				.role("ADMIN").implies("MANAGER")
				.role("MANAGER").implies("USER")
				.build();
	}
	
}
