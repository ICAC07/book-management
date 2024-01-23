package com.baufest.book.management.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.baufest.book.management.dto.Book;

@Mapper(componentModel = "spring" )
public interface IMapper {
	
	IMapper MAPPER = Mappers.getMapper(IMapper.class);
	
	public com.baufest.book.management.model.Book to(Book book);
	
	public Book to(com.baufest.book.management.model.Book book);
	
	public List<Book> toAll(List<com.baufest.book.management.model.Book> books);
}
