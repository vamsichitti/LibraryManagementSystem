package com.library.LibraryManagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.LibraryManagement.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	public Book findByTitle(String title);
	public Book findByAuthor(String author);
	public Optional<Book> findByIsbn(String isbn);

}
