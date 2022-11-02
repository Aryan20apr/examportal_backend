package com.aryan.examportal_backend.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.weaver.NewConstructorTypeMunger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.aryan.examportal_backend.services.impl.UserDetailServiceImpl;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	//This class works before the request for authenticating the user
	@Autowired
	private UserDetailServiceImpl userDetailServiceImpl;
	
	@Autowired
	private JwtUtils jwtUtils;//This token has all the methds who can generate and validate token 
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String requestTokenHeader=request.getHeader("Authorization");
		
		String username=null;
		String jwtToken=null;
		
		if(requestTokenHeader!=null&&requestTokenHeader.startsWith("Bearer" ))
		{
			try {
				jwtToken=requestTokenHeader.substring(7);
				username=jwtUtils.extractUsername(jwtToken);
				
			} catch ( ExpiredJwtException e) {
				e.printStackTrace();
				System.out.println("JWT Token has expired");
			}
			catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.toString());
			}
		}
		else {
			System.out.println("Invalid token,not start with bearer");
		}
		
		//If token is present, is it validating
		
		if(username!=null&&SecurityContextHolder.getContext().getAuthentication()==null)
		{
			final UserDetails userDetails= userDetailServiceImpl.loadUserByUsername(username);
			if(this.jwtUtils.validateToken(jwtToken, userDetails))
			{//Token is valid
				
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationtoken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				usernamePasswordAuthenticationtoken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationtoken);
			}
		}
		else {
			System.out.println("Token is not valid");
		}
		filterChain.doFilter(request, response);
	}
	
	

}
