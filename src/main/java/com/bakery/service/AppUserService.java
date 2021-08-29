//package com.bakery.service;
//
//import java.util.List;
//
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import com.bakery.model.AppUser;
//import com.bakery.repository.AppUserRepository;
//
//@Service
//public class AppUserService implements UserDetailsService{
//	
//
//	public AppUserService(AppUserRepository appUserRepository) {
//		super();
//		this.appUserRepository = appUserRepository;	
//				}
//
//	//	User user;
//	AppUserRepository appUserRepository;
//	PasswordEncoder passwordEncoder;
//
//	@Override
//	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
//         AppUser appUser = appUserRepository.findByUsername(name);
//         if (appUser == null)
//         throw new UsernameNotFoundException("invalid name/password");
//         String username = appUser.getUsername();
//         String password = appUser.getPassword();
//         List<SimpleGrantedAuthority> authority = appUser.getAuthority();
//         
//         User user = new User(username,password,authority);
//		return user;
//	}
//	
//	public void addUser(AppUser user) {
//		appUserRepository.insert(user);
//	}
//}
