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
	
    @PostMapping(Constant.EndPoint.Authentication.REGISTER_CUSTOMER)
    public ResponseEntity<JwtAuthenticationResponse> registerCustomer(@RequestBody Credential request) {
        return new ResponseEntity<>(authenticationService.registerCustomer(request), HttpStatus.CREATED);
    }
    
    @PostMapping(Constant.EndPoint.Authentication.GET_ACCESS_TOKEN)
    public ResponseEntity<JwtAuthenticationResponse> getAccessToken(@RequestBody Credential request) {
        return ResponseEntity.ok(authenticationService.getAccessToken(request));
    }
}
