package com.bakery.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bakery.model.AppUser;
import com.bakery.model.Role;
import com.bakery.repository.AppUserRepository;

@Service
public class JWTUserDetailsServiceImpl implements UserDetailsService {

//	Function<Integer, String> fun = 
	@Autowired
	AppUserRepository appUserRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		AppUser appuser = appUserRepository.findByUsername(username);
		if(appuser == null) {
			throw new UsernameNotFoundException("user not found");
		}
		String uname = appuser.getUsername();
		String password = appuser.getPassword();
		Set<Role> roles = appuser.getRoles();
		List<GrantedAuthority> authorities = roles
				.stream()
				.map((role)->{
				String roletype = role.getRoleType().name();
				return new SimpleGrantedAuthority(roletype);
			})
			.collect(Collectors.toList());
		User user = new User(uname, password,authorities);
		return user;
		
	}

}
