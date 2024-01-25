package com.baufest.book.management.service;

import com.baufest.book.management.dto.security.request.Credential;
import com.baufest.book.management.dto.security.response.JwtAuthenticationResponse;

public interface AuthenticationService {
	JwtAuthenticationResponse accessToken(Credential request);
}
