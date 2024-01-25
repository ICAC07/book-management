package com.baufest.book.management.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.baufest.book.management.dto.Book;
import com.baufest.book.management.dto.BookResponse;
import com.baufest.book.management.exception.BookBusinessException;
import com.baufest.book.management.mapper.IMapper;
import com.baufest.book.management.repository.BookRepository;
import com.baufest.book.management.service.impl.BookServiceImpl;
import com.baufest.book.management.service.impl.MessageTranslator;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {
	
	@InjectMocks
	BookServiceImpl service;
	
	private Book book;
	
	@Mock
    private BookRepository repository;
	
	@Mock
    private MessageTranslator messageTranslator;
	
	@Mock
	private IMapper mapper;
	
	@BeforeEach
    public void setup(){
        book = newBook(null);
    }
	
	@SuppressWarnings("unchecked")
	@DisplayName("Get book by Id")
	@Test
	public void getById() throws BookBusinessException {
		// Precondition or setup
		Long id = 1L;
		com.baufest.book.management.model.Book entityCom = newEntity(id);
		when(repository.findById(id)).thenReturn(Optional.of(entityCom));
		Book bookResponse = newBook(id);
		when(mapper.to(entityCom)).thenReturn(bookResponse);
		
		// Execute Test
		BookResponse response = service.get(id);
		
		// Validation
		assertNotNull(response);
		assertEquals(((List<Book>) response.getData()).size(), 1);
	}
	
	@DisplayName("Get book by Id with Exception")
	@Test
	public void getByIdShouldThrowException() throws BookBusinessException {
		// Precondition or setup
		Long id = 1L;
		when(repository.findById(id)).thenReturn(Optional.empty());
		
		// Execute Test
		Throwable exception = assertThrows(BookBusinessException.class, () -> {
			service.get(id);
		});
		
		// Validation
		assertNotNull(exception);
	}
	
	@SuppressWarnings("unchecked")
	@DisplayName("Get all books")
	@Test
	public void getAll() throws BookBusinessException {
		// Precondition or setup
		List<com.baufest.book.management.model.Book> entityBooks = Arrays.asList(newEntity(1L), newEntity(2L));
		List<com.baufest.book.management.dto.Book> books = Arrays.asList(newBook(1L), newBook(2L));
		when(repository.findAll()).thenReturn(entityBooks);
		when(mapper.toAll(anyList())).thenReturn(books);
		
		// Execute Test
		BookResponse response = service.getAll();
		
		// Validation
		assertNotNull(response);
		assertEquals(((List<Book>) response.getData()).size(), 2);
	}
	
	@DisplayName("Get all books with Exception")
	@Test
	public void getAllShouldThrowException() throws BookBusinessException {
		// Precondition or setup
		Long id = 1L;
		when(repository.findById(id)).thenReturn(Optional.empty());
		
		// Execute Test
		Throwable exception = assertThrows(BookBusinessException.class, () -> {
			service.get(id);
		});
		
		// Validation
		assertNotNull(exception);
	}
	
	@SuppressWarnings("unchecked")
	@DisplayName("Created book successful")
	@Test
	public void add() throws BookBusinessException {
		// Precondition or setup
		Long id = null;
		com.baufest.book.management.model.Book entity = newEntity(id);
		id = 1L;
		when(mapper.to(book)).thenReturn(entity);
		com.baufest.book.management.model.Book entityCom = newEntity(id);
		when(repository.save(entity)).thenReturn(entityCom);
		Book bookResponse = newBook(id);
		when(mapper.to(entityCom)).thenReturn(bookResponse);
		
		// Execute Test
		ResponseEntity<BookResponse> response = service.add(book);
		
		// Validation
		assertNotNull(response);
		assertEquals(((List<Book>) response.getBody().getData()).size(), 1);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}
	
	@DisplayName("Create book with Exception")
	@Test
	public void addShouldThrowException() {
		// Precondition or setup
		Long id = null;
		com.baufest.book.management.model.Book entity = newEntity(id);
		id = 1L;
		com.baufest.book.management.model.Book entityCom = newEntity(id);
		when(repository.save(entity)).thenReturn(entityCom);
		
		// Execute Test
		Throwable exception = assertThrows(BookBusinessException.class, () -> {
			service.add(book);
		});
		
		// Validation
		assertNotNull(exception);
	}
	
	@DisplayName("Deleted successful")
	@Test
	public void delete() throws BookBusinessException {
		// Precondition or setup
		Long id = 1L;
		com.baufest.book.management.model.Book entityCom = newEntity(id);
		when(repository.findById(id)).thenReturn(Optional.of(entityCom));
		when(messageTranslator.get(anyString())).thenReturn("Deleted successfully!");
		
		// Execute Test
		ResponseEntity<BookResponse> response = service.delete(id);
		
		// Validation
		assertNotNull(response);
		assertEquals(((String) response.getBody().getData()), "Deleted successfully!");
		assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
	}
	
	@DisplayName("Deleted with Exception")
	@Test
	public void deleteShouldThrowException() {
		// Precondition or setup
		Long id = 1L;
		when(repository.findById(id)).thenReturn(Optional.empty());
		
		// Execute Test
		Throwable exception = assertThrows(BookBusinessException.class, () -> {
			service.delete(id);
		});
		
		// Validation
		assertNotNull(exception);
	}
	
	private Book newBook(Long id) {
		return Book.builder()
			.id(id)
			.title("Matematicas 1")
			.author("Baldor")
			.isbn("978-6075502090")
			.quantity(45)
			.build();
	}
	
	private com.baufest.book.management.model.Book newEntity(Long id) {
		return com.baufest.book.management.model.Book.builder()
			.id(id)
			.title("Matematicas 1")
			.author("Baldor")
			.isbn("978-6075502090")
			.quantity(45)
			.build();
	}

}
