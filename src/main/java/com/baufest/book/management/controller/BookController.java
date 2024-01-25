package com.baufest.book.management.controller;

import org.springframework.http.HttpStatus;
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
	public ResponseEntity<BookResponse> getAll() throws BookBusinessException {
		return ResponseEntity.ok(service.getAll());
	}
	
	@GetMapping(value = Constant.EndPoint.ID)
	public ResponseEntity<BookResponse> getById(@PathVariable Long id) throws BookBusinessException {
		return ResponseEntity.ok(service.get(id));
	}
	
	@PostMapping
	public ResponseEntity<BookResponse> add(@RequestBody Book body) throws BookBusinessException {
		return new ResponseEntity<>(service.add(body), HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = Constant.EndPoint.ID)
	public ResponseEntity<BookResponse> delete(@PathVariable Long id) throws BookBusinessException {
		return new ResponseEntity<>(service.delete(id), HttpStatus.ACCEPTED);
	}
	
	@GetMapping(value= Constant.EndPoint.HEALTH)
	public ResponseEntity<String> health() {
		return ResponseEntity.ok(service.health());
	}
	
	@ExceptionHandler(BookBusinessException.class)
	public ResponseEntity<BookResponse> bussinesException(BookBusinessException bbe) {
		return new ResponseEntity<>(BookResponse.builder().error(bbe.getMessage()).build() , bbe.getStatusCode());
	}
}
