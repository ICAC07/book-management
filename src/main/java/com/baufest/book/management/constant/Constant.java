package com.baufest.book.management.constant;

public class Constant {
	private Constant() {}
	
	public static class EndPoint {
		public static final String ROOT			= "/v1/api/book";
		public static final String ID			= "/{id}";
	}
	
	public static class Error {
		public static final String NOT_FOUND	=	"error.not.found";
		public static final String SAVE 		= 	"error.save";
		public static final String DELETE 		= 	"error.delete";
	}
	
	public static class Success {
		public static final String DELETE 		=	"success.delete";
	}
	
	

}
