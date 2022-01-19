package com.rafael.estudos.springboot.utils;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JWTUtils {

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private Long expiration;

	public String generateToken(String username) {

		return Jwts.builder().setSubject(username).setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(io.jsonwebtoken.SignatureAlgorithm.HS512, secret.getBytes()).compact();
	}

	public boolean tokenValido(String token) {
		Claims claims = getClaims(token);
		if (hasClaims(claims)) {
			return isValidToken(claims);
		}
		return false;
	}

	private boolean isValidToken(Claims claims) {
		String username = claims.getSubject();
		Date expiration = claims.getExpiration();
		Date now = new Date(System.currentTimeMillis());

		return username != null && expiration != null && now.before(expiration);
	}

	private boolean hasClaims(Claims claims) {
		return claims != null;
	}

	public String getUsername(String token) {
		Claims claims = getClaims(token);
		if (hasClaims(claims)) {
			return claims.getSubject();
		}
		return null;
	}

	private Claims getClaims(String token) {
		try {
			return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			return null;
		}
	}

}
