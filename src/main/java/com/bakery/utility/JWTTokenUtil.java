package com.bakery.utility;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTTokenUtil {

	@Value("${jwt.secret}")
	String jwtSecret;
	@Value("${jwt.expiry}")
	String jwtExpiry;

	public boolean validateToken(String jwtToken) {
		try {
			Jwts.parser()
			.setSigningKey(jwtSecret)
			.parseClaimsJws(jwtToken);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	public String getUsernameFromToken(String jwtToken) {
		String username = Jwts.parser().setSigningKey(jwtSecret)
			 .parseClaimsJws(jwtToken)
			 .getBody()
			 .getSubject();
		return username;
			 
	}
	public String generateToken(UserDetails user) {
		
		//Jwts is factory class
		return Jwts.builder()
				.setSubject(user.getUsername())
				.setIssuedAt(new Date())
//				.setExpiration((int)(new Date().getTime())+jwtExpiry)
				.signWith(SignatureAlgorithm.HS512,jwtSecret)
				.compact();// serialize 
	}

}