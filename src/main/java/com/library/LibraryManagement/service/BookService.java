package com.library.LibraryManagement.service;

import java.util.List;

import com.library.LibraryManagement.entity.Book;
import com.library.LibraryManagement.entity.Loan;
import com.library.LibraryManagement.exceptions.ResourceAlreadyExistsException;
import com.library.LibraryManagement.exceptions.ResourceNotFoundException;
import org.apache.coyote.BadRequestException;

public interface BookService {
 public Book createBook(Book book) throws ResourceAlreadyExistsException, BadRequestException;
 public Book updateBook(Book book) throws ResourceNotFoundException;
 public List<Book> getAllBooks()throws ResourceNotFoundException;
 public Book getBookById(long id) throws ResourceNotFoundException;
 public void deleteBookById(String id) throws ResourceNotFoundException;
 List<Book> searchBooksByTitle(String title);

 List<Book> searchBooksByAuthor(String author);

 List<Book> searchBooksByGenre(String genre);
 Loan issueBook(String isbn, long userId);
}
