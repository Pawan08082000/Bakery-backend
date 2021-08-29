package com.bakery.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bakery.utility.JWTTokenUtil;

@Component
public class JWTRequestfFilter extends OncePerRequestFilter {

	@Autowired
	JWTTokenUtil jwtTokenUtil;
	
	@Autowired
	UserDetailsService jwtUserDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
		//check the request
		String jwtToken = parseJwt(request);
		// validate the token
		if(jwtToken!=null && jwtTokenUtil.validateToken(jwtToken) ) {
			// if valid token strip and get username
			String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
			// check if name exists in db
			UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);
			
			UsernamePasswordAuthenticationToken authToken = 
					new UsernamePasswordAuthenticationToken(userDetails.getUsername(),null,userDetails.getAuthorities());
			
			authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			//After setting the Authentication in the context, we specify
			// that the current user is Authenticated. So it passes the
			//Spring Security Configuration successfully
			SecurityContextHolder.getContext().setAuthentication(authToken);
		}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		filterChain.doFilter(request, response);
	}
	
	private String parseJwt(HttpServletRequest request) {
		String requestHeader = request.getHeader("Authorization");
		//token looks like => A
		if(StringUtils.hasText(requestHeader)&& requestHeader.startsWith("Bearer ")) {
			String jwtToken = requestHeader.substring(7);
			return jwtToken;
		}
		return null;
	}

}
