package com.baufest.book.management.service;

import org.springframework.http.ResponseEntity;

import com.baufest.book.management.dto.Book;
import com.baufest.book.management.dto.BookResponse;
import com.baufest.book.management.exception.BookBusinessException;

public interface BookService {
	BookResponse getAll() throws BookBusinessException;
	BookResponse get(Long id) throws BookBusinessException;
	ResponseEntity<BookResponse> add(Book book) throws BookBusinessException;
	ResponseEntity<BookResponse> delete(Long id) throws BookBusinessException;
	String health();
}
