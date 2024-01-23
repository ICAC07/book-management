package com.baufest.book.management.exception;

public class BookTechnicalException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private final String message;
	
	public BookTechnicalException(String message) {
		super(message);
	    this.message = message;
	}
	
	public BookTechnicalException(String message, Throwable cause) {
		super(message, cause);
	    this.message = message;
	}
	
	@Override
	public String getMessage() {
	    return message;
	}
	
}
