package com.bakery.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection="appusers")
public class AppUser {
	@Id
	private String userid;
	@Indexed(unique = true)
	private String username;
	private String password;
	@Indexed(unique = true)
	private String email;
	@DBRef
	private Set<Role> roles = new HashSet<>();
	private String token;
	
	public AppUser(String username, String password,String email) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	public ArrayList<Object> AppUserResponse(String email,Set<Role> role, String token ) {
		this.email = email;
		this.roles = role;
		this.token = token;
		ArrayList<Object> arr = new ArrayList<Object>();
		arr. add(this.email); 
		arr. add(this.roles);
		arr.add(this.token);

		return arr;
	}
	

}
