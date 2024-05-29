package com.library.LibraryManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.LibraryManagement.entity.Book;
import com.library.LibraryManagement.exceptions.NoResourceFoundException;
import com.library.LibraryManagement.exceptions.ResourceAlreadyExistsException;
import com.library.LibraryManagement.service.BookService;
import com.library.LibraryManagement.service.BookServiceImpl;

@RestController
@RequestMapping(value="book")
public class BookController {

	@Autowired
	public BookService bookService;
	
	@PostMapping(value="createbook")
	public ResponseEntity<Book> registerNewBook(@RequestBody Book book) throws ResourceAlreadyExistsException{
		Book createdBook = bookService.createBook(book);
		return new ResponseEntity<>(createdBook,HttpStatus.CREATED);
	}
	
	@GetMapping(value="getallbooks")
	public ResponseEntity<List<Book>> listOfAllBooks() throws NoResourceFoundException{
		List<Book> booksList = bookService.getAllBooks();
		return new ResponseEntity<>(booksList,HttpStatus.OK);
	}
	
}
