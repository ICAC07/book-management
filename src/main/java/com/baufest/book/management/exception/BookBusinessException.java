package com.baufest.book.management.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

public class BookBusinessException extends Exception {
	
	private static final long serialVersionUID = 1;
	@Getter private final String message;
	@Getter private final HttpStatus statusCode;
	
	public BookBusinessException(String message) {
		super(message);
	    this.message = message;
	    this.statusCode  = null;
	}
	
	public BookBusinessException(String message, Throwable cause) {
		super(message, cause);
	    this.message = message;
	    this.statusCode  = null;
	}
	
	public BookBusinessException(String message, HttpStatus statusCode) {
		super(message);
	    this.message = message;
	    this.statusCode  = statusCode;
	}
}
