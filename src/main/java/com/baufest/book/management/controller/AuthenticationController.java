package com.baufest.book.management.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baufest.book.management.constant.Constant;
import com.baufest.book.management.dto.security.request.Credential;
import com.baufest.book.management.dto.security.response.JwtAuthenticationResponse;
import com.baufest.book.management.service.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(Constant.EndPoint.Authentication.ROOT)
@RequiredArgsConstructor
public class AuthenticationController {
	
	private final AuthenticationService authenticationService;
	
    @PostMapping(Constant.EndPoint.Authentication.ACCESS_TOKEN)
    public ResponseEntity<JwtAuthenticationResponse> accessToken(@RequestBody Credential request) {
        return new ResponseEntity<>(authenticationService.accessToken(request), HttpStatus.CREATED);
    }
}
