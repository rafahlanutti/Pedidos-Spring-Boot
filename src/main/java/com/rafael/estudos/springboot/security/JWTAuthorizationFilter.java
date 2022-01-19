package com.rafael.estudos.springboot.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.rafael.estudos.springboot.utils.JWTUtils;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	private static final String AUTHORIZATION = "Authorization";
	private static final String BEARER = "Bearer ";
	private JWTUtils jwtUtils;
	private UserDetailsService detailsService;

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTUtils jwtUtils,
			UserDetailsService detailsService) {
		super(authenticationManager);
		this.jwtUtils = jwtUtils;
		this.detailsService = detailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String header = request.getHeader(AUTHORIZATION);

		if (hasHeaderValid(header)) {
			UsernamePasswordAuthenticationToken auth = getAuthentication(header.substring(7));

			if (auth != null) {
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		}
		chain.doFilter(request, response);
	}

	private boolean hasHeaderValid(String header) {
		return header != null && header.startsWith(BEARER);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(String token) {
		if (jwtUtils.tokenValido(token)) {
			String username = jwtUtils.getUsername(token);
			var user = detailsService.loadUserByUsername(username);
			return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		}
		return null;
	}

}
