package com.baufest.book.management.service;

import com.baufest.book.management.dto.Book;
import com.baufest.book.management.dto.BookResponse;
import com.baufest.book.management.exception.BookBusinessException;

public interface BookService {
	
	BookResponse getAll();
	BookResponse get(Long id) throws BookBusinessException;
	BookResponse add(Book book) throws BookBusinessException;
	BookResponse delete(Long id) throws BookBusinessException;
}
