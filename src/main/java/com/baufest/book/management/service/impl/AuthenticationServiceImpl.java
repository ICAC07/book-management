package com.baufest.book.management.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.baufest.book.management.dto.security.request.Credential;
import com.baufest.book.management.dto.security.response.JwtAuthenticationResponse;
import com.baufest.book.management.model.Role;
import com.baufest.book.management.model.User;
import com.baufest.book.management.repository.UserRepository;
import com.baufest.book.management.service.AuthenticationService;
import com.baufest.book.management.service.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
		
	@Override
	public JwtAuthenticationResponse accessToken(Credential request) {
	    var user = User.builder()
	    			.username(request.getUsername())
	    			.password(passwordEncoder.encode(request.getPassword()))
	            .role(Role.ADMIN).build();
	    userRepository.save(user);
	    var jwt = jwtService.generateToken(user);
	    return JwtAuthenticationResponse.builder().token(jwt).build();
	}
}
