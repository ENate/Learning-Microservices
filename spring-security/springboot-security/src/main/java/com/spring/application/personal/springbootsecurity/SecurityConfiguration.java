package com.spring.application.personal.springbootsecurity;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	// Create a datasource data bean and pass it via
	@Autowired
	DataSource dataSource;
	// Override the configure method and pass instance of Authentication builder Manager Builder
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.jdbcAuthentication()
		.dataSource(dataSource); // create schema for H" data bases using default schema.
		/** In case we want spring security to create users
		.withDefaultSchema()
		.withUser(
				User.withUsername("user")
				.password("{noop}password")
				.roles("USER")
				)
				
		.withUser(
				User.withUsername("admin")
				.password("{noop}admin")
				.roles("ADMIN")
				);
				*/
	}
	
	// Specify roles which has to be authenticated.
	// Authorize all request and do hand matchers to specific user roles. Also included are specifications for different users.
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		// Look up users and check passwords.
		http.authorizeRequests()
								.antMatchers("/admin").hasRole("ADMIN")
								.antMatchers("/user").hasAnyRole("ADMIN", "USER")
								.antMatchers("/").permitAll()
								.and().formLogin();
	}
	/** 
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
*/
}
