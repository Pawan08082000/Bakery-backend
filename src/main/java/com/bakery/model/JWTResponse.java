package com.bakery.model;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JWTResponse {
	private String token;
	private String email;
	private Set<Role> roles = new HashSet<>();
}
