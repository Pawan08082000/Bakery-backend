//package com.bakery.controllers;
//
//import java.util.Arrays;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.bakery.model.AppUser;
//import com.bakery.service.AppUserService;
//
//
//
//@RestController	
//public class AppUserController {
//
//	@Autowired
//	AppUserService appUserService;
//	
//	@Autowired
//	PasswordEncoder passwordEncoder;
//	
////	@GetMapping("/")
////	public String greet() {
////		return "Welcome Spring!";
////	}
//	
////	@GetMapping("/")
////	public String adduser() {
////		SimpleGrantedAuthority auth1 = new SimpleGrantedAuthority("ADMIN");
////		SimpleGrantedAuthority auth2 = new SimpleGrantedAuthority("USER");
////		String password = passwordEncoder.encode("pass123");
////		AppUser appUser = new AppUser(1,"pawan",password,Arrays.asList(auth1,auth2));
////		appUserService.addUser(appUser);
////		return "User Added!";
////	}
//	@GetMapping("/")
//    public String addUser() {
//        SimpleGrantedAuthority auth1 =new SimpleGrantedAuthority("ADMIN");
//        SimpleGrantedAuthority auth2 =new SimpleGrantedAuthority("USER");
//        String password = passwordEncoder.encode("pass123");
//        AppUser appUser = new AppUser(2,"Pawan",password,Arrays.asList(auth1));
//        appUserService.addUser(appUser);
//        return "User added";
//    }
//	
//	@GetMapping("/user/allbook")
//	public String showBooks() {
//		return "showing all books";
//	}
//	
//	@GetMapping("/admin/editBook")
//	public String editBook() {
//		return "book edited";
//	}
//	
//	@GetMapping("/user/bookByAuthor/{author}")
//	public String showByAuthor(@PathVariable("author")String author ) {
//		return author;
//	
//	}
//}
