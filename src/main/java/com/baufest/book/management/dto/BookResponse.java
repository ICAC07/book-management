package com.baufest.book.management.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookResponse {
	
	private Object data;
	private String error;

}
