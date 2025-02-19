package com.bankApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankApp.Config.AuthRequest;
import com.bankApp.Config.JwtUtil;
import com.bankApp.service.CustomUserDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/auth/login")
public class AuthController {
	
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @PostMapping("/auth")
    public String authenticate(@RequestBody AuthRequest authRequest) throws Exception {
    	System.out.println("Request Payload: " + new ObjectMapper().writeValueAsString(authRequest));
    	System.out.println("Username: " + authRequest.getUsername());
        System.out.println("Password: " + authRequest.getPassword());
    	try {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
    	} catch (BadCredentialsException e) {
    		throw new Exception("Incorrect username or password", e);
		}	
    	
    	final UserDetails userDetails= customUserDetailsService.loadUserByUsername(authRequest.getUsername());
    	
        final String jwt = jwtUtil.generateToken(authRequest.getUsername());
        return jwt;
    }

}
