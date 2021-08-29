package com.bakery.controllers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bakery.model.AppUser;
import com.bakery.model.JWTRequest;
import com.bakery.model.JWTResponse;
import com.bakery.model.Role;
import com.bakery.repository.AppUserRepository;
import com.bakery.repository.RoleRepository;
import com.bakery.utility.JWTTokenUtil;


@RestController
@CrossOrigin
public class JWTAuthController {
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	AppUserRepository appUserRepository;

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	JWTTokenUtil jwtToken;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	@PostMapping("/api/auth/register")
	public ResponseEntity<?> registerUser(@RequestBody JWTRequest request){
		String username = request.getUsername();
		String password = passwordEncoder.encode(request.getPassword());
		String email = request.getEmail();
		Role roleone = roleRepository.findByRoleType("MODERATOR").get();
//		Role roletwo  = roleRepository.findByRoleType("ADMIN").get();
//		Role roleone = new Role(RoleType.USER);
//		Role roletwo = new Role(RoleType.ADMIN);
		Set<Role> roles = new HashSet<>(Arrays.asList(roleone));
		AppUser appuser = new AppUser(username,password,email);
		appuser.setRoles(roles);
		appUserRepository.insert(appuser);
		return ResponseEntity.ok("User registered!");
	}
	
	@PostMapping("/api/auth/login")
	public ResponseEntity<JWTResponse> loginUser(@RequestBody JWTRequest request){
		
		String username = request.getUsername();
		String password = request.getPassword();
		String email = request.getEmail();
		authenticate(username,password);
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		String token = jwtToken.generateToken(userDetails);
		AppUser user = appUserRepository.findByUsername(username);
		JWTResponse jwtResponse = new JWTResponse(token,user.getEmail(),user.getRoles());
		ResponseEntity<JWTResponse> response = new ResponseEntity<JWTResponse>(jwtResponse, HttpStatus.OK);
		return response;

	}
	private void authenticate(String username,String password) {
	try	{
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
	}catch(DisabledException e) {
		throw new DisabledException("User Disabled");
	}catch(BadCredentialsException e) {
		throw new BadCredentialsException("BadCredentialsException");
	}
	}
}
