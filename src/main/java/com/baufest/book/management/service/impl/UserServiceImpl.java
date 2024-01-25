package com.baufest.book.management.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.baufest.book.management.constant.Constant;
import com.baufest.book.management.repository.UserRepository;
import com.baufest.book.management.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    
	private final UserRepository userRepository;
	private final MessageTranslator messageTranslator;
    
    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return userRepository.findByusername(username)
                        .orElseThrow(() -> new UsernameNotFoundException(messageTranslator.get(Constant.Error.USER_NOT_FOUND, username)));
            }
        };
    }
}