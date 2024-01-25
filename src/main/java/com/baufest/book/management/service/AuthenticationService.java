package com.baufest.book.management.service;

import com.baufest.book.management.dto.security.request.SignUpRequest;
import com.baufest.book.management.dto.security.request.SigninRequest;
import com.baufest.book.management.dto.security.response.JwtAuthenticationResponse;

public interface AuthenticationService {
	JwtAuthenticationResponse signup(SignUpRequest request);
	JwtAuthenticationResponse signin(SigninRequest request);
}
