package com.baufest.book.management.dto.security.request;

import com.baufest.book.management.model.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Credential {
	private String username;
    private String password;
    private Role role;
}
