package com.baufest.book.management.constant;

public class Constant {
	private Constant() {}
	
	public static class EndPoint {
		public static final String ROOT			= "/v1/api/book";
		public static final String ID			= "/{id}";
		public static final String HEALTH		= "/health";
		public static final String COMODIN		= "/**";
		
		public static class Credential {
			public static final String USER		= "admin";
			public static final String PASS		= "secret";	
		}
	}
	
	public static class Error {
		public static final String NO_CONTENT	=	"error.no.content";
		public static final String SAVE 		= 	"error.save";
		public static final String DELETE 		= 	"error.delete";
	}
	
	public static class Success {
		public static final String DELETE 		=	"success.delete";
	}
	
	

}
