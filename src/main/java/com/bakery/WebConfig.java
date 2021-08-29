//package com.bakery;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
////@Configuration
//public class WebConfig extends WebSecurityConfigurerAdapter{
//
//	//authentication - inmemory authentication
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
//		.withUser("admin")
//		.password(passwordEncoder().encode("admin123"))
//		.roles("ADMIN","USER")
//		.and()
//		.withUser("pawan")
//		.password(passwordEncoder().encode("pawan123"))
//		.roles("USER");
//	}
//
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//	
//	//Authorization 
//	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		// TODO Auto-generated method stub
//		http.authorizeRequests()
//		.antMatchers("/").permitAll()
//		.antMatchers("/user/**").hasAnyRole("ADMIN","USER")
//		.antMatchers("/**","/admin/**").hasAnyRole("ADMIN")
//		.and()
//		.formLogin();
//	}
//
//	
//}
