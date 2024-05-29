package com.library.LibraryManagement.service;

import java.util.List;

import com.library.LibraryManagement.entity.Book;
import com.library.LibraryManagement.exceptions.NoResourceFoundException;
import com.library.LibraryManagement.exceptions.ResourceAlreadyExistsException;

public interface BookService {
 public Book createBook(Book book)throws ResourceAlreadyExistsException;
 public Book updateBook(Book book);
 public List<Book> getAllBooks()throws NoResourceFoundException;
 public Book getBookById(int id);
 public void deleteBookById(int id);
}
