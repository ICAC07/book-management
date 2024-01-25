package com.baufest.book.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.baufest.book.management.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
