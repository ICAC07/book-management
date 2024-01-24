package com.baufest.book.management.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baufest.book.management.constant.Constant;
import com.baufest.book.management.dto.Book;
import com.baufest.book.management.dto.BookResponse;
import com.baufest.book.management.exception.BookBusinessException;
import com.baufest.book.management.service.BookService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(Constant.EndPoint.ROOT)
public class BookController {
	
	private BookService service;
	
	@GetMapping
	public BookResponse getAll() {
		return service.getAll();
	}
	
	@GetMapping(value = Constant.EndPoint.ID)
	public BookResponse getById(@PathVariable Long id) throws BookBusinessException {
		return service.get(id);
	}
	
	@PostMapping
	public BookResponse add(@RequestBody Book body) throws BookBusinessException {
		return service.add(body);
	}
	
	@DeleteMapping(value = Constant.EndPoint.ID)
	public BookResponse delete(@PathVariable Long id) throws BookBusinessException {
		return service.delete(id);
	}
	
	@GetMapping(value= Constant.EndPoint.HEALTH)
	public Boolean health() {
		return Boolean.TRUE;
	}
	
	@ExceptionHandler(BookBusinessException.class)
	public ResponseEntity<BookResponse> bussinesException(BookBusinessException bbe) {
		return new ResponseEntity<>(BookResponse.builder().error(bbe.getMessage()).build() , bbe.getStatusCode());
	}
}
