package com.baufest.book.management.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.data.util.Streamable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baufest.book.management.constant.Constant;
import com.baufest.book.management.dto.Book;
import com.baufest.book.management.dto.BookResponse;
import com.baufest.book.management.exception.BookBusinessException;
import com.baufest.book.management.mapper.IMapper;
import com.baufest.book.management.repository.BookRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
	
	private final BookRepository repository;
	private final MessageTranslator messageTraslator;
	private final IMapper mapper;
	
	@Override
	@Transactional(readOnly = true)
	public BookResponse getAll() throws BookBusinessException {
		List<Book> books = mapper.toAll(Streamable.of(repository.findAll()).toList());
		if(!books.isEmpty()) {
			return BookResponse.builder().data(books).build();
		}
		
		throw new BookBusinessException(messageTraslator.get(Constant.Error.NO_CONTENT), HttpStatus.NO_CONTENT);
	}
	
	@Override
	@Transactional(readOnly = true)
	public BookResponse get(Long id) throws BookBusinessException {
		com.baufest.book.management.model.Book book = findById(id);
		return BookResponse.builder().data(Arrays.asList(mapper.to(book))).build();
	}
	
	@Override
	public BookResponse add(Book data) throws BookBusinessException {
		try {
			com.baufest.book.management.model.Book book = repository.save(mapper.to(data));
			return BookResponse.builder().data(Arrays.asList(mapper.to(book))).build();
		}catch(Exception se) {
			log.error(messageTraslator.get(Constant.Error.SAVE, se.getMessage()));
			throw new BookBusinessException(messageTraslator.get(Constant.Error.SAVE, se.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR); 
		}
	}

	@Override
	public BookResponse delete(Long id) throws BookBusinessException {
		try {
			com.baufest.book.management.model.Book book = findById(id);
			repository.deleteById(book.getId());
			return BookResponse.builder().data(messageTraslator.get(Constant.Success.DELETE)).build();
		}catch(BookBusinessException bbe) {
			throw bbe; 
		}catch(Exception se) {
			log.error(messageTraslator.get(Constant.Error.DELETE, se.getMessage()));
			throw new BookBusinessException(messageTraslator.get(Constant.Error.DELETE, se.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR); 
		}
	}
	
	@Override
	public String health() {
		return messageTraslator.get(Constant.Success.HEALTH);
	}
	
	private com.baufest.book.management.model.Book findById(Long id) throws BookBusinessException {
		return repository.findById(id).orElseThrow(
					() ->  new BookBusinessException(messageTraslator.get(Constant.Error.NO_CONTENT), HttpStatus.NO_CONTENT));
	}

}
