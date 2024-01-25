package com.baufest.book.management.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.baufest.book.management.constant.Constant;
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
	private final AuthenticationManager authenticationManager;
	private final MessageTranslator messageTranslator;
		
	@Override
	public JwtAuthenticationResponse registerCustomer(Credential request) {
	    var user = User.builder()
    			.username(request.getUsername())
    			.password(passwordEncoder.encode(request.getPassword()))
	            .role(Role.ADMIN).build();
	    userRepository.save(user);
	    var jwt = jwtService.generateToken(user);
	    return JwtAuthenticationResponse.builder().token(jwt).build();
	}
	
	@Override
    public JwtAuthenticationResponse getAccessToken(Credential request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        var user = userRepository.findByusername(request.getUsername())
                .orElseThrow(() -> new IllegalArgumentException(messageTranslator.get(Constant.Error.USER_INVALID)));
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}
