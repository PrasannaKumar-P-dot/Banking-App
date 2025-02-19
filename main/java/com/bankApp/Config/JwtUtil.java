package com.bankApp.Config;



import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


public class JwtUtil {

	private String secrete = "Prasanna@12345";
	
	public String generateToken(String username) {
		return Jwts.builder().setSubject(username).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
				.signWith(SignatureAlgorithm.HS256, secrete).compact();
	}
	
	public String extractUsername(String token) {
	    return extractClaims(token).getSubject();
	}
	
	public Claims extractClaims(String token) {
		return Jwts.parser().setSigningKey(secrete).parseClaimsJws(token).getBody();
	}
	
	public boolean validateToken(String token, String username) {
		return extractClaims(token).getSubject().equals(username) && !isTokenExpired(token);
	}

	private boolean isTokenExpired(String token) {
		return extractClaims(token).getExpiration().before(new Date());
	}
}
